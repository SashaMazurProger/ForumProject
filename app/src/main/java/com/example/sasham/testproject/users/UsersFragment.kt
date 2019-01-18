package com.example.sasham.testproject.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.network.WebestApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_users.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UsersFragment : Fragment() {

    lateinit var api: WebestApi

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater!!.inflate(R.layout.fragment_users, container, false)
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        api = Retrofit.Builder()
                .baseUrl(Constants.WEBEST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(WebestApi::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = GroupAdapter<ViewHolder>()
        listView.apply {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(context)
        }

        api.users()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ w ->
                    val section = Section()
                    section.addAll(w.users!!.map { UserItem(it) })
                    adapter.add(section)

                },
                        {
                            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                        })


        adapter.setOnItemClickListener { item, view ->
            if (item is UserItem) {
                val userItem = item as UserItem
                Toast.makeText(context, "${userItem.user.userName} view: ${view.javaClass.name} ", Toast.LENGTH_SHORT).show()
            }
        }


    }


}

