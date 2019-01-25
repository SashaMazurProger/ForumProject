package com.example.sasham.testproject.themes

import android.content.Intent
import android.os.Bundle
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
import java.util.*


class ThemesFragment : BaseFragment(), ThemesView, androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener {

    override val layoutId: Int
        get() = R.layout.fragment_themes_listing

    @InjectPresenter
    lateinit var presenter: ThemesPresenter
    private val themes = ArrayList<Theme>()
    private var groupAdapter: GroupAdapter<ViewHolder>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        groupAdapter = GroupAdapter()
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        themesList!!.adapter = groupAdapter
        themesList!!.layoutManager = layoutManager

        groupAdapter!!.setOnItemClickListener { item, view ->
            val intent = Intent(context, MessagesActivity::class.java)
            intent.putExtra(Constants.THEME_MODEL, (item as ThemeItem).theme)
            startActivity(intent)
        }

        //TODO If we scrolled to end list then load more data

        swipeRefreshLayout!!.setOnRefreshListener(this)

        //В случае если фрагмент не загрузил данные - загружаем первую страницу.
        // Пропускаем если данные уже есть и нам не нужно их заменять
        if (themes.size == 0) {
            presenter!!.firstPage()
        }
    }


    override fun showThemes(themes: List<Theme>) {

        if (swipeRefreshLayout!!.isRefreshing) {
            swipeRefreshLayout!!.isRefreshing = false
        }

        val section = Section()
        section.addAll(themes.map { ThemeItem(it) })
        groupAdapter!!.add(section)
    }

    override fun onRefresh() {
        swipeRefreshLayout!!.isRefreshing = true
        presenter!!.loadNewData()
    }

}// Required empty public constructor
