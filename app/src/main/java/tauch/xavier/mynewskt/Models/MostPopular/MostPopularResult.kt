package tauch.xavier.mynewskt.Models.MostPopular

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MostPopularResult {

    @SerializedName("url")
    @Expose
    var url: String? = null//
    @SerializedName("adx_keywords")
    @Expose
    var adxKeywords: String? = null//
    @SerializedName("column")
    @Expose
    var column: String? = null//
    @SerializedName("section")
    @Expose
    var section: String? = null//
    @SerializedName("byline")
    @Expose
    var byline: String? = null//
    @SerializedName("type")
    @Expose
    var type: String? = null//
    @SerializedName("title")
    @Expose
    var title: String? = null//
    @SerializedName("abstract")
    @Expose
    var abstract: String? = null//
    @SerializedName("published_date")
    @Expose
    lateinit var publishedDate: String//
    @SerializedName("source")
    @Expose
    var source: String? = null//
    @SerializedName("id")
    @Expose
    var id: Long? = null//
    @SerializedName("asset_id")
    @Expose
    var assetId: Long? = null
    @SerializedName("views")
    @Expose
    var views: Int? = null
    @SerializedName("media")
    @Expose
    lateinit var multimedia: List<MostPopularMedium>
}