package tauch.xavier.mynewskt.Models.ArticleSearch


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Doc {

    @SerializedName("web_url")
    @Expose
    var webUrl: String? = null
    @SerializedName("snippet")
    @Expose
    var snippet: String? = null
    @SerializedName("lead_paragraph")
    @Expose
    var leadParagraph: String? = null
    @SerializedName("abstract")
    @Expose
    var abstract: String? = null
    @SerializedName("print_page")
    @Expose
    var printPage: String? = null
    @SerializedName("source")
    @Expose
    var source: String? = null
    @SerializedName("multimedia")
    @Expose
    lateinit var multimedia: List<Multimedium>
    @SerializedName("headline")
    @Expose
    lateinit var headline: Headline
    @SerializedName("keywords")
    @Expose
    var keywords: List<Keyword>? = null
    @SerializedName("pub_date")
    @Expose
    lateinit var pubDate: String
    @SerializedName("document_type")
    @Expose
    var documentType: String? = null
    @SerializedName("news_desk")
    @Expose
    var newsDesk: String? = null
    @SerializedName("section_name")
    @Expose
    var sectionName: String? = null
    @SerializedName("byline")
    @Expose
    var byline: Byline? = null
    @SerializedName("word_count")
    @Expose
    var wordCount: Int? = null
    @SerializedName("type_of_material")
    @Expose
    var typeOfMaterial: String? = null
    @SerializedName("_id")
    @Expose
    var id: String? = null

}