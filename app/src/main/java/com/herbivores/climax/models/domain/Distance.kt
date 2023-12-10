package com.herbivores.climax.models.domain

data class Distance(val meters: Int) {
    val kilometers = meters / 1000

    override fun toString() = if (kilometers < 1) "$meters m" else "$kilometers km"
}

val Int.meters get() = Distance(this)