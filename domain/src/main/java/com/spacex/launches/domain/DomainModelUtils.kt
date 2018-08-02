package com.spacex.launches.domain

import android.util.SparseIntArray
import java.util.*

object DomainModelUtils {

    fun <T : BaseDM> getComplementary(referenceSet: List<T>, comparableSet: List<T>): List<T> {
        val complementary = ArrayList<T>()
        val referenceSetIds = getIds(referenceSet)
        for (i in comparableSet.indices) {
            val comparableSetItem = comparableSet[i]
            val comparableSetId = comparableSetItem.id
            if (referenceSetIds.get(comparableSetId) == 0) {
                complementary.add(comparableSetItem)
            }
        }
        return complementary
    }

    private fun <T : BaseDM> getIds(list: List<T>): SparseIntArray {
        val ids = SparseIntArray(list.size)
        for (i in list.indices) {
            val id = list[i].id
            ids.put(id, id)
        }
        return ids
    }
}
