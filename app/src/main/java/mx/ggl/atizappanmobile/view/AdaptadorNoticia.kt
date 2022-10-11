package mx.ggl.atizappanmobile.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.ggl.atizappanmobile.R
import mx.ggl.atizappanmobile.model.Noticia

class AdaptadorNoticia (val context: Context,
                        var arrNoticias: List<Noticia>)
    : RecyclerView.Adapter<AdaptadorNoticia.RenglonNoticia>()
{
    class RenglonNoticia(var renglonNoticia: View) : RecyclerView.ViewHolder(renglonNoticia)
    {
        fun set(noticia: Noticia) {
            val tvTitulo = renglonNoticia.findViewById<TextView>(R.id.tvTitulo)
            //val url = renglonNoticia.findViewById<Button>(R.id.masbtn)
            //println(tvTitulo)
            //val tvDescripcion = renglonNoticia.findViewById<TextView>(R.id.tvDescripcion)
            val imgNoticia = renglonNoticia.findViewById<ImageView>(R.id.imgNoticia)
            tvTitulo.setText(noticia.titulo)
            //println("titulo")
            //println(noticia.titulo)
            //tvDescripcion.setText(noticia.descripcion)
            // Descargar la bandera y ponerla en imgBandera
            val url = noticia.imagen
            //println(url) //Nunca en producción
            Glide.with(renglonNoticia.context).load(url).into(imgNoticia);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RenglonNoticia {
        val vista = LayoutInflater.from(context)
            .inflate(R.layout.renglonnoticia, parent, false)
        return RenglonNoticia(vista)
    }

    override fun onBindViewHolder(holder: RenglonNoticia, position: Int) {
        val noticia = arrNoticias[position]
        holder.set(noticia)
        val boton = holder.renglonNoticia.findViewById<Button>(R.id.masbtn)
        boton.setOnClickListener{
            println("Click en $position")
            val uri = Uri.parse(arrNoticias[position].link)
            val intNoticia = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intNoticia)
        }


    }

    override fun getItemCount(): Int {
        return arrNoticias.size
    }


}