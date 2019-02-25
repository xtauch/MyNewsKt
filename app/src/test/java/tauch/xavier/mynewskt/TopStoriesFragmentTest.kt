package tauch.xavier.mynewskt


import tauch.xavier.mynewskt.Controllers.Fragments.TopStoriesFragment
import org.junit.Test

import org.junit.Assert.assertNotNull

class TopStoriesFragmentTest {

    @Test
    @Throws(Exception::class)
    fun topStoriesFragmentNullCheck() {
        val topStoriesFragment = TopStoriesFragment.newInstance()
        assertNotNull(topStoriesFragment)
    }
}