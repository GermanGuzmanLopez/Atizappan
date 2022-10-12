package mx.ggl.atizappanmobile.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.ggl.atizappanmobile.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListaAlertasVM: ViewModel() {
    //Modelo
    private val retrofit by lazy { //El objeto que se conecta a la red y accede a los servicios definidos
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //objeto para descargar info
    private val servidorAlertasAPI by lazy {
        retrofit.create(ServidorAlertasAPI::class.java)
    }

    val listaAlertas = MutableLiveData<List<Alerta>>()

    //Interfaz del viewModel
    fun descargarDatosAlertas() {
        val call = servidorAlertasAPI.descargarDatosAlerta()
        call.enqueue(object: Callback<ListaAlertas> {
            override fun onResponse(call: Call<ListaAlertas>, response: Response<ListaAlertas>) {
                if (response.isSuccessful) {
                    println("Lista de noticias: ${response.body()?.contenido}")
                    // Avisar a la vista (adaptador) que hay datos nuevos
                    listaAlertas.value = response.body()?.contenido

                } else {
                    println("Error en los datos: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ListaAlertas>, t: Throwable) {
                println("Error en la descarga: ${t.localizedMessage}")
            }
        })
    }
}