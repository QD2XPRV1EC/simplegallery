package com.example.pawel.simplegallery.repository

import com.example.pawel.simplegallery.api.User
import com.example.pawel.simplegallery.api.UsersApi
import com.example.pawel.simplegallery.tools.toFlowable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

class UsersRepository constructor(private val usersApi: UsersApi) : BaseLiveDataRepository() {

    private val users: BehaviorSubject<List<User>> = BehaviorSubject.create()

    fun loadUsers(compositeDisposable: CompositeDisposable, after: () -> Unit = {}) {
        compositeDisposable.add(usersApi.getList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    networkError.onNext(false)
                    users.onNext(it)
                    after()
                }, {
                    networkError.onNext(true)
                    after()
                }))
    }

    fun getUsers(compositeDisposable: CompositeDisposable): Flowable<List<User>> {
        if (!users.hasValue())
            loadUsers(compositeDisposable)

        return users.toFlowable()
    }

    fun getUserById(compositeDisposable: CompositeDisposable, id: Int): Flowable<User> {
        if (!users.hasValue())
            loadUsers(compositeDisposable)

        return users.toFlowable()
                .map { it.first { it.id == id } }
    }

}