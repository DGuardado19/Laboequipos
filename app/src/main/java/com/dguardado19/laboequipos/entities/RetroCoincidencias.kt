package com.dguardado19.laboequipos.entities

import com.squareup.moshi.Json

data class RetroCoincidencias(
    @field:Json(name = "Response")
    var Respuesta: String,
    @field:Json(name = "Search")
    var Search: List<Movie>,
    @field:Json(name="Results")
    var Results : String

)