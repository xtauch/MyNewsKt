package tauch.xavier.mynewskt.Models.ArticleSearch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Legacy {

    @SerializedName("hasthumbnail")
    @Expose
    var hasthumbnail: String? = null
    @SerializedName("thumbnailheight")
    @Expose
    var thumbnailheight: Int? = null
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null
    @SerializedName("xlargewidth")
    @Expose
    var xlargewidth: Int? = null
    @SerializedName("xlargeheight")
    @Expose
    var xlargeheight: Int? = null
    @SerializedName("xlarge")
    @Expose
    var xlarge: String? = null
    @SerializedName("hasxlarge")
    @Expose
    var hasxlarge: String? = null

}