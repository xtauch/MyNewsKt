package tauch.xavier.mynewskt.Models.ArticleSearch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response {

    @SerializedName("meta")
    @Expose
    var meta: Meta? = null
    @SerializedName("docs")
    @Expose
    lateinit var docs: List<Doc>
    @SerializedName("facets")
    @Expose
    var facets: Facets? = null

}