package com.example.pawel.simplegallery.repository

import com.example.pawel.simplegallery.api.Album
import com.example.pawel.simplegallery.api.AlbumsApi
import com.example.pawel.simplegallery.tools.toFlowable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

class AlbumsRepository constructor(private val albumsApi: AlbumsApi) : BaseLiveDataRepository() {

    private val albums: HashMap<Int, BehaviorSubject<List<Album>>> = HashMap()

    fun loadAlbums(compositeDisposable: CompositeDisposable, userId: Int, after: () -> Unit = {}) {
        compositeDisposable.add(albumsApi.getListByUserId(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    networkError.onNext(false)
                    albums[userId]?.onNext(it)
                    after()
                }, {
                    networkError.onNext(true)
                    after()
                }))
    }

    fun getListByUserId(compositeDisposable: CompositeDisposable, userId: Int): Flowable<List<Album>> {
        if (!albums.containsKey(userId))
            albums[userId] = BehaviorSubject.create()

        val subject = albums[userId]!!

        if (!subject.hasValue())
            loadAlbums(compositeDisposable, userId)

        return subject.toFlowable()
    }

}