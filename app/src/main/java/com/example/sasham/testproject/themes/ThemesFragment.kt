package com.example.sasham.testproject.themes

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.base.BaseFragment
import com.example.sasham.testproject.messages.MessagesActivity
import com.example.sasham.testproject.model.Theme
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_themes_listing.*


class ThemesFragment : BaseFragment(), ThemesView, androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener {

    override val layoutId: Int
        get() = R.layout.fragment_themes_listing

    @InjectPresenter
    lateinit var presenter: ThemesPresenter
    private var themesAdapter: GroupAdapter<ViewHolder>? = null
    private var sectionsAdapter: GroupAdapter<ViewHolder>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        themesAdapter = GroupAdapter()
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        themesList!!.adapter = themesAdapter
        themesList!!.layoutManager = layoutManager

        themesAdapter!!.setOnItemClickListener { item, view ->
            val intent = Intent(context, MessagesActivity::class.java)
            intent.putExtra(Constants.THEME_MODEL, (item as ThemeItem).theme)
            startActivity(intent)
        }


        sectionsAdapter = GroupAdapter()
        val layoutManager2 = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        sectionsList!!.adapter = sectionsAdapter
        sectionsList!!.layoutManager = layoutManager2

        sectionsAdapter!!.setOnItemClickListener { item, view ->
            presenter.selectSection((item as SectionItem).section)
            baseActivity!!.setToolbarTitle(item.section.title)
            drawerLayout.closeDrawer(Gravity.LEFT)
        }

        //TODO If we scrolled to end list then load more data

        swipeRefreshLayout!!.setOnRefreshListener(this)
    }


    override fun showThemes(themes: List<Theme>) {

        if (swipeRefreshLayout!!.isRefreshing) {
            swipeRefreshLayout!!.isRefreshing = false
        }

        val section = Section()
        section.addAll(themes.map { ThemeItem(it) })
        themesAdapter!!.clear()
        themesAdapter!!.add(section)
    }

    override fun showSections(sections: List<com.example.sasham.testproject.model.Section>) {

        val section = Section()
        section.addAll(sections.map { SectionItem(it) })
        sectionsAdapter!!.clear()
        sectionsAdapter!!.add(section)
    }

    override fun onRefresh() {
        swipeRefreshLayout!!.isRefreshing = true
        presenter!!.loadNewData()
    }

}// Required empty public constructor