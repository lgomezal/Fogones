package com.codigohifi.fogones.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.ArrayAdapter
import com.codigohifi.fogones.R
import com.codigohifi.fogones.activity.BillActivity
import com.codigohifi.fogones.model.Plate
import com.codigohifi.fogones.model.Tables
import kotlinx.android.synthetic.main.content_table.*
import kotlinx.android.synthetic.main.fragment_table_pager.*

class TablePagerFragment: Fragment() {

    var cuenta: Float = 0.00f

    private enum class VIEW_INDEX(val index: Int) {
        LOADING(0), TABLE(1)
    }

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

        // Configuramos las animaciones para el viewSwitcher
        view_switcher.setInAnimation(activity, android.R.anim.fade_in)
        view_switcher.setOutAnimation(activity, android.R.anim.fade_out)

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

        // Le decimos al viewSwitcher que muestre la primera vista
        view_switcher.displayedChild = TablePagerFragment.VIEW_INDEX.LOADING.index

        view.postDelayed({
            if (initialTableIndex != null) {
                moveToTable(initialTableIndex)
                updateTableInfo(initialTableIndex)
            }
            view_switcher?.displayedChild = TablePagerFragment.VIEW_INDEX.TABLE.index
        }, resources.getInteger(R.integer.default_fake_delay).toLong())


    }

    private fun updateTableInfo(position: Int) {
        if (activity is AppCompatActivity) {
            val supportActionBar: ActionBar? = (activity as? AppCompatActivity)?.supportActionBar
            supportActionBar?.title = Tables.getTable(position).description

            // Actualizamos el listView
            val table = Tables.getTable(position)
            val plates: ArrayList<Plate> = table.plates
            cuenta = 0.00f
            listViewLoad(plates)
        }
    }

    fun moveToTable(position: Int) {
        view_pager.currentItem = position

        // Actualizamos el listView
        val table = Tables.getTable(position)
        val plates: ArrayList<Plate> = table.plates
        cuenta = 0.00f
        listViewLoad(plates)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.pager_navigation, menu)
        inflater?.inflate(R.menu.table_activity, menu)
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
        R.id.menu_show_bill -> {
            val description = table_description.text.toString()
            val netBill: Float = cuenta
            // Lanzamos la pantalla de la cuenta
            startActivity(BillActivity.intent( context!!, description, netBill))
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

    fun listViewLoad(plates: ArrayList<Plate>) {
        val platesList: ArrayList<Plate> = plates
        val descriptions: ArrayList<String> = arrayListOf()
        var index = 0
        for (item in platesList) {
            descriptions.add(getString(R.string.plate_list_description, plates[index].description, plates[index].price))
            cuenta += plates[index].price
            index += 1
        }
        val adapter = ArrayAdapter<String>(
                activity,
                android.R.layout.simple_list_item_1,
                descriptions)

        table_plate_list.adapter = adapter

        // Incluimos el total de la cuenta
        billText?.text = getString(R.string.bill_format, cuenta)

    }

}