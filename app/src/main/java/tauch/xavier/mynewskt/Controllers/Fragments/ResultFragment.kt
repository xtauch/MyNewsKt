package tauch.xavier.mynewskt.Controllers.Fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.bumptech.glide.Glide
import tauch.xavier.mynewskt.Adapters.ResultAdapter
import tauch.xavier.mynewskt.Controllers.Activities.SearchActivity
import tauch.xavier.mynewskt.Controllers.Activities.WebActivity
import tauch.xavier.mynewskt.Models.ArticleSearch.ArticleSearch
import tauch.xavier.mynewskt.Models.ArticleSearch.Doc
import tauch.xavier.mynewskt.R
import tauch.xavier.mynewskt.Utils.ItemClickSupport

import tauch.xavier.mynewskt.Utils.NYTStreams
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.fragment_result.*
import tauch.xavier.mynewskt.BuildConfig

import java.util.ArrayList
import java.util.Timer
import java.util.TimerTask

class ResultFragment : Fragment() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    // FOR DATA
    private var mDisposable: Disposable? = null
    private var mDocs: MutableList<Doc> = ArrayList()
    private var mResultAdapter: ResultAdapter? = null

    lateinit var query: String
    lateinit var beginDate: String
    lateinit var endDate: String
    lateinit var checkboxes: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val result = inflater.inflate(R.layout.fragment_result, container, false)

        mRecyclerView = result.findViewById(R.id.fragment_result_recycler_view) as RecyclerView
        swipeRefreshLayout = result.findViewById(R.id.fragment_result_swipe_container) as SwipeRefreshLayout

        this.configureResultRecyclerView()
        this.executeHttpResultRequest()
        this.configureSwipeRefreshLayout()
        this.configureOnClickRecyclerView()

        return result
    }

    override fun onDestroy() {
        this.disposeWhenDestroy()
        super.onDestroy()
    }

    // -----------------
    // ACTION
    // -----------------

    // Configure click on RecyclerView's item
    private fun configureOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_item)
            .setOnItemClickListener(object : ItemClickSupport.OnItemClickListener {
                override fun onItemClicked(recyclerView: RecyclerView, position: Int, v: View) {
                    val intent = Intent(context, WebActivity::class.java)
                    intent.putExtra("openUrl", mDocs[position].webUrl)
                    startActivity(intent)
                }
            })
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    // Configure RecyclerView, Adapter, LayoutManager & glue it together
    private fun configureResultRecyclerView() {
        this.mDocs = ArrayList()
        this.mResultAdapter = ResultAdapter(this.mDocs, Glide.with(this))
        this.mRecyclerView.adapter = this.mResultAdapter
        this.mRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun configureSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener { executeHttpResultRequest() }
    }


    // -----------------
    // HTTP (RxJAVA)
    // -----------------

    // Execute our Stream
    private fun executeHttpResultRequest() {
        query = arguments!!.getString("query")
        beginDate = arguments!!.getString("beginDate")
        endDate = arguments!!.getString("endDate")
        checkboxes = arguments!!.getString("checkboxes")
        Log.e("httpResultRequest", "$query $beginDate $endDate $checkboxes")
        // Execute the stream subscribing to Observable defined inside NYTStream
        this.mDisposable =
            NYTStreams.fetchArticleSearch(query, beginDate, endDate, checkboxes, BuildConfig.NYTApiKey)
                .subscribeWith(object : DisposableObserver<ArticleSearch>() {

                    override fun onNext(articleSearch: ArticleSearch) {
                        Log.e("ArtSearch - onNext", "On Next TopStoriesFragment")
                        // Update RecyclerView after getting results from NYT API
                        updateResultUI(articleSearch.response.docs)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("ArtSearch - onError", Log.getStackTraceString(e))
                        Toast.makeText(context, "Une erreur est survenue ", Toast.LENGTH_LONG).show()
                    }

                    override fun onComplete() {
                        Log.e("ArtSearch - onComplete", "On Complete TopStoriesFragment")

                    }
                })
    }

    private fun disposeWhenDestroy() {
        if (this.mDisposable != null && !this.mDisposable!!.isDisposed) this.mDisposable!!.dispose()
    }

    // -----------------
    // UPDATE UI
    // -----------------

    // Stop refreshing and clear actual list of users to add all the new ones
    fun updateResultUI(docs: List<Doc>) {
        swipeRefreshLayout.isRefreshing = false
        mDocs.clear()
        if (docs.isEmpty()) {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage("There's no result for this search \n You will be redirect automatically in 5 seconds")
                .show()
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    endSearchResult()
                }
            }, 0)

        } else {
            mDocs.addAll(docs)
            mResultAdapter!!.notifyDataSetChanged()
        }
    }

    fun endSearchResult() {
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        startActivity(Intent(activity, SearchActivity::class.java))
    }

    companion object {

        fun newInstance(): ResultFragment {
            return ResultFragment()
        }
    }

}