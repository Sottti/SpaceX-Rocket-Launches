package com.spacex.rockets

import org.junit.Assert
import org.junit.Test

class Long_ExtensionsTest {

    @Test
    fun getMillisSinceTest() {
        Assert.assertTrue((System.currentTimeMillis() + 1000L).getMillisSince() == 0L)
        Assert.assertTrue((System.currentTimeMillis() - 1000L).getMillisSince() > 995L)
        Assert.assertTrue((System.currentTimeMillis() - 1000L).getMillisSince() < 1005L)
    }
}