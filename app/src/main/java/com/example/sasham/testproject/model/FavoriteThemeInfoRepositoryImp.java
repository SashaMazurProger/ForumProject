package com.example.sasham.testproject.model;

import java.util.Arrays;
import java.util.List;

public class FavoriteThemeInfoRepositoryImp implements FavoriteThemeInfoRepository {
    @Override
    public List<FavoriteThemeInfo> getMostViewed(int count) {
        return Arrays.asList(new FavoriteThemeInfo("268615", 3, 1521094041));
    }
}
