package tauch.xavier.mynewskt


import tauch.xavier.mynewskt.Controllers.Fragments.BusinessFragment
import org.junit.Test

import org.junit.Assert.assertNotNull

class BusinessFragmentTest {

    @Test
    @Throws(Exception::class)
    fun businessFragmentNullCheck() {
        val businessFragment = BusinessFragment.newInstance()
        assertNotNull(businessFragment)
    }

}
