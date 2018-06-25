package com.example.pawel.simplegallery.repository

import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import com.example.pawel.simplegallery.tools.toFlowable

abstract class BaseLiveDataRepository {

    protected var networkError: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    fun getNetworkError(): Flowable<Boolean> {
        return networkError.toFlowable()
    }

}