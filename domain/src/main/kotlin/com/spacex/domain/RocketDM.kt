package com.spacex.domain

class RocketDM(
        id: Int,
        val stringId: String,
        val name: String,
        val country: String,
        val enginesCount: String,
        val isActive: Boolean,
        val description : String
) : BaseDM(id)
