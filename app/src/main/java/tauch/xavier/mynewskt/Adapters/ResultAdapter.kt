package tauch.xavier.mynewskt.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import tauch.xavier.mynewskt.Models.ArticleSearch.Doc
import tauch.xavier.mynewskt.R
import tauch.xavier.mynewskt.Views.ResultViewHolder

class ResultAdapter(// FOR DATA
    private val mDocs: List<Doc>, private val glide: RequestManager
) : RecyclerView.Adapter<ResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        // Create view holder and inflating its xml layout
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_item, parent, false)

        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.updateWithResult(this.mDocs[position], this.glide)
    }

    override fun getItemCount(): Int {
        return this.mDocs.size
    }
}