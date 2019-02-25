package tauch.xavier.mynewskt


import tauch.xavier.mynewskt.Controllers.Fragments.MostPopularFragment
import org.junit.Test

import org.junit.Assert.assertNotNull

class MostPopularFragmentTest {

    @Test
    @Throws(Exception::class)
    fun mostPopularFragmentNullCheck() {
        val mostPopularFragment = MostPopularFragment.newInstance()
        assertNotNull(mostPopularFragment)
    }
}
