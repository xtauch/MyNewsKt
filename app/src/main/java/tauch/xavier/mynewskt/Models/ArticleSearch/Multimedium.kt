package tauch.xavier.mynewskt.Models.ArticleSearch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Multimedium {

    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("subtype")
    @Expose
    var subtype: String? = null
    @SerializedName("legacy")
    @Expose
    var legacy: Legacy? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null

}