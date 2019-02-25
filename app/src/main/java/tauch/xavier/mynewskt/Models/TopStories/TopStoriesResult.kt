package tauch.xavier.mynewskt.Models.TopStories

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TopStoriesResult {

    @SerializedName("section")
    @Expose
    var section: String? = null
    @SerializedName("subsection")
    @Expose
    lateinit var subsection: String
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("abstract")
    @Expose
    var abstract: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("byline")
    @Expose
    var byline: String? = null
    @SerializedName("item_type")
    @Expose
    var itemType: String? = null
    @SerializedName("updated_date")
    @Expose
    var updatedDate: String? = null
    @SerializedName("created_date")
    @Expose
    lateinit var createdDate: String
    @SerializedName("published_date")
    @Expose
    var publishedDate: String? = null
    @SerializedName("material_type_facet")
    @Expose
    var materialTypeFacet: String? = null
    @SerializedName("kicker")
    @Expose
    var kicker: String? = null
    @SerializedName("des_facet")
    @Expose
    var desFacet: List<String>? = null
    @SerializedName("org_facet")
    @Expose
    var orgFacet: List<String>? = null
    @SerializedName("per_facet")
    @Expose
    var perFacet: List<Any>? = null
    @SerializedName("geo_facet")
    @Expose
    var geoFacet: List<Any>? = null
    @SerializedName("multimedia")
    @Expose
    lateinit var multimedia: List<TopStoriesMultimedium>
    @SerializedName("short_url")
    @Expose
    var shortUrl: String? = null

}
