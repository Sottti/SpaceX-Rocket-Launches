package com.spacex.launches.domain

class RocketDM(
        id: Int,
        val stringId : String,
        val name: String,
        val country: String,
        val enginesCount: String,
        val isActive: Boolean
) : BaseDM() {
    init {
        this.id = id
    }
}
