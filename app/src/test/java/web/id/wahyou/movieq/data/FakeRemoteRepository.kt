package web.id.wahyou.movieq.data
import io.reactivex.Single
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import web.id.wahyou.movieq.BuildConfig
import web.id.wahyou.movieq.BuildConfig.apiKey
import web.id.wahyou.movieq.data.model.movie.ResponseMovie
import web.id.wahyou.movieq.data.model.tvshow.ResponseTvShow
import web.id.wahyou.movieq.data.network.ApiService
import java.util.concurrent.TimeUnit

class FakeRemoteRepository {
    private fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = when (BuildConfig.DEBUG) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }
    private fun providesApiKey() : Interceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request: Request = chain.request()
            val url: HttpUrl = request.url.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()
            request = request.newBuilder().url(url).build()
            return chain.proceed(request)
        }
    }
    private fun providesHttpClient(
        interceptor: HttpLoggingInterceptor,
        apiKey: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            addInterceptor(interceptor)
            addInterceptor(apiKey)
        }.build()
    }
    private fun getRetrofit() : Retrofit = Retrofit.Builder().apply {
        baseUrl(BuildConfig.baseUrl)
        client(providesHttpClient(
            providesHttpLoggingInterceptor(),
            providesApiKey()
        ))
        addConverterFactory(GsonConverterFactory.create())
        addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
    }.build()

    fun getMovie() : Single<ResponseMovie> =
        getRetrofit().create(ApiService::class.java).getUpcomingMovie()
    fun getTv() : Single<ResponseTvShow> =
        getRetrofit().create(ApiService::class.java).getTvShow()
}