package com.example.news
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MyPageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val newsSources = listOf(
            "VICE-News", "Ary News", "BBC News", "BBC Sport",
            "USA Today", "CNN", "Fox News", "Google News",
            "The Verge", "News24"
        )

        return if (position < newsSources.size) {
            NewsFragment.newInstance(newsSources[position])
        } else {
            Fragment() // Return a default empty fragment
        }
    }

    override fun getCount(): Int {
        return 10 // Return the total number of fragments (news sources)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "VICE-News"
            1 -> "Ary News"
            2 -> "BBC News"
            3 -> "BBC Sport"
            4 -> "USA Today"
            5 -> "CNN"
            6 -> "Fox News"
            7 -> "Google News"
            8 -> "The Verge"
            9 -> "News24"
            else -> "Hello" // Return a default title
        }
    }
}