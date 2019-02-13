package com.example.sasham.testproject.messages


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.base.BaseFragment
import com.example.sasham.testproject.model.Message
import com.example.sasham.testproject.model.Theme
import com.example.sasham.testproject.users.UserDialog
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_messages.*

/**
 * A simple [Fragment] subclass.
 */
class MessagesFragment : BaseFragment(), MessagesListingView, SwipeRefreshLayout.OnRefreshListener {


    override val layoutId: Int
        get() = R.layout.fragment_messages

    @InjectPresenter
    lateinit var presenter: MessagesPresenter

    @ProvidePresenter
    fun provide(): MessagesPresenter {
        return MessagesPresenter(arguments!!.getParcelable(Constants.THEME_MODEL))
    }

    private var groupAdapter: GroupAdapter<ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        groupAdapter = GroupAdapter()
        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context,
                androidx.recyclerview.widget.RecyclerView.VERTICAL, false)
        messagesRecyclerView!!.adapter = groupAdapter
        messagesRecyclerView!!.layoutManager = layoutManager

        swipeRefreshLayout!!.setOnRefreshListener(this)

    }

    override fun showUserDialog(message: Message) {
        if (!message.userId.isNullOrEmpty()) {
            UserDialog.newInstance(message.userId!!.toInt())
                    .show(childFragmentManager, null)
        }
    }

    override fun showThemeMessages(messages: List<Message>) {
        if (swipeRefreshLayout!!.isRefreshing) {
            swipeRefreshLayout!!.isRefreshing = false
        }

        val section = Section()
        section.addAll(messages.map { MessageItem(it, presenter) })
        groupAdapter!!.add(section)
    }


    override fun onRefresh() {
        swipeRefreshLayout!!.isRefreshing = true
        presenter!!.fetchMessages()
    }

    companion object {

        fun newInstance(theme: Theme): MessagesFragment {
            val bundle = Bundle()
            bundle.putParcelable(Constants.THEME_MODEL, theme)
            val messagesFragment = MessagesFragment()
            messagesFragment.arguments = bundle
            return messagesFragment
        }
    }
}
