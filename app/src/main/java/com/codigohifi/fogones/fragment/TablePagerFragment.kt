package com.codigohifi.fogones.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.codigohifi.fogones.R
import com.codigohifi.fogones.activity.TablePagerActivity
import com.codigohifi.fogones.model.Tables
import kotlinx.android.synthetic.main.fragment_table_pager.*

class TablePagerFragment: Fragment() {

    companion object {
        val ARG_TABLE = "ARG_TABLE"

        fun newInstance(tableIndex: Int): TablePagerFragment {
            val arguments = Bundle()
            arguments.putInt(ARG_TABLE, tableIndex)
            val fragment = TablePagerFragment()
            fragment.arguments = arguments

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_table_pager, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = object: FragmentPagerAdapter(fragmentManager) {
            override fun getItem(position: Int): Fragment {
                return TableFragment.newInstance(Tables.getTable(position))
            }

            override fun getCount(): Int {
                return Tables.count
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return Tables.getTable(position).description
            }

        }

        view_pager.adapter = adapter

        view_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                updateTableInfo(position)
            }

        })

        val initialTableIndex = arguments?.getInt(ARG_TABLE, 0)

        if (initialTableIndex != null) {
            moveToTable(initialTableIndex)
            updateTableInfo(initialTableIndex)
        }

    }

    private fun updateTableInfo(position: Int) {
        if (activity is AppCompatActivity) {
            val supportActionBar: ActionBar? = (activity as? AppCompatActivity)?.supportActionBar
            supportActionBar?.title = Tables.getTable(position).description
        }
    }

    private fun moveToTable(position: Int) {
        view_pager.currentItem = position
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.pager_navigation, menu)
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

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)

        val previousMenu = menu?.findItem(R.id.previous)
        val nextMenu = menu?.findItem(R.id.next)

        val adapter = view_pager.adapter!!
        previousMenu?.isEnabled = view_pager.currentItem > 0
        nextMenu?.isEnabled = view_pager.currentItem < adapter.count - 1
    }

}