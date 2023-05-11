package org.android.go.sopt.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.go.sopt.domain.AuthRepository
import org.android.go.sopt.data.repositoryImpl.AuthRepositoryImpl
import org.android.go.sopt.data.repositoryImpl.FollowerRepositoryImpl
import org.android.go.sopt.domain.FollowerRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun providesFollowerRepo(followerImpl: FollowerRepositoryImpl): FollowerRepository

    @Singleton
    @Binds
    abstract fun providesSignRepo(authImpl: AuthRepositoryImpl): AuthRepository

}