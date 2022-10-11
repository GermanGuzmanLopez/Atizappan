package mx.ggl.atizappanmobile.model

import com.google.gson.annotations.SerializedName

data class Noticia(
    @SerializedName("title")
    val titulo: String,
    @SerializedName("description")
    val descripcion: String,
    @SerializedName("urlToImage")
    val imagen: String,
    @SerializedName("url")
    val link: String
)
