package mx.ggl.atizappanmobile.ui.dashboard

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.ggl.atizappanmobile.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var binding:FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDashboardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        registrarEventos()
    }

    private fun registrarEventos() {
        binding.bomberos.setOnClickListener {
            llamarBomberos()
        }
        binding.seguridad.setOnClickListener {
            llamarSeguridad()
        }
        binding.emergencias.setOnClickListener {
            llamarEmergencias()
        }
        binding.forestal.setOnClickListener {
            llamarForestal()
        }
        binding.cruzRoja.setOnClickListener {
            llamarCruzRoja()
        }
        binding.derechos.setOnClickListener {
            llamarDerechos()
        }
        binding.caminos.setOnClickListener {
            llamarCaminos()
        }

        binding.infoBomberos.setOnClickListener {
            infoBomberos()
        }
        binding.infoCR.setOnClickListener {
            infoCR()
        }
        binding.infoCaminos.setOnClickListener {
            infoCaminos()
        }
        binding.infoDerechos.setOnClickListener {
            infoDerechos()
        }
        binding.infoForestal.setOnClickListener {
            infoForestal()
        }
        binding.infoEmergencias.setOnClickListener {
            infoEmergencias()
        }
        binding.infoSeguridad.setOnClickListener {
            infoSeguridad()
        }

    }

    private fun infoSeguridad() {
        val alert= AlertDialog.Builder(requireContext())
            .setTitle("Seguridad Publica")
            .setMessage("Tel: 55 3622 2730\nZempoala 5, Ignacio Lopez Rayon, 52986 Cd López Mateos, Méx.")
            .setNegativeButton("Llamar"){ _, _ ->
                llamarSeguridad()
            }
            .setNeutralButton("Cancelar"){_,_ ->

            }
            .setPositiveButton("Ubicación"){_, _ ->
                ubicacionSeguridad()
            }
        alert.show()
    }

    private fun infoEmergencias() {
        val alert= AlertDialog.Builder(requireContext())
            .setTitle("Emergencias Atizapan (C4)")
            .setMessage("Tel: 55 1106 2163 / 55 5366 7193\n52986, Zempoala 16, Ignacio Lopez Rayon, Cd López Mateos, Méx.")
            .setPositiveButton("Ubicación"){ _, _ ->
                ubicacionEmergencias()
            }
            .setNegativeButton("Llamar"){_,_ ->
                llamarEmergencias()
            }
            .setNeutralButton("Cancelar"){_, _ ->

            }
        alert.show()
    }

    private fun infoForestal() {
        val alert= AlertDialog.Builder(requireContext())
            .setTitle("Incendios forestales")
            .setMessage("Tel: 55 5554 0612 / 01 800 00 77 100")
            .setPositiveButton("Llamar"){ _, _ ->
                llamarForestal()
            }
            .setNeutralButton("Cancelar"){_,_ ->

            }
        alert.show()
    }

    private fun infoDerechos() {
        val alert= AlertDialog.Builder(requireContext())
            .setTitle("Comisión de Derechos humanos")
            .setMessage("Tel: 55 5229 5600")
            .setPositiveButton("Llamar"){ _, _ ->
                llamarDerechos()
            }
            .setNeutralButton("Cancelar"){_,_ ->

            }
        alert.show()
    }

    private fun infoCaminos() {
        val alert= AlertDialog.Builder(requireContext())
            .setTitle("Policia Federal de Caminos")
            .setMessage("Tel: 55 5684 2142 / 55 5684 9710")
            .setPositiveButton("Llamar"){ _, _ ->
                llamarCaminos()
            }
            .setNeutralButton("Cancelar"){_,_ ->

            }
        alert.show()
    }

    private fun infoCR() {
        val alert= AlertDialog.Builder(requireContext())
            .setTitle("Cruz Roja")
            .setMessage("Tel: 55 5822 2188\nCalz. S. Mateo 10, Prof Cristobal Higuera, 52940 Cd López Mateos, Méx.")
            .setNegativeButton("Llamar"){ _, _ ->
                llamarCruzRoja()
            }
            .setPositiveButton("Ubicación"){_, _ ->
                ubicacionCR()
            }
            .setNeutralButton("Cancelar"){_,_ ->

            }
        alert.show()
    }

    private fun infoBomberos() {
        val alert= AlertDialog.Builder(requireContext())
            .setTitle("Bomberos Atizapan")
            .setMessage("Tel: 55 3622 1004\n Municipio Libre 3, Lomas de Atizapan, 52977 Cd López Mateos, Méx.")
            .setNegativeButton("Llamar"){ _, _ ->
                llamarBomberos()
            }
            .setPositiveButton("Ubicación"){_, _ ->
                ubicacionBomberos()
            }
            .setNeutralButton("Cancelar"){_,_ ->

            }
        alert.show()
    }

    private fun llamarBomberos() {
        var intent = Intent (Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:5536221004"));
        startActivity(intent);
    }

    private fun llamarCaminos() {
        var intent = Intent (Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:5556842142"));
        startActivity(intent);
    }

    private fun llamarDerechos() {
        var intent = Intent (Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:5552295600"));
        startActivity(intent);
    }

    private fun llamarCruzRoja() {
        var intent = Intent (Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:5558222188"));
        startActivity(intent);
    }

    private fun llamarForestal() {
        var intent = Intent (Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:5555540612"));
        startActivity(intent);
    }

    private fun llamarEmergencias() {
        var intent = Intent (Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:5511062163"));
        startActivity(intent);
    }

    private fun llamarSeguridad() {
        var intent = Intent (Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:5536222730"));
        startActivity(intent);
    }

    private fun ubicacionSeguridad(){
        val uri = Uri.parse("geo:19.5961052,-99.2271223?q=Seguridad Pública Atizapán")
        val intMapa = Intent(Intent.ACTION_VIEW, uri)
        intMapa.setPackage("com.google.android.apps.maps")
        startActivity(intMapa)
    }

    private fun ubicacionBomberos(){
        val uri = Uri.parse("geo:19.5961052,-99.2271223?q=Bomberos Atizapán")
        val intMapa = Intent(Intent.ACTION_VIEW, uri)
        intMapa.setPackage("com.google.android.apps.maps")
        startActivity(intMapa)
    }

    private fun ubicacionCR(){
        val uri = Uri.parse("geo:19.5961052,-99.2271223?q=Cruz Roja Atizapán")
        val intMapa = Intent(Intent.ACTION_VIEW, uri)
        intMapa.setPackage("com.google.android.apps.maps")
        startActivity(intMapa)
    }

    private fun ubicacionEmergencias(){
        val uri = Uri.parse("geo:19.5961052,-99.2271223?q=C4 Atizapán")
        val intMapa = Intent(Intent.ACTION_VIEW, uri)
        intMapa.setPackage("com.google.android.apps.maps")
        startActivity(intMapa)
    }
}