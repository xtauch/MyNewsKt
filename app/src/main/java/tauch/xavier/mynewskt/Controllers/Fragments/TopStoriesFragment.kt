package tauch.xavier.mynewskt.Controllers.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import android.widget.Toast

import com.bumptech.glide.Glide
import tauch.xavier.mynewskt.Adapters.TopStoriesAdapter
import tauch.xavier.mynewskt.Controllers.Activities.WebActivity
import tauch.xavier.mynewskt.Models.TopStories.TopStories
import tauch.xavier.mynewskt.Models.TopStories.TopStoriesResult
import tauch.xavier.mynewskt.R
import tauch.xavier.mynewskt.Utils.ItemClickSupport
import tauch.xavier.mynewskt.Utils.NYTStreams
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

import kotlinx.android.synthetic.main.fragment_top_stories.*
import kotlinx.android.synthetic.main.fragment_top_stories.view.*


import java.util.ArrayList


open class TopStoriesFragment : Fragment() {


    lateinit var mRecyclerView: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var mProgressBar: ProgressBar

    //FOR DATA
    lateinit var mTopStoriesResults: MutableList<TopStoriesResult>
    lateinit var mTopStoriesAdapter: TopStoriesAdapter
    var mDisposable: Disposable? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_top_stories, container, false)

        mRecyclerView = view.findViewById(R.id.fragment_top_stories_recycler_view) as RecyclerView
        swipeRefreshLayout = view.findViewById(R.id.fragment_main_swipe_container) as SwipeRefreshLayout
        mProgressBar = view.findViewById(R.id.loadingPanel) as ProgressBar

        this.configureRecyclerView()
        this.executeHttpTopStoriesRequest()
        this.configureSwipeRefreshLayout()
        this.configureOnClickRecyclerView()
        return view
    }

    override fun onDestroy() {
        Log.e("onDestroy", "onDestroy TopStoriesFragment")
        this.disposeWhenDestroy()
        super.onDestroy()
    }

    // -----------------
    // ACTION
    // -----------------

    // Configure click on RecyclerView's item
    fun configureOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_item)
            .setOnItemClickListener(object : ItemClickSupport.OnItemClickListener {
                override fun onItemClicked(recyclerView: RecyclerView, position: Int, v: View) {
                    val intent = Intent(context, WebActivity::class.java)
                    intent.putExtra("openUrl", mTopStoriesResults[position].url)
                    startActivity(intent)
                }
            })
    }


    // -----------------
    // CONFIGURATION
    // -----------------

    // 3 - Configure RecyclerView, Adapter, LayoutManager & glue it together
    fun configureRecyclerView() {
        // 3.1 - Reset list
        this.mTopStoriesResults = ArrayList()
        // 3.2 - Create mTopStoriesAdapter passing the top stories
        this.mTopStoriesAdapter = TopStoriesAdapter(this.mTopStoriesResults, Glide.with(this))
        // 3.3 - Attach the mTopStoriesAdapter to the recyclerview to populate items
        this.mRecyclerView.adapter = this.mTopStoriesAdapter
        // 3.4 - Set layout manager to position the items
        val mLayoutManager = LinearLayoutManager(context)
        this.mRecyclerView.layoutManager = mLayoutManager

        mRecyclerView.addItemDecoration(
            DividerItemDecoration(
                mRecyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )


    }

    // Configure the SwipeRefreshLayout
    fun configureSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener { executeHttpTopStoriesRequest() }
    }

    // ------------------------------
    //  HTTP REQUEST (RxJAVA)
    // ------------------------------


    // Execute our Stream
    private fun executeHttpTopStoriesRequest() {
        // Execute the stream subscribing to Observable defined inside NYTStream
        this.mDisposable =
            NYTStreams.fetchTopStories("home").subscribeWith(object : DisposableObserver<TopStories>() {

                override fun onNext(topStories: TopStories) {
                    Log.e("TopStoriesF - onNext", "On Next TopStoriesFragment")
                    // Update RecyclerView after getting results from NYT API
                    updateUI(topStories.topStoriesResults)
                }

                override fun onError(e: Throwable) {
                    Log.e("TopStoriesF - onError", Log.getStackTraceString(e))
                    Toast.makeText(context, "Une erreur est survenue ", Toast.LENGTH_LONG).show()
                }

                override fun onComplete() {
                    Log.e("TopSF - onComplete", "On Complete TopStoriesFragment")

                }
            })
    }

    fun disposeWhenDestroy() {
        if (this.mDisposable != null && !this.mDisposable!!.isDisposed) this.mDisposable!!.dispose()
    }


    // ------------------
    //  UPDATE UI
    // ------------------

    // 3 - Update UI showing only name of users
    fun updateUI(topStoriesResults: List<TopStoriesResult>) {
        mProgressBar.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = false
        mTopStoriesResults.clear()
        mTopStoriesResults.addAll(topStoriesResults)
        mTopStoriesAdapter.notifyDataSetChanged()
    }

    companion object {


        fun newInstance(): TopStoriesFragment {
            return TopStoriesFragment()
        }
    }
}
