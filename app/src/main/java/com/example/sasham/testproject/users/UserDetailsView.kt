package com.example.sasham.testproject.users

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.sasham.testproject.base.IView
import com.example.sasham.testproject.model.User

@StateStrategyType(AddToEndStrategy::class)
interface UserDetailsView : IView {
    fun showUser(user: User)
}