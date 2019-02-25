package tauch.xavier.mynewskt.Views


import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.fragment_item.view.*

import tauch.xavier.mynewskt.Models.TopStories.TopStoriesResult
import tauch.xavier.mynewskt.R

class TopStoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun updateWithTopStories(topStoriesResult: TopStoriesResult, glide: RequestManager) {
      itemView.fragment_page_item_title.setText(topStoriesResult.title)
      itemView.fragment_page_item_date!!.setText(topStoriesResult.createdDate.substring(0, 10))
        if (!topStoriesResult.subsection.isEmpty()) {
            itemView.fragment_page_item_section!!.setText(topStoriesResult.section + " > " + topStoriesResult.subsection)
        } else {
            itemView.fragment_page_item_section!!.setText(topStoriesResult.section)
        }
        if (topStoriesResult.multimedia.size !== 0) {
            glide.load(topStoriesResult.multimedia.get(0).url)
                .into(itemView.fragment_page_item_image)
        } else {
            itemView.fragment_page_item_image!!.setImageResource(R.drawable.ic_menu_gallery)
        }


    }
}