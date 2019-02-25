package tauch.xavier.mynewskt.Models.ArticleSearch

/**
 * Created by Xavier TAUCH on 19/04/2018.
 */
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Headline {

    @SerializedName("main")
    @Expose
    var main: String? = null
    @SerializedName("kicker")
    @Expose
    var kicker: String? = null

}