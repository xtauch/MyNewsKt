package tauch.xavier.mynewskt.Models.ArticleSearch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Facets {

    @SerializedName("day_of_week")
    @Expose
    var dayOfWeek: DayOfWeek? = null

}