package tauch.xavier.mynewskt.Models.ArticleSearch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Term {

    @SerializedName("term")
    @Expose
    var term: String? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null

}