package com.mnishiguchi.weatherapp

import com.mnishiguchi.weatherapp.ext.toDateString
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by masa on 6/22/17.
 */
class ExtTest {
    @Test fun long_to_date_string() {
        assertEquals("Oct 19, 2015", 1445275635000L.toDateString())
    }
}