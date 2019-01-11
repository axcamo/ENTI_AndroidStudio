package com.perea.marc.streampad

import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.google.gson.internal.Streams
import com.perea.marc.streampad.model.TWGameResponse
import com.perea.marc.streampad.model.TWStreamResponse
import com.perea.marc.streampad.network.ApiService
import com.perea.marc.streampad.model.TWStream


import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.fragment_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //search_list.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        getApiData()
    }

    fun getApiData() {

        var newGameId: String? = null
        // Get Games
        ApiService.service.getGames("Twitch Plays").enqueue(object : Callback<TWGameResponse> {
            override fun onFailure(call: Call<TWGameResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<TWGameResponse>, response: Response<TWGameResponse>) {

                if (response.isSuccessful) {
                    response.body()?.data?.let { games ->
                        for (game in games) {
                            Log.i("Twitch", games.toString())
                            newGameId = game.id
                            Log.i("Twitch", newGameId)
                        }
                    }
                }
            }
        })

        //Get Streams

        ApiService.service.getStreams(newGameId!!).enqueue(object : Callback<TWStreamResponse> {
            override fun onFailure(call: Call<TWStreamResponse>, t: Throwable) {
                // No response from server
                // TODO: Handle error
                t.printStackTrace()
            }

            override fun onResponse(call: Call<TWStreamResponse>, response: Response<TWStreamResponse>) {
                // We got response from server
                if (response.isSuccessful) {
                    response.body()?.data?.let { streams ->
                        for (stream in streams) {
                            Log.i("Twitch", streams.toString())

                            // Get Game
                            /*
                            stream.gameId?.let {
                                ApiService.service.getGames(it).enqueue(object : Callback<TWGameResponse> {
                                    override fun onFailure(call: Call<Any>, t: Throwable) {
                                        t.printStackTrace()
                                    }

                                    override fun onResponse(call: Call<Any>, response: Response<Any>) {
                                        Log.i("MainActivity", response.body()?.toString() ?: "")
                                    }
                                })
                            }
                            */
                        }
                    } ?: Log.e("MainActivity", "Error getting streams")
                } else {
                    // TODO: Handle error
                }
            }

        })

    }


}

/*
    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val rootView = inflater.inflate(R.layout.fragment_main, container, false)
            rootView.section_label.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
*/