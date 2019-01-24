package com.example.sasham.testproject.themes

import com.example.sasham.testproject.model.DataRepository
import com.example.sasham.testproject.model.Theme

import javax.inject.Inject

import io.reactivex.Observable

class ThemesListingInteractorImp @Inject
constructor(private val dataRepository: DataRepository) : ThemesListingInteractor {

    override fun fetchThemes(page: Int): Observable<List<Theme>> {

        val pageString = page.toString()
        return dataRepository.themes(pageString)
    }
}
