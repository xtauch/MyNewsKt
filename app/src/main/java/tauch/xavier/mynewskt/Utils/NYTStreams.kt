package tauch.xavier.mynewskt.Utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tauch.xavier.mynewskt.Models.ArticleSearch.ArticleSearch
import tauch.xavier.mynewskt.Models.MostPopular.MostPopular
import tauch.xavier.mynewskt.Models.TopStories.TopStories

import java.util.concurrent.TimeUnit

/**
 * Created by Xavier TAUCH on 08/04/2018.
 */

object NYTStreams {

    private val nytService by lazy {
        NYTApiService.create()
    }

    fun fetchTopStories(section: String): Observable<TopStories> {
        return nytService.getTopStories(section)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(10, TimeUnit.SECONDS)
    }

    fun fetchMostPopular(section: String): Observable<MostPopular> {
        return nytService.getMostPopular(section)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(10, TimeUnit.SECONDS)
    }

    fun fetchBusinessTopStories(): Observable<TopStories> {
        return nytService.getTopStories("business")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(10, TimeUnit.SECONDS)
    }

    fun fetchArticleSearch(
        query: String,
        beginDate: String,
        endDate: String,
        filterQuery: String,
        apiKey: String
    ): Observable<ArticleSearch> {
        return nytService.getArticleSearch(query, beginDate, endDate, filterQuery, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(10, TimeUnit.SECONDS)
    }
}
