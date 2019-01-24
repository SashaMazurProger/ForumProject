package com.example.sasham.testproject.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.sasham.testproject.MvpAppCompatFragment
import com.example.sasham.testproject.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : MvpAppCompatFragment(), UsersView {


    @InjectPresenter
    lateinit var presenter: UsersPresenter

//    @ProvidePresenterTag(presenterClass = UsersPresenter::class, type = PresenterType.GLOBAL)
//
//    @ProvidePresenter(type = PresenterType.GLOBAL)
//    fun provideDialogPresenter() = UsersPresenter()

//    override val layoutId: Int
//        get() = R.layout.fragment_users

    private lateinit var adapter: GroupAdapter<ViewHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GroupAdapter()
        listView.setAdapter(adapter)
        listView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

        adapter.setOnItemClickListener { item, view ->
            if (item is UserItem) {
                val userItem = item as UserItem
                Toast.makeText(context, "${userItem.user.name} view: ${view.javaClass.name} ", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun message(m: String) {
        Toast.makeText(context, m, Toast.LENGTH_SHORT).show()
    }

    override fun showUsers(users: List<UserItem>) {
        val section = Section()
        section.addAll(users)
        adapter.add(section)
    }
}

