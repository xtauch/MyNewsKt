package tauch.xavier.mynewskt

import android.support.test.runner.AndroidJUnit4
import io.reactivex.observers.TestObserver

import org.junit.Test
import org.junit.runner.RunWith

import tauch.xavier.mynewskt.Models.TopStories.TopStories
import tauch.xavier.mynewskt.Utils.NYTStreams
import org.hamcrest.MatcherAssert.assertThat
import tauch.xavier.mynewskt.Models.ArticleSearch.ArticleSearch
import tauch.xavier.mynewskt.Models.MostPopular.MostPopular

@RunWith(AndroidJUnit4::class)
class NYTApiCallsInstrumentedTest {

    @Test
    @Throws(Exception::class)
    fun getTopStoriesTest(){

        val testObservable = NYTStreams.fetchTopStories("home")
        val testObserver = TestObserver<TopStories>()

        testObservable.subscribeWith(testObserver)
            .assertNoTimeout()
            .assertNoErrors()
            .awaitTerminalEvent()

        // Get list of news fetched
        val newsFetched = testObserver.values()[0]
        // Verify if status: "OK"
        assertThat("The status of the Stream was read correctly", newsFetched.status.equals("OK"))
        // Verify if section: "home"
        assertThat("The section of the Stream was read correctly", newsFetched.section.equals("home"))
        // Verify if Results Exist
        assertThat("Results exist in the Stream request", newsFetched.topStoriesResults != null)
    }

    @Test
    @Throws(Exception::class)
    fun getMostPopularStoriesTest(){

        val testObservable = NYTStreams.fetchMostPopular("all-sections")
        val testObserver = TestObserver<MostPopular>()

        testObservable.subscribeWith(testObserver)
            .assertNoTimeout()
            .assertNoErrors()
            .awaitTerminalEvent()

        // Get list of news fetched
        val newsFetched = testObserver.values()[0]
        // Verify if status: "OK"
        assertThat("The status of the Stream was read correctly", newsFetched.status.equals("OK"))
        // Verify if Results Exist
        assertThat("Results exist in the Stream request", newsFetched.mostPopularResults != null)
    }

    @Test
    @Throws(Exception::class)
    fun getArticleSearchTest(){

        // Criteria of the query
        val query = "France"
        val checkboxes = "news_desk:( Business , Politics )"
        val beginDate = "20190101"
        val endDate = "20190219"
        val testObservable = NYTStreams.fetchArticleSearch(query, beginDate, endDate, checkboxes, BuildConfig.NYTApiKey)
        val testObserver = TestObserver<ArticleSearch>()

        testObservable.subscribeWith(testObserver)
            .assertNoTimeout()
            .assertNoErrors()
            .awaitTerminalEvent()

        // Get list of news fetched
        val newsFetched = testObserver.values()[0]
        // Verify if status: "OK"
        assertThat("The status of the Stream was read correctly", newsFetched.status.equals("OK"))
        // Verify if Results Exist
        assertThat("Results exist in the Stream request", newsFetched.response != null)
    }
}
