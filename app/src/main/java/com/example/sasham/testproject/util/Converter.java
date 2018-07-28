package com.example.sasham.testproject.util;

import com.example.sasham.testproject.model.Theme;
import com.example.sasham.testproject.network.ThemeAnswer;

public class Converter {

    public static Theme themeAnswerToTheme(ThemeAnswer themeAnswer){
        if(themeAnswer==null){
            return null;
        }

        return new Theme(
                themeAnswer.getId(),
                themeAnswer.getUserId(),
                themeAnswer.getUserName(),
                themeAnswer.getTopicText(),
                themeAnswer.getMsgText(),
                themeAnswer.getMsgTime(),
                themeAnswer.getTopicUpdated(),
                themeAnswer.getIsAdmin(),
                themeAnswer.getIsModerator(),
                themeAnswer.getForumName()
        );
    }
}
