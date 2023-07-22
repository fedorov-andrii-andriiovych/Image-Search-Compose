package com.fedorov.andrii.andriiovych.imagesearch.presentation.di

import android.content.Context
import androidx.room.Room
import com.fedorov.andrii.andriiovych.imagesearch.data.database.AppDatabase
import com.fedorov.andrii.andriiovych.imagesearch.data.network.ImageService
import com.fedorov.andrii.andriiovych.imagesearch.data.repositories.ImageSaveRepositoryImpl
import com.fedorov.andrii.andriiovych.imagesearch.data.repositories.NetworkRepositoryImpl
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.ImageSaveRepository
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindNetworkRepository(NetworkRepositoryImpl: NetworkRepositoryImpl): NetworkRepository

    @Binds
    abstract fun bindImageSaveRepository(ImageSaveRepositoryImpl: ImageSaveRepositoryImpl): ImageSaveRepository
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideImageService(): ImageService {
        val baseUrl = "https://pixabay.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ImageService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database").fallbackToDestructiveMigration()
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    fun providesCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher
