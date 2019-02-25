package tauch.xavier.mynewskt.Models.ArticleSearch

/**
 * Created by Xavier TAUCH on 08/04/2018.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleSearch {

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("copyright")
    @Expose
    var copyright: String? = null

    @SerializedName("response")
    @Expose
    lateinit var response: Response
}