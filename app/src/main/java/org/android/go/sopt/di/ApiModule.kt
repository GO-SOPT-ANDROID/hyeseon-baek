package org.android.go.sopt.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.android.go.sopt.data.service.FollowerApiService
import org.android.go.sopt.data.service.SignApiService
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    /*
     retrofit2.Retrofit is bound multiple times:
     같은 retrofit 참조하면 모호하다고 생각해서 오류남 Qualifier활용할것
     */
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class SignRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ReqresRetrofit

    @Provides
    @Singleton
    fun provideSignApiService(@SignRetrofit retrofit: Retrofit): SignApiService =
        retrofit.create(SignApiService::class.java)

    @Provides
    @Singleton
    fun provideReqresApiService(@ReqresRetrofit retrofit: Retrofit): FollowerApiService =
        retrofit.create(FollowerApiService::class.java)
}