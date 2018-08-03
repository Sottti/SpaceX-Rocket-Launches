package com.spacex.domain

import org.junit.Assert
import org.junit.Test

class RocketDM_ExtensionsTests {

    private val falcon1 = RocketDM(
            1, "falcon1", "Falcon 1", "Republic...", "1", false)
    private val falcon9 = RocketDM(2, "falcon9", "Falcon 9", "Usa", "9", true)
    private val falconHeavy = RocketDM(3, "falconheavy", "Falcon Heavy", "USA", "27", true)
    private val bfr = RocketDM(4, "bfr", "BFR", "USA", "31", false)

    @Test
    fun getActiveRocketsTest() {
        val rockets: List<RocketDM> = listOf(falcon1, falcon9, falconHeavy, bfr)
        Assert.assertTrue(rockets.getActiveRockets().size == 2)
        Assert.assertTrue(rockets.get(0).id == 1)
        Assert.assertTrue(rockets.get(1).id == 2)
    }

    @Test
    fun getComplementaryTest() {
        val rockets: List<RocketDM> = listOf(falcon1, falcon9)
        val rocketsTwo: List<RocketDM> = listOf(falconHeavy, bfr)
        val rocketsThree: List<RocketDM> = listOf(falcon9, falconHeavy)

        Assert.assertTrue(rockets.getComplementary(rockets).isEmpty())

        Assert.assertTrue(rockets.getComplementary(rocketsThree).size == 1)
        Assert.assertTrue(rockets.getComplementary(rocketsThree)[0].id == 1)

        Assert.assertTrue(rocketsTwo.getComplementary(rockets).size == 2)
        Assert.assertTrue(rocketsTwo.getComplementary(rockets)[0].id == 3)
        Assert.assertTrue(rocketsTwo.getComplementary(rockets)[1].id == 4)
    }

}