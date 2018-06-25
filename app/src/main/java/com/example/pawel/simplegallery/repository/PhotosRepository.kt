package com.example.pawel.simplegallery.repository

import com.example.pawel.simplegallery.api.Photo
import com.example.pawel.simplegallery.api.PhotosApi
import com.example.pawel.simplegallery.tools.toFlowable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class PhotosRepository constructor(private val photosApi: PhotosApi) : BaseLiveDataRepository() {

    private val lastPhotos: BehaviorSubject<List<Photo>> = BehaviorSubject.create()
    private var lastPhotosAlbumId = 0

    fun loadPhotos(compositeDisposable: CompositeDisposable, albumId: Int, after: () -> Unit = {}) {
        compositeDisposable.add(photosApi.getListByAlbumId(albumId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    networkError.onNext(false)
                    lastPhotos.onNext(it)
                    lastPhotosAlbumId = albumId
                    after()
                }, {
                    networkError.onNext(true)
                    after()
                }))
    }

    fun getListByAlbumId(compositeDisposable: CompositeDisposable, albumId: Int): Flowable<List<Photo>> {
        if (lastPhotosAlbumId != albumId && lastPhotos.hasValue())
            lastPhotos.onNext(listOf())

        if (lastPhotosAlbumId != albumId || !lastPhotos.hasValue())
            loadPhotos(compositeDisposable, albumId)

        return lastPhotos.toFlowable()
    }

    fun getById(compositeDisposable: CompositeDisposable, id: Int): Flowable<Photo> {
        val subject = PublishSubject.create<Photo>()

        compositeDisposable.add(photosApi.getListById(id)
                .map { it.first() }
                .subscribe({
                    networkError.onNext(false)
                    subject.onNext(it)
                }, {
                    networkError.onNext(true)
                }))


        return subject.toFlowable()

    }

}