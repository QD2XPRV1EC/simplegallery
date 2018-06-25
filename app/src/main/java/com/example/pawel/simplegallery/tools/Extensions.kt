package com.example.pawel.simplegallery.tools

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable

fun <T> Observable<T>.toFlowable(): Flowable<T> {
    return this.toFlowable(BackpressureStrategy.LATEST)
}