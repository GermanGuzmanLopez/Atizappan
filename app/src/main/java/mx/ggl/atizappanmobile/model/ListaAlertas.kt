package mx.ggl.atizappanmobile.model

import com.google.gson.annotations.SerializedName

data class ListaAlertas(
    @SerializedName("articles")
    val contenido: List<Alerta>
)
