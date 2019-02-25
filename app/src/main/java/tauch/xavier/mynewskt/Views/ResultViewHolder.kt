package tauch.xavier.mynewskt.Views

import android.support.v7.widget.RecyclerView
import android.view.View

import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.fragment_item.view.*
import tauch.xavier.mynewskt.Models.ArticleSearch.Doc
import tauch.xavier.mynewskt.R


class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun updateWithResult(doc: Doc, glide: RequestManager) {
        itemView.fragment_page_item_title!!.setText(doc.headline.main)
        itemView.fragment_page_item_date!!.setText(doc.pubDate.substring(0, 10))
        itemView.fragment_page_item_section!!.setText(doc.sectionName)
        if (doc.multimedia.size !== 0) {
            glide.load("https://www.nytimes.com/" + doc.multimedia.get(0).url)
                .into(itemView.fragment_page_item_image)
        } else {
            itemView.fragment_page_item_image!!.setImageResource(R.drawable.ic_menu_gallery)
        }
    }
}