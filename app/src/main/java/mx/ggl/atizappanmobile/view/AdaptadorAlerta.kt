package mx.ggl.atizappanmobile.view

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import mx.ggl.atizappanmobile.R
import mx.ggl.atizappanmobile.model.Alerta
import java.io.File

class AdapatorAlerta(private val alertaList: ArrayList<Alerta>) : RecyclerView.Adapter<AdapatorAlerta.MyViewHolder>()
{
    class MyViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView){
        fun set(alerta: Alerta){
            val titulo : TextView = itemView.findViewById(R.id.tvAlerta)
            val descripcion: TextView = itemView.findViewById(R.id.tvDescripcionAlerta)
            val fecha: TextView = itemView.findViewById(R.id.tvFecha)
            val prioridad: TextView = itemView.findViewById(R.id.tvPrioridad)
            val imageView: ImageView = itemView.findViewById(R.id.imgAlerta)

            titulo.text = alerta.titulo
            descripcion.text = alerta.descripcion
            fecha.text = alerta.fecha
            prioridad.text = alerta.prioridad

            val storageRef = FirebaseStorage.getInstance().reference.child("images/${alerta.img}")

            val localFile = File.createTempFile("tempImage", "png")
            storageRef.getFile(localFile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                imageView.setImageBitmap(bitmap)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):AdapatorAlerta.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.renglon_alerta,
            parent,false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: AdapatorAlerta.MyViewHolder, position: Int){
        val alerta : Alerta = alertaList[position]
        holder.set(alerta)
    }
    override fun getItemCount(): Int {
        return alertaList.size
    }
}