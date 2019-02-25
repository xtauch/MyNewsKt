package tauch.xavier.mynewskt.Models.ArticleSearch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Person {

    @SerializedName("organization")
    @Expose
    var organization: String? = null
    @SerializedName("role")
    @Expose
    var role: String? = null
    @SerializedName("rank")
    @Expose
    var rank: Int? = null
    @SerializedName("firstname")
    @Expose
    var firstname: String? = null
    @SerializedName("middlename")
    @Expose
    var middlename: String? = null
    @SerializedName("lastname")
    @Expose
    var lastname: String? = null

}