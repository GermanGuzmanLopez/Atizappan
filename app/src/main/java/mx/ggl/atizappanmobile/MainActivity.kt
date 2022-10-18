package mx.ggl.atizappanmobile

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import mx.ggl.atizappanmobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.banner.infoApp.setOnClickListener(){
            val alert= AlertDialog.Builder(this)
                .setTitle("Créditos")
                .setMessage("Atizappán es una aplicación desarrollada por:\n-Isabel Vieyra\n-Germán Guzmán\n-Yahir Cortéz\n-Naomi Anciola\n-Fernando Emilio Nava\n" +
                        "\nEn colaboración con el municipio de Atizapán de Zaragoza y Protección Civíl del Estado de México\n" +
                        "\nContacto: A01745860@tec.mx")
                .setPositiveButton("Aceptar"){_, _ ->
                }
            alert.show()
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )


//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }



}