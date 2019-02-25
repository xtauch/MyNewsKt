package tauch.xavier.mynewskt.Utils

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tauch.xavier.mynewskt.BuildConfig
import tauch.xavier.mynewskt.Models.ArticleSearch.ArticleSearch
import tauch.xavier.mynewskt.Models.MostPopular.MostPopular
import tauch.xavier.mynewskt.Models.TopStories.TopStories

/**
 * Created by Xavier TAUCH on 21/01/2019.
 */
interface NYTApiService {

    @GET("topstories/v2/{section}.json?api-key="+BuildConfig.NYTApiKey)
    fun getTopStories(@Path("section") section: String): Observable<TopStories>

    @GET("mostpopular/v2/mostviewed/{section}/1.json?api-key="+BuildConfig.NYTApiKey)
    fun getMostPopular(@Path("section") section: String): Observable<MostPopular>

    @GET("search/v2/articlesearch.json")
    fun getArticleSearch(
        @Query("q") query: String,
        @Query("begin_date") beginDate: String,
        @Query("end_date") endDate: String,
        @Query("fq") filterQuery: String,
        @Query("api-key") apiKey: String
    ): Observable<ArticleSearch>


    companion object {
        fun create(): NYTApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://api.nytimes.com/svc/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(NYTApiService::class.java)
        }
    }

}