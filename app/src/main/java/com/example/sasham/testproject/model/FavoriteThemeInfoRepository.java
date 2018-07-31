package com.example.sasham.testproject.model;

import java.util.List;

public interface FavoriteThemeInfoRepository {

    List<FavoriteThemeInfo> getMostViewed(int count);
}
