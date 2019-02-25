package tauch.xavier.mynewskt.Models.ArticleSearch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Keyword {

    @SerializedName("rank")
    @Expose
    var rank: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("value")
    @Expose
    var value: String? = null

}