package com.example.sasham.testproject.util;

import com.example.sasham.testproject.model.Message;
import com.example.sasham.testproject.model.Theme;
import com.example.sasham.testproject.network.MessageAnswer;
import com.example.sasham.testproject.network.ThemeAnswer;

public class Converter {

    public static Theme themeAnswerToTheme(ThemeAnswer themeAnswer) {
        if (themeAnswer == null) {
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

    public static Message messageAnswerToMessage(MessageAnswer messageAnswer) {
        if (messageAnswer == null) {
            return null;
        }

        return new Message(
                messageAnswer.getId(),
                messageAnswer.getTopicId(),
                messageAnswer.getUserId(),
                messageAnswer.getUserName(),
                messageAnswer.getMsgTime(),
                messageAnswer.getMsgText(),
                messageAnswer.getUserEmail(),
                messageAnswer.getMsgStatus(),
                messageAnswer.getMsgRem(),
                messageAnswer.getReputation(),
                messageAnswer.getIsAdmin(),
                messageAnswer.getIsModerator(),
                messageAnswer.getMsgCount(),
                messageAnswer.getAvatar()
        );
    }
}
