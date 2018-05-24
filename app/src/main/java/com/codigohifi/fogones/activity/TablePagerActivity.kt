package com.codigohifi.fogones.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.Menu
import android.view.MenuItem
import com.codigohifi.fogones.R
import com.codigohifi.fogones.fragment.TableFragment
import com.codigohifi.fogones.model.Tables
import kotlinx.android.synthetic.main.activity_table_pager.*

class TablePagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_pager)

        toolbar.setLogo(R.mipmap.ic_launcher)
        setSupportActionBar(toolbar)


        val tables = Tables()

        val adapter = object: FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return TableFragment.newInstance(tables.getTable(position))
            }

            override fun getCount(): Int {
                return tables.count
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return tables.getTable(position).description
            }

        }

        view_pager.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.pager_navigation, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.previous -> {
            view_pager.currentItem = view_pager.currentItem - 1
            true
        }
        R.id.next -> {
            view_pager.currentItem = view_pager.currentItem + 1
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu)

        val previousMenu = menu?.findItem(R.id.previous)
        val nextMenu = menu?.findItem(R.id.next)

        val adapter = view_pager.adapter!!
        previousMenu?.isEnabled = view_pager.currentItem > 0
        nextMenu?.isEnabled = view_pager.currentItem < adapter.count - 1

        return true
    }
}
