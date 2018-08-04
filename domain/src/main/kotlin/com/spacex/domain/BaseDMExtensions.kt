package com.spacex.domain

fun <T : BaseDM> List<T>.getComplementary(referenceSet: List<T>): List<T> {
    return filter { objectDM -> !referenceSet.getIds().contains(objectDM.id) }
}

private fun <T : BaseDM> List<T>.getIds(): List<Int> {
    return List(size) { i -> this[i].id }
}