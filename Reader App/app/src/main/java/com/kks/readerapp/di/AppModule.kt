package com.kks.readerapp.di

import com.google.firebase.firestore.FirebaseFirestore
import com.kks.readerapp.BuildConfig
import com.kks.readerapp.network.BooksApi
import com.kks.readerapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        retryOnConnectionFailure(true) /* 연결에 문제 발생시 재시도 */
        addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }) /* 디버그 상태에서 http 통신 로깅 */

    }.build()
    @Singleton
    @Provides
    fun provideBookApi(
        okHttpClient: OkHttpClient
    ): BooksApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApi::class.java)
    }
}