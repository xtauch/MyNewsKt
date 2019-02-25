package tauch.xavier.mynewskt.Adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import tauch.xavier.mynewskt.Models.MostPopular.MostPopularResult
import tauch.xavier.mynewskt.R
import tauch.xavier.mynewskt.Views.MostPopularViewHolder

/**
 * Created by Xavier TAUCH on 19/04/2018.
 */
class MostPopularAdapter(// FOR DATA
    private val mMostPopularResults: List<MostPopularResult>, private val glide: RequestManager
) : RecyclerView.Adapter<MostPopularViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostPopularViewHolder {
        // Create view holder and inflating its xml layout
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_item, parent, false)

        return MostPopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: MostPopularViewHolder, position: Int) {
        holder.updateWithMostPopular(this.mMostPopularResults[position], this.glide)
    }

    override fun getItemCount(): Int {
        return this.mMostPopularResults.size
    }
}