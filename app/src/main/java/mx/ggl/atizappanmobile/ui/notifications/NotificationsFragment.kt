package mx.ggl.atizappanmobile.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import mx.ggl.atizappanmobile.databinding.FragmentHomeBinding
import mx.ggl.atizappanmobile.databinding.FragmentNotificationsBinding
import mx.ggl.atizappanmobile.model.Alerta
import mx.ggl.atizappanmobile.model.ListaAlertas
import mx.ggl.atizappanmobile.model.Noticia
import mx.ggl.atizappanmobile.view.AdapatorAlerta
import mx.ggl.atizappanmobile.view.AdaptadorNoticia
import mx.ggl.atizappanmobile.viewModel.ListaAlertasVM
import mx.ggl.atizappanmobile.viewModel.ListaNoticiasVM

class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private val viewModel : ListaAlertasVM by viewModels()

    private lateinit var alertaArrayList: ArrayList<Alerta>
    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView : RecyclerView
    private lateinit var adaptador : AdapatorAlerta

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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
        alertaArrayList = arrayListOf()
        val layout = LinearLayoutManager(requireContext())
        //Ya no se declara, se usa la variable de instancia
        adaptador = AdapatorAlerta(alertaArrayList)

        binding.rvAlertas.adapter = adaptador
        binding.rvAlertas.layoutManager = layout

        // separador
        val separador = DividerItemDecoration(requireContext(), layout.orientation)
        binding.rvAlertas.addItemDecoration(separador)

    }

    private fun configurarObservablesA() {

        db = FirebaseFirestore.getInstance()
        db.collection("Notificaciones").orderBy("fecha", Query.Direction.DESCENDING)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if(error != null) {
                        Log.e("Firestore Error", error.message.toString())
                        return
                    }
                    for(dc : DocumentChange in value?.documentChanges!!) {
                        if(dc.type == DocumentChange.Type.ADDED){
                            alertaArrayList.add(dc.document.toObject(Alerta::class.java))
                        }
                    }
                    adaptador.notifyDataSetChanged()
                }
            })
    }

    override fun onStart(){
        super.onStart()
        //viewModel.descargarDatosAlertas()
    }
}