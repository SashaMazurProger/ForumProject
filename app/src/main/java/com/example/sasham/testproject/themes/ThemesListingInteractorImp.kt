package com.example.sasham.testproject.themes

import com.example.sasham.testproject.model.NetworkRepository
import com.example.sasham.testproject.model.Theme
import com.example.sasham.testproject.network.ThemeAnswer
import com.example.sasham.testproject.network.ThemesWraper
import com.example.sasham.testproject.network.WebestApi
import com.example.sasham.testproject.util.Converter

import java.util.ArrayList

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function

class ThemesListingInteractorImp @Inject
constructor(private val networkRepository: NetworkRepository) : ThemesListingInteractor {

    override fun fetchThemes(page: Int): Observable<List<Theme>> {

        val pageString = page.toString()
        return networkRepository.themes(pageString)
    }
}
