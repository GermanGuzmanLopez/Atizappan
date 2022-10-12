package mx.ggl.atizappanmobile.model

import com.bumptech.glide.load.engine.Resource
import com.google.gson.annotations.SerializedName

data class Alerta(
    @SerializedName("title")
    val encabezado: String,
    @SerializedName("description")
    val descripcion: String,
    @SerializedName("urlToImage")
    val imagen: String,
    @SerializedName("publishedAt")
    val fecha: String,
    @SerializedName("url")
    val prioridad: String
)
