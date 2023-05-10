package org.android.go.sopt.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.android.go.sopt.data.service.SignUpService
import org.android.go.sopt.util.Constants.BASE_URL
import retrofit2.Retrofit
import java.io.IOException

object ApiFactory {
    @OptIn(ExperimentalSerializationApi::class)
    val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient(AppInterceptor()))
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    fun okHttpClient(interceptor: AppInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
            val newRequest = request().newBuilder()
                .build()
            proceed(newRequest)
        }
    }
    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)

    object ServicePool{
        val signUpService = ApiFactory.create<SignUpService>()
    }
}