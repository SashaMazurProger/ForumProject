package com.example.sasham.testproject.users

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.sasham.testproject.R
import com.example.sasham.testproject.base.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : BaseFragment(), UsersView {

    @InjectPresenter
    lateinit var presenter: UsersPresenter

    override val layoutId: Int
        get() = R.layout.fragment_users

    private lateinit var adapter: GroupAdapter<ViewHolder>

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

    override fun showUsers(users: List<UserItem>) {
        val section = Section()
        section.addAll(users)
        adapter.add(section)
    }
}

