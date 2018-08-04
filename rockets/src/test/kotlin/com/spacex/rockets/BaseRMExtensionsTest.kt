package com.spacex.rockets

import com.spacex.rockets.model.RocketRM
import com.spacex.rockets.model.getOldestItemAgeInMillis
import org.junit.Assert
import org.junit.Test

internal class BaseRMExtensionsTest {
    private val now = System.currentTimeMillis()
    private val falcon1 = RocketRM(
            1, "falcon1", "Falcon 1", "Republic...", "1", false, "Desc", now - 10)
    private val falcon9 = RocketRM(2, "falcon9", "Falcon 9", "Usa", "9", true, "Desc", now - 15)
    private val falconHeavy = RocketRM(3, "falconheavy", "Falcon Heavy", "USA", "27", true, "Desc", now - 20)
    private val bfr = RocketRM(4, "bfr", "BFR", "USA", "31", false, "Desc", now - 25)

    @Test
    fun getOldestItemAgeInMillisTest() {
        val rockets: List<RocketRM> = listOf(falcon1, falcon9, falconHeavy, bfr)
        Assert.assertTrue(rockets.getOldestItemAgeInMillis() >= 25)

        val rockets2: List<RocketRM> = listOf(falcon1)
        Assert.assertTrue(rockets2.getOldestItemAgeInMillis() >= 10)
    }
}