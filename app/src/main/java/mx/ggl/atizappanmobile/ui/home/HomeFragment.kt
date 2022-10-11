package mx.ggl.atizappanmobile.ui.home

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
import mx.ggl.atizappanmobile.model.Noticia
import mx.ggl.atizappanmobile.view.AdaptadorNoticia
import mx.ggl.atizappanmobile.viewModel.ListaNoticiasVM

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    // viewmodel
    private val viewModel : ListaNoticiasVM by viewModels()

    // adaptador
    private lateinit var adaptador : AdaptadorNoticia

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = FragmentHomeBinding.inflate(layoutInflater)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarRV()
        configurarObservables()
    }

    private fun configurarRV() {
        val arrNoticias = listOf(Noticia("","","",""))
        val layout = LinearLayoutManager(requireContext())
        //Ya no se declara, se usa la variable de instancia
        adaptador = AdaptadorNoticia(requireContext(), arrNoticias)
        binding.rvNoticias.adapter = adaptador
        binding.rvNoticias.layoutManager = layout

        // separador
        val separador = DividerItemDecoration(requireContext(), layout.orientation)
        binding.rvNoticias.addItemDecoration(separador)

    }

    private fun configurarObservables() {
        viewModel.listaNoticias.observe(viewLifecycleOwner){lista ->
            val arrNoticia = lista.toList()
            adaptador.arrNoticias = arrNoticia //cambiamos la fuente de datos
            adaptador.notifyDataSetChanged() //Recarga los datos
        }
    }

    override fun onStart(){
        super.onStart()
        viewModel.descargarDatosNoticias()
    }
}