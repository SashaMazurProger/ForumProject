package com.example.sasham.testproject.themes

import com.example.sasham.testproject.model.Theme

import io.reactivex.Observable

interface ThemesListingInteractor {

    fun fetchThemes(page: Int): Observable<List<Theme>>
}
