package tauch.xavier.mynewskt.Adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import tauch.xavier.mynewskt.Controllers.Fragments.BusinessFragment
import tauch.xavier.mynewskt.Controllers.Fragments.MostPopularFragment
import tauch.xavier.mynewskt.Controllers.Fragments.TopStoriesFragment
import tauch.xavier.mynewskt.R


class PageAdapter//Default Constructor
    (mgr: FragmentManager, private val mContext: Context) : FragmentPagerAdapter(mgr) {

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 //Page number 1
            -> return TopStoriesFragment.newInstance()
            1 //Page number 2
            -> return MostPopularFragment.newInstance()
            2 //Page number 3
            -> return BusinessFragment.newInstance()
            else -> return null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {

        val TOP_STORIES = mContext.getString(R.string.top_stories)
        val MOST_POPULAR = mContext.getString(R.string.most_popular)
        val BUSINESS = mContext.getString(R.string.business)

        when (position) {
            0 //Page number 1
            -> return TOP_STORIES
            1 //Page number 2
            -> return MOST_POPULAR
            2 //Page number 3
            -> return BUSINESS
            else -> return null
        }
    }
}