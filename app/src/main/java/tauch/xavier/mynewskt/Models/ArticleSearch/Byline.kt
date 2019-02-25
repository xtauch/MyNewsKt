package tauch.xavier.mynewskt.Models.ArticleSearch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Byline {

    @SerializedName("person")
    @Expose
    var person: List<Person>? = null
    @SerializedName("original")
    @Expose
    var original: String? = null

}