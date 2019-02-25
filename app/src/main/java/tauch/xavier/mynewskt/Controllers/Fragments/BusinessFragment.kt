package tauch.xavier.mynewskt.Controllers.Fragments

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast

import tauch.xavier.mynewskt.Models.TopStories.TopStories
import tauch.xavier.mynewskt.R
import tauch.xavier.mynewskt.Utils.NYTStreams
import io.reactivex.observers.DisposableObserver


class BusinessFragment : TopStoriesFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_top_stories, container, false)

        mRecyclerView = view.findViewById(R.id.fragment_top_stories_recycler_view) as RecyclerView
        swipeRefreshLayout = view.findViewById(R.id.fragment_main_swipe_container) as SwipeRefreshLayout
        mProgressBar = view.findViewById(R.id.loadingPanel) as ProgressBar

        configureRecyclerView()
        configureSwipeRefreshLayout()
        configureOnClickRecyclerView()
        this.executeHttpTopStoriesRequest()
        return view
    }

    override fun onDestroy() {
        Log.e("onDestroy", "onDestroy BusinessFragment")
        disposeWhenDestroy()
        super.onDestroy()
    }

    // -----------------
    // HTTP (RxJAVA)
    // -----------------

    // Execute our Stream
    private fun executeHttpTopStoriesRequest() {
        // Execute the stream subscribing to Observable defined inside GithubStream

        mDisposable =
            NYTStreams.fetchBusinessTopStories().subscribeWith(object : DisposableObserver<TopStories>() {
                override fun onNext(topStories: TopStories) {
                    Log.e("BusinessF - onNext", "On Next BusinessFragment")
                    // Update RecyclerView after getting results from NYT API
                    updateUI(topStories.topStoriesResults)
                }

                override fun onError(e: Throwable) {
                    Log.e("BusinessF - onError", Log.getStackTraceString(e))
                    Toast.makeText(context, "Une erreur est survenue ", Toast.LENGTH_LONG).show()
                }

                override fun onComplete() {
                    Log.e("BusinessF - onComplete", "On Complete BusinessFragment")
                }
            })
    }

    companion object {

        fun newInstance(): BusinessFragment {
            return BusinessFragment()
        }
    }
}

