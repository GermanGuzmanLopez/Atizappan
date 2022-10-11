package mx.ggl.atizappanmobile.model

import com.google.gson.annotations.SerializedName

data class ListaNoticias(
    @SerializedName("articles")
    val articulos: List<Noticia>
)
