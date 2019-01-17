package com.example.sasham.testproject.messages


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.Unbinder
import com.example.sasham.testproject.BaseActivity
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.model.Message
import com.example.sasham.testproject.model.Theme
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_messages.*
import java.util.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MessagesFragment : Fragment(), MessagesListingView, SwipeRefreshLayout.OnRefreshListener, BaseActivity.OnConnectionListener {

    @Inject
    lateinit var presenter: MessagesListingPresenter

    private val messages = ArrayList<Message>()
    private var theme: Theme? = null

    private var messagesListingAdapter: MessagesListingAdapter? = null
    private var baseActivity: BaseActivity? = null
    private var isConnected = false
    private var isDataLoaded = false

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            baseActivity = context
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater!!.inflate(R.layout.fragment_messages, container, false)
        return root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        theme = arguments.getParcelable(Constants.THEME_MODEL)

        presenter!!.setView(this)


        messagesListingAdapter = MessagesListingAdapter(messages)
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        messagesRecyclerView!!.adapter = messagesListingAdapter
        messagesRecyclerView!!.layoutManager = layoutManager

        swipeRefreshLayout!!.setOnRefreshListener(this)

        if (baseActivity != null) {
            baseActivity!!.addOnConnectionListener(this)
        }

        if (theme != null) {
            presenter!!.fetchMessages(theme!!.id!!)
        } else {
            onError(getString(R.string.no_messages))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        presenter!!.destroy()
        if (baseActivity != null) {
            baseActivity!!.removeOnConnectionListener(this)
        }
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun showThemeMessages(messageList: List<Message>) {
        if (swipeRefreshLayout!!.isRefreshing) {
            swipeRefreshLayout!!.isRefreshing = false
        }
        messageProgress!!.visibility = View.GONE
        messagesRecyclerView!!.visibility = View.VISIBLE

        if (messageList.size == 0) {
            onError(getString(R.string.no_messages))
        } else {
            emptyView!!.visibility = View.GONE
        }

        isDataLoaded = true
        messages.clear()
        messages.addAll(messageList)
        messagesListingAdapter!!.notifyDataSetChanged()
    }

    override fun onLoading() {
        emptyView!!.visibility = View.GONE

        if (!swipeRefreshLayout!!.isRefreshing) {
            messageProgress!!.visibility = View.VISIBLE
        }
    }

    override fun onError(message: String) {
        if (swipeRefreshLayout!!.isRefreshing) {
            swipeRefreshLayout!!.isRefreshing = false
        }
        messagesRecyclerView!!.visibility = View.GONE
        messageProgress!!.visibility = View.GONE
        emptyView!!.visibility = View.VISIBLE
        emptyView!!.text = message

        isDataLoaded = false
    }

    override fun onRefresh() {
        if (isConnected) {
            swipeRefreshLayout!!.isRefreshing = true
            presenter!!.loadNewData(theme!!.id!!)
        } else if (swipeRefreshLayout!!.isRefreshing) {
            swipeRefreshLayout!!.isRefreshing = false
        }
    }

    override fun internetConnectionChanged(connected: Boolean) {
        isConnected = connected
        if (mayDownloadData()) {
            presenter!!.fetchMessages(theme!!.id!!)
        }
    }

    private fun mayDownloadData(): Boolean {
        return isConnected && !isDataLoaded
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
}// Required empty public constructor
