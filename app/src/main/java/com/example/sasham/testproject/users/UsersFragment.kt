package com.example.sasham.testproject.users

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import com.example.sasham.testproject.R
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GroupAdapter()
        listView.setAdapter(adapter)
        listView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

        adapter.setOnItemClickListener { item, view ->
            if (item is UserItem) {
                val userDialog = UserDialog.newInstance(item.user)
                userDialog.show(childFragmentManager, UserDialog::javaClass.name)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_users, menu)

        val searchItem = menu.findItem(R.id.search_action)
        val search = searchItem.actionView as SearchView

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.searchUser(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        search.setOnCloseListener {
            presenter.loadAllUsers()
            searchItem.expandActionView()
            true
        }

    }

    override fun showUsers(users: List<UserItem>) {
        val section = Section()
        section.addAll(users)
        adapter.clear()
        adapter.add(section)
    }

    companion object {

        fun newInstance(): UsersFragment {
            return UsersFragment()
        }
    }
}

