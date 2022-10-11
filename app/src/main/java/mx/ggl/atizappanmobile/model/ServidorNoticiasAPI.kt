package mx.ggl.atizappanmobile.model

import retrofit2.Call
import retrofit2.http.GET

interface ServicioNoticiasAPI {
    @GET("v2/top-headlines?country=mx&apiKey=65e66e02c2b04cec94ac840819fc7806")
    fun descargarDatosPaises(): Call<ListaNoticias>
}