package com.example.sasham.testproject.users

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.sasham.testproject.base.IView

@StateStrategyType(AddToEndStrategy::class)
interface UsersView : IView {
    fun showUsers(users: List<UserItem>)
}
