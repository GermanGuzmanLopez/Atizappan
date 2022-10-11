package mx.ggl.atizappanmobile.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.ggl.atizappanmobile.model.ListaNoticias
import mx.ggl.atizappanmobile.model.Noticia
import mx.ggl.atizappanmobile.model.ServicioNoticiasAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListaNoticiasVM: ViewModel() {
    //Modelo
    private val retrofit by lazy { //El objeto que se conecta a la red y accede a los servicios definidos
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //objeto para descargar info
    private val servicioNoticiasAPI by lazy {
        retrofit.create(ServicioNoticiasAPI::class.java)
    }

    val listaNoticias = MutableLiveData<List<Noticia>>()

    //Interfaz del viewModel
    fun descargarDatosNoticias() {
        val call = servicioNoticiasAPI.descargarDatosPaises()
        call.enqueue(object: Callback<ListaNoticias> {
            override fun onResponse(call: Call<ListaNoticias>, response: Response<ListaNoticias>) {
                if (response.isSuccessful) {
                    println("Lista de noticias: ${response.body()?.articulos}")
                    // Avisar a la vista (adaptador) que hay datos nuevos
                    listaNoticias.value = response.body()?.articulos

                } else {
                    println("Error en los datos: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ListaNoticias>, t: Throwable) {
                println("Error en la descarga: ${t.localizedMessage}")
            }
        })
    }
}