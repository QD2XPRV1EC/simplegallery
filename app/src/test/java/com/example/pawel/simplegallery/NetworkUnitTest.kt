package com.example.pawel.simplegallery

import com.example.pawel.simplegallery.tools.RepositoryModule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class NetworkUnitTest {

    private lateinit var compositeDisposable: CompositeDisposable

    @Before
    fun before() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        compositeDisposable = CompositeDisposable()
    }

    @After
    fun after() {
        compositeDisposable.clear()
    }


    @Test
    fun testApiCalls() {
        val module = RepositoryModule()

        val users = module.usersRepository().getUsers(compositeDisposable).blockingFirst()
        assertTrue(users.isNotEmpty())

        val userById = module.usersRepository().getUserById(compositeDisposable, users[0].id).blockingFirst()
        assertTrue(userById.id == users[0].id)

        val albums = module.albumsRepository().getListByUserId(compositeDisposable, users[0].id).blockingFirst()
        assertTrue(albums.isNotEmpty())

        val photos = module.photosRepository().getListByAlbumId(compositeDisposable, albums[0].id).blockingFirst()
        assertTrue(photos.isNotEmpty())

        val photoById = module.photosRepository().getById(compositeDisposable, photos[0].id).blockingFirst()
        assertTrue(photoById.id == photos[0].id)
    }
}
