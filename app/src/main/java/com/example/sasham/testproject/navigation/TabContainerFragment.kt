package com.example.sasham.testproject.navigation

import android.content.Context
import android.os.Bundle
import androidx.annotation.Nullable
import com.example.sasham.testproject.App
import com.example.sasham.testproject.R
import com.example.sasham.testproject.base.BaseFragment
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

/**
 * Created by terrakok 25.11.16
 */
class TabContainerFragment : BaseFragment(), RouterProvider, BackButtonListener {

    override val layoutId: Int
        get() = R.layout.fragment_tab_container

    private var navigator: Navigator? = null

    @Inject
    lateinit var ciceroneHolder: LocalCiceroneHolder

    private val screenName: String
        get() = arguments!!.getString(EXTRA_NAME)

    private val cicerone: Cicerone<Router>
        get() = ciceroneHolder!!.getCicerone(screenName)

    override val router: Router
        get() = cicerone.router

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance?.appComp?.inject(this)
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (childFragmentManager.findFragmentById(R.id.ftc_container) == null) {
            cicerone.router.replaceScreen(Screens.ForwardScreen(screenName, 0))
        }
    }

    override fun onResume() {
        super.onResume()
        cicerone.navigatorHolder.setNavigator(getNavigator())
    }

    override fun onPause() {
        cicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun getNavigator(): Navigator {
        if (navigator == null) {
            navigator = SupportAppNavigator(activity, childFragmentManager, R.id.ftc_container)
        }

        return navigator as Navigator
    }

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.ftc_container)
        if (fragment != null
                && fragment is BackButtonListener
                && (fragment as BackButtonListener).onBackPressed()) {
            return true
        } else {
            (activity as RouterProvider).router.exit()
            return true
        }
    }

    companion object {
        private val EXTRA_NAME = "tcf_extra_name"

        fun getNewInstance(name: String): TabContainerFragment {
            val fragment = TabContainerFragment()

            val arguments = Bundle()
            arguments.putString(EXTRA_NAME, name)
            fragment.arguments = arguments

            return fragment
        }
    }
}
