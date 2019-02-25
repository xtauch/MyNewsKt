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
import tauch.xavier.mynewskt.Adapters.MostPopularAdapter
import tauch.xavier.mynewskt.Controllers.Activities.WebActivity
import tauch.xavier.mynewskt.Models.MostPopular.MostPopular
import tauch.xavier.mynewskt.Models.MostPopular.MostPopularResult
import tauch.xavier.mynewskt.R
import tauch.xavier.mynewskt.Utils.ItemClickSupport
import tauch.xavier.mynewskt.Utils.NYTStreams
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.fragment_most_popular.*

import java.util.ArrayList


class MostPopularFragment : Fragment() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var mProgressBar: ProgressBar

    // FOR DATA
    lateinit var mMostPopularResults: MutableList<MostPopularResult>
    lateinit var mMostPopularAdapter: MostPopularAdapter
    private var mDisposable: Disposable? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_most_popular, container, false)

        mRecyclerView = view.findViewById(R.id.fragment_most_popular_recycler_view) as RecyclerView
        swipeRefreshLayout = view.findViewById(R.id.fragment_main_swipe_container) as SwipeRefreshLayout
        mProgressBar = view.findViewById(R.id.loadingPanel) as ProgressBar

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        this.configureMostPopularRecyclerView()
        this.executeHttpMostPopularRequest()
        this.configureOnClickRecyclerView()
        this.configureSwipeRefreshLayout()
    }



    override fun onDestroy() {
        Log.e("onDestroy", "onDestroy MostPopularFragment")
        this.disposeWhenDestroy()
        super.onDestroy()
    }

    // -----------------
    // ACTION
    // -----------------

    // Configure item click on RecyclerView
    private fun configureOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_item)
            .setOnItemClickListener(object : ItemClickSupport.OnItemClickListener {
                override fun onItemClicked(recyclerView: RecyclerView, position: Int, v: View) {
                    val intent = Intent(context, WebActivity::class.java)
                    intent.putExtra("openUrl", mMostPopularResults[position].url)
                    startActivity(intent)
                }
            })
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    // Configure RecyclerView, Adapter, LayoutManager & glue it together
    private fun configureMostPopularRecyclerView() {
        this.mMostPopularResults = ArrayList()
        this.mMostPopularAdapter = MostPopularAdapter(this.mMostPopularResults, Glide.with(this))
        mRecyclerView.adapter = this.mMostPopularAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        mRecyclerView.addItemDecoration(
            DividerItemDecoration(
                mRecyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }


    // Configure the SwipeRefreshLayout
    fun configureSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener { executeHttpMostPopularRequest() }
    }
    // -----------------
    // HTTP (RxJAVA)
    // -----------------

    // Execute our Stream
    private fun executeHttpMostPopularRequest() {
        // Execute the stream subscribing to Observable defined inside GithubStream
        this.mDisposable =
            NYTStreams.fetchMostPopular("all-sections").subscribeWith(object : DisposableObserver<MostPopular>() {
                override fun onNext(mostPopular: MostPopular) {
                    // Update RecyclerView after getting results from NYT API
                    mProgressBar.visibility = View.GONE
                    updateMostPopularUI(mostPopular.mostPopularResults)
                }

                override fun onError(e: Throwable) {
                    Log.e("MostPopular onError", Log.getStackTraceString(e))
                    Toast.makeText(context, "Une erreur est survenue ", Toast.LENGTH_LONG).show()
                }

                override fun onComplete() {
                    Log.e("MostPopular onComplete", "On Complete !!")

                }
            })
    }

    private fun disposeWhenDestroy() {
        if (this.mDisposable != null && !this.mDisposable!!.isDisposed) this.mDisposable!!.dispose()
    }

    // -----------------
    // UPDATE UI
    // -----------------

    private fun updateMostPopularUI(mostPopularResults: List<MostPopularResult>) {
        swipeRefreshLayout.isRefreshing = false
        mMostPopularResults.clear()
        mMostPopularResults.addAll(mostPopularResults)
        mMostPopularAdapter!!.notifyDataSetChanged()
    }

    companion object {

        fun newInstance(): MostPopularFragment {
            return MostPopularFragment()
        }
    }
}