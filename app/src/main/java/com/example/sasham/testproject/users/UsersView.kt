package com.example.sasham.testproject.users

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface UsersView : MvpView {

    fun showUsers(users: List<UserItem>)
    fun message(m: String)
}
