package com.perea.marc.socialwall

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabs.selectedItemId = R.id.board
        tabs.setOnNavigationItemSelectedListener {

            when (item.itemId) {
                R.id.board -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val fragment = HomeFragment()
                    fragmentTransaction.replace(R.id.fragmentContainer, fragment)
                    fragmentTransaction.commit()
                }
                R.id.news -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val fragment = NewsFragment()
                    fragmentTransaction.replace(R.id.fragmentContainer, fragment)
                    fragmentTransaction.commit()
                }
                R.id.profile -> {
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
