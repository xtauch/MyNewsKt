package tauch.xavier.mynewskt.Models.MostPopular

/**
 * Created by Xavier TAUCH on 08/04/2018.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MostPopular {

    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("copyright")
    @Expose
    var copyright: String? = null
    @SerializedName("num_results")
    @Expose
    var numResults: Int? = null
    @SerializedName("results")
    @Expose
    lateinit var mostPopularResults: List<MostPopularResult>
}