package com.example.pawel.simplegallery.tools

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class LiveDataViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}