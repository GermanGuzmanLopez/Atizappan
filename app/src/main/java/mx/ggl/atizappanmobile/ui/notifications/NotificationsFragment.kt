package mx.ggl.atizappanmobile.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mx.ggl.atizappanmobile.databinding.FragmentHomeBinding
import mx.ggl.atizappanmobile.databinding.FragmentNotificationsBinding
import mx.ggl.atizappanmobile.model.Alerta
import mx.ggl.atizappanmobile.model.ListaAlertas
import mx.ggl.atizappanmobile.model.Noticia
import mx.ggl.atizappanmobile.view.AdaptadorAlerta
import mx.ggl.atizappanmobile.view.AdaptadorNoticia
import mx.ggl.atizappanmobile.viewModel.ListaAlertasVM
import mx.ggl.atizappanmobile.viewModel.ListaNoticiasVM

class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding

    private val viewModel : ListaAlertasVM by viewModels()

    //Adaptador
    private lateinit var adaptador : AdaptadorAlerta

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        binding = FragmentNotificationsBinding.inflate(layoutInflater)
        val root: View = binding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarRVA()
        configurarObservablesA()
    }

    private fun configurarRVA() {
        val arrAlertas = listOf(Alerta("Amenaza virtual de tiroteo","Desalojo de la Preparatoria Oficial No.64 tras amenaza de tiroteo.","https://images.milenio.com/X2j3DLaeLo_ksLL0dRU6tsroF1M=/958x596/uploads/media/2022/09/07/las-publicaciones-fueron-posteriormente-eliminadas.jpg","07.09.2022","Alta"))
        val layout = LinearLayoutManager(requireContext())
        //Ya no se declara, se usa la variable de instancia
        adaptador = AdaptadorAlerta(requireContext(), arrAlertas)
        binding.rvAlertas.adapter = adaptador
        binding.rvAlertas.layoutManager = layout

        // separador
        val separador = DividerItemDecoration(requireContext(), layout.orientation)
        binding.rvAlertas.addItemDecoration(separador)

    }

    private fun configurarObservablesA() {
        viewModel.listaAlertas.observe(viewLifecycleOwner){lista2 ->
            val arrAlerta = lista2.toList()
            adaptador.arrAlertas = arrAlerta //cambiamos la fuente de datos
            //adaptador.notifyDataSetChanged() //Recarga los datos
        }
    }

    override fun onStart(){
        super.onStart()
        //viewModel.descargarDatosAlertas()
    }
}