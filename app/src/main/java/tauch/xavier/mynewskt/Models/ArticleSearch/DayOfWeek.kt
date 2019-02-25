package tauch.xavier.mynewskt.Models.ArticleSearch


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DayOfWeek {

    @SerializedName("_type")
    @Expose
    var type: String? = null
    @SerializedName("missing")
    @Expose
    var missing: Int? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null
    @SerializedName("other")
    @Expose
    var other: Int? = null
    @SerializedName("terms")
    @Expose
    var terms: List<Term>? = null
}