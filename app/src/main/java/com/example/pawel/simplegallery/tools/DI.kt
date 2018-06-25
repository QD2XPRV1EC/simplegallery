package com.example.pawel.simplegallery.tools

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.bumptech.glide.annotation.GlideModule
import com.example.pawel.simplegallery.api.AlbumsApi
import com.example.pawel.simplegallery.api.PhotosApi
import com.example.pawel.simplegallery.api.UsersApi
import com.example.pawel.simplegallery.repository.AlbumsRepository
import com.example.pawel.simplegallery.repository.PhotosRepository
import com.example.pawel.simplegallery.repository.UsersRepository
import com.example.pawel.simplegallery.ui.album.AlbumActivity
import com.example.pawel.simplegallery.ui.album.AlbumViewModel
import com.example.pawel.simplegallery.ui.profile.ProfileActivity
import com.example.pawel.simplegallery.ui.profile.ProfileAlbumsFragment
import com.example.pawel.simplegallery.ui.profile.ProfileInfoFragment
import com.example.pawel.simplegallery.ui.profile.ProfileViewModel
import com.example.pawel.simplegallery.ui.users.UsersActivity
import com.example.pawel.simplegallery.ui.users.UsersViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton
import com.bumptech.glide.module.AppGlideModule
import com.example.pawel.simplegallery.ui.photo.PhotoActivity
import com.example.pawel.simplegallery.ui.photo.PhotoViewModel


class ViewModelFactory {

    @Inject
    lateinit var usersRepository: UsersRepository

    @Inject
    lateinit var albumsRepository: AlbumsRepository

    @Inject
    lateinit var photosRepository: PhotosRepository

    init {
        DaggerViewModelFactoryComponent.builder().build().inject(this)
    }

    private val simpleFactory = object : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return when(modelClass) {
                UsersViewModel::class.java -> UsersViewModel(usersRepository)
                else -> modelClass.newInstance()
            } as T
        }
    }

    private val factoryWithId = object : ViewModelProvider.NewInstanceFactory() {
        var id :Int = 0

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return when(modelClass) {
                ProfileViewModel::class.java -> ProfileViewModel(id, usersRepository, albumsRepository)
                AlbumViewModel::class.java -> AlbumViewModel(id, photosRepository)
                PhotoViewModel::class.java -> PhotoViewModel(id, photosRepository)
                else -> modelClass.newInstance()
            } as T
        }
    }

    fun raw() = simpleFactory

    fun withId(id:Int): ViewModelProvider.NewInstanceFactory {
        factoryWithId.id = id
        return factoryWithId
    }



}

@GlideModule
class MyAppGlideModule : AppGlideModule()

@Module
class RepositoryModule {

    private var retrofit = createRetrofit()

    @Provides
    @Singleton
    fun usersRepository() = UsersRepository(retrofit.create(UsersApi::class.java))

    @Provides
    @Singleton
    fun albumsRepository() = AlbumsRepository(retrofit.create(AlbumsApi::class.java))

    @Provides
    @Singleton
    fun photosRepository() = PhotosRepository(retrofit.create(PhotosApi::class.java))

}

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun viewModelFactory() = ViewModelFactory()

}

@Module
class FragmentsModule {

    @Provides
    fun profileAlbumsFragment() = ProfileAlbumsFragment()

    @Provides
    fun profileInfoFragment() = ProfileInfoFragment()
}

@Singleton
@Component(modules = [RepositoryModule::class])
interface ViewModelFactoryComponent {
    fun inject(viewModelFactory: ViewModelFactory)
}

@Singleton
@Component(modules = [ViewModelModule::class])
interface FragmentComponent {
    fun inject(fragment: ProfileAlbumsFragment)
    fun inject(fragment: ProfileInfoFragment)
}

@Singleton
@Component(modules = [ViewModelModule::class, FragmentsModule::class])
interface ApplicationComponent {
    fun inject(activity: UsersActivity)
    fun inject(activity: ProfileActivity)
    fun inject(activity: AlbumActivity)
    fun inject(activity: PhotoActivity)
}