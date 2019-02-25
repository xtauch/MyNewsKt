package tauch.xavier.mynewskt.Models.ArticleSearch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meta {

    @SerializedName("hits")
    @Expose
    var hits: Int? = null
    @SerializedName("time")
    @Expose
    var time: Int? = null
    @SerializedName("offset")
    @Expose
    var offset: Int? = null

}