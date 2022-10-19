package mx.ggl.atizappanmobile.model

data class Alerta(
    val titulo: String ?= null,
    val descripcion: String ?= null,
    val fecha: String ?= null,
    val prioridad: String ?= null,
    val img: String ?= null
)
