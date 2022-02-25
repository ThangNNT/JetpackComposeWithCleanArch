package com.nnt.remote.di

import android.net.sip.SipErrorCode
import android.util.Log
import com.nnt.remote.BuildConfig
import com.nnt.remote.api.MovieApi
import com.nnt.remote.datasource.RemoteMovieDataSource
import com.nnt.remote.datasource.RemoteMovieDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideHttpClient(
    ): HttpClient {
        return HttpClient(Android) {

            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true

                })

                engine {
                    connectTimeout = SipErrorCode.TIME_OUT
                    socketTimeout = SipErrorCode.TIME_OUT
                }
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Ktor==>", message)
                    }

                }
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                }
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                host = BuildConfig.BASE_URL_API
                url {
                    protocol = URLProtocol.HTTPS
                }
                parameter("api_key", BuildConfig.API_KEY)
            }
        }
    }

    @Provides
    @Singleton
    fun provideMovieApi(httpClient: HttpClient): MovieApi {
        return MovieApi(httpClient)
    }

    @Provides
    @Singleton
    fun provideRemoteMovieDataSource(movieApi: MovieApi): RemoteMovieDataSource {
        return RemoteMovieDataSourceImpl(movieApi)
    }
}