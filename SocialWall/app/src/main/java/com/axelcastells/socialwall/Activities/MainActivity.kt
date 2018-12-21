package com.axelcastells.socialwall.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.axelcastells.socialwall.Fragments.HomeFragment
import com.axelcastells.socialwall.Fragments.NewsFragment
import com.axelcastells.socialwall.Fragments.ProfileFragment
import com.axelcastells.socialwall.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.selectedItemId = R.id.tab_home
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            // TODO: Go to the correct screen
            when(item.itemId){
                R.id.tab_home ->{
                    //TODO: Go to home tab
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val fragment = HomeFragment()
                    fragmentTransaction.replace(R.id.fragmentContainer, fragment)
                    fragmentTransaction.commit()
                }
                R.id.tab_news ->{
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val fragment = NewsFragment()
                    fragmentTransaction.replace(R.id.fragmentContainer, fragment)
                    fragmentTransaction.commit()
                }
                R.id.tab_profile ->{
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val fragment = ProfileFragment()
                    fragmentTransaction.replace(R.id.fragmentContainer, fragment)
                    fragmentTransaction.commit()
                }
            }
            true
        }

    }
}
