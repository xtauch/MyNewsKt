package tauch.xavier.mynewskt.Views

import android.support.v7.widget.RecyclerView
import android.view.View

import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.fragment_item.view.*
import tauch.xavier.mynewskt.Models.MostPopular.MostPopularResult
import tauch.xavier.mynewskt.R


class MostPopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun updateWithMostPopular(mostPopularResult: MostPopularResult, glide: RequestManager) {
        itemView.fragment_page_item_title.text = mostPopularResult.title
        itemView.fragment_page_item_date!!.setText(mostPopularResult.publishedDate.substring(0, 10))
        itemView.fragment_page_item_section!!.setText(mostPopularResult.section)
        if (mostPopularResult.multimedia.size !== 0) {
            glide.load(mostPopularResult.multimedia.get(0).mediaMetadata.get(0).url)
                .into(itemView.fragment_page_item_image)
        } else {
            itemView.fragment_page_item_image!!.setImageResource(R.drawable.ic_menu_gallery)
        }
    }
}