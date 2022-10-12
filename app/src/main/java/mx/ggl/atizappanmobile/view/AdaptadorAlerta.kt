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
import mx.ggl.atizappanmobile.model.Alerta
import mx.ggl.atizappanmobile.model.Noticia

class AdaptadorAlerta (val context: Context,
                       var arrAlertas: List<Alerta>)
    : RecyclerView.Adapter<AdaptadorAlerta.RenglonAlerta>()
{
    class RenglonAlerta(var renglonAlerta: View) : RecyclerView.ViewHolder(renglonAlerta)
    {
        fun set(alerta: Alerta) {
            val tvAlerta = renglonAlerta.findViewById<TextView>(R.id.tvAlerta)
            val tvDescripcionAlerta = renglonAlerta.findViewById<TextView>(R.id.tvDescripcionAlerta)
            val imgAlerta = renglonAlerta.findViewById<ImageView>(R.id.imgAlerta)
            val tvFecha = renglonAlerta.findViewById<TextView>(R.id.tvFecha)
            val tvPrioridad = renglonAlerta.findViewById<TextView>(R.id.tvPrioridad)
            tvAlerta.setText(alerta.encabezado)
            tvFecha.setText(alerta.fecha)
            tvPrioridad.setText(alerta.prioridad)
            tvDescripcionAlerta.setText(alerta.descripcion)

            // Descargar la bandera y ponerla en imgBandera
            val url = alerta.imagen

            Glide.with(renglonAlerta.context).load(url).into(imgAlerta);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RenglonAlerta {
       val vista = LayoutInflater.from(context)
            .inflate(R.layout.renglon_alerta, parent, false)
        return RenglonAlerta(vista)
    }

    override fun onBindViewHolder(holder: RenglonAlerta, position: Int) {
        val alerta = arrAlertas[position]
        holder.set(alerta)
        /**val boton = holder.renglonAlerta.findViewById<Button>(R.id.imgAlerta)
        boton.setOnClickListener{
            println("Click en $position")
            val uri = Uri.parse(arrAlertas[position].descripcion)
            val intAlerta = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intAlerta)**/
        }




    override fun getItemCount(): Int {
        return arrAlertas.size
    }


}