package tauch.xavier.mynewskt.Adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import tauch.xavier.mynewskt.R
import tauch.xavier.mynewskt.Views.TopStoriesViewHolder
import tauch.xavier.mynewskt.Models.TopStories.TopStoriesResult


class TopStoriesAdapter// CONSTRUCTOR
    (// FOR DATA
    private val mTopStoriesResults: List<TopStoriesResult>, private val glide: RequestManager
) : RecyclerView.Adapter<TopStoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopStoriesViewHolder {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_item, parent, false)

        return TopStoriesViewHolder(view)
    }

    // UPDATE VIEW HOLDER WITH A TOP STORY
    override fun onBindViewHolder(viewHolder: TopStoriesViewHolder, position: Int) {
        viewHolder.updateWithTopStories(this.mTopStoriesResults[position], this.glide)
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    override fun getItemCount(): Int {
        return this.mTopStoriesResults.size
    }
}