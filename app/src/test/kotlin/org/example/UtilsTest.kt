package org.example

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class UtilsTest {
    @Test fun isAscendingWorks() {
        val list = listOf(1, 2, 3, 4, 5)
        assertTrue(Utils.isAscending(list))
    }

    @Test fun isDescendingWorks() {
        val list = listOf(5, 4, 3, 2, 1)
        assertTrue(Utils.isDescending(list))
    }
}