package com.example.sasham.testproject.themes

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.MvpAppCompatFragment
import com.example.sasham.testproject.R
import com.example.sasham.testproject.base.BaseActivity
import com.example.sasham.testproject.model.Theme
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_themes_listing.*
import java.util.*


class ThemesListingFragment : MvpAppCompatFragment(), ThemesListingView, BaseActivity.OnConnectionListener, androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener {

    @InjectPresenter
    lateinit var presenter: ThemesListingPresenterImp

    private var callback: Callback? = null
    private val themes = ArrayList<Theme>()

    private var themesListingAdapter: ThemesListingAdapter? = null
    private var baseActivity: BaseActivity? = null
    private var isConnected = false

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Callback) {
            callback = context
        }
        if (context is BaseActivity) {
            baseActivity = context
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater!!.inflate(R.layout.fragment_themes_listing, container, false)
        return root
    }

    private fun mayLoadMoreData(): Boolean {
        return !themesRecyclerView!!.canScrollVertically(1) && isConnected
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        themesListingAdapter = ThemesListingAdapter(themes, this)
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        themesRecyclerView!!.adapter = themesListingAdapter
        themesRecyclerView!!.layoutManager = layoutManager

        //If we scrolled to end list then load more data
        themesRecyclerView!!.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (mayLoadMoreData()) {
                    presenter!!.nextPage()
                }
            }
        })

        swipeRefreshLayout!!.setOnRefreshListener(this)

        activity!!.title = getString(R.string.app_name)

        if (baseActivity != null) {
            baseActivity!!.addOnConnectionListener(this)
        }

        //В случае если фрагмент не загрузил данные - загружаем первую страницу.
        // Пропускаем если данные уже есть и нам не нужно их заменять
        if (themes.size == 0) {
            presenter!!.firstPage()
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState!!.putParcelableArrayList(Constants.THEME_MODEL, themes as ArrayList<out Parcelable>)
    }

    override fun showThemes(themeList: List<Theme>) {

        if (swipeRefreshLayout!!.isRefreshing) {
            swipeRefreshLayout!!.isRefreshing = false
        }
        themesProgress!!.visibility = View.GONE

        if (themeList.size == 0) {
            message(getString(R.string.no_themes))
        }

        themes.clear()
        themes.addAll(themeList)
        themesListingAdapter!!.notifyDataSetChanged()
    }

    override fun onLoading() {
        themesEmptyView!!.visibility = View.GONE

        if (!swipeRefreshLayout!!.isRefreshing) {
            themesProgress!!.visibility = View.VISIBLE
        }
    }

    override fun message(errorMessage: String?) {
        //        themesRecyclerView.setVisibility(View.GONE);
        themesProgress!!.visibility = View.GONE
        themesEmptyView!!.text = errorMessage
    }

    override fun onThemeClicked(theme: Theme) {
        if (callback != null) {
            callback!!.onThemeClicked(theme)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (baseActivity != null) {
            baseActivity!!.removeOnConnectionListener(this)
        }
    }

    override fun internetConnectionChanged(connected: Boolean) {
        isConnected = connected
        if (mayLoadMoreData()) {
            presenter!!.nextPage()
        }
    }

    override fun onRefresh() {
        if (isConnected) {
            swipeRefreshLayout!!.isRefreshing = true
            presenter!!.loadNewData()
        } else {
            swipeRefreshLayout!!.isRefreshing = false
        }
    }

    override fun onDetach() {
        callback = null
        baseActivity = null
        super.onDetach()
    }

    interface Callback {
        fun onThemeClicked(theme: Theme)
    }
}// Required empty public constructor
