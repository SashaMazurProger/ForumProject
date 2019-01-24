package com.example.sasham.testproject.util

import com.example.sasham.testproject.model.Message
import com.example.sasham.testproject.model.Theme
import com.example.sasham.testproject.network.MessageAnswer
import com.example.sasham.testproject.network.ThemeAnswer

object Converter {

    fun themeAnswerToTheme(themeAnswer: ThemeAnswer?): Theme? {
        return if (themeAnswer == null) {
            null
        } else Theme(
                themeAnswer.id.toString(),
                themeAnswer.userId.toString(),
                themeAnswer.userName,
                themeAnswer.topicText,
                themeAnswer.msgText,
                themeAnswer.msgTime.toString(),
                themeAnswer.topicUpdated.toString()
        )

    }

    fun messageAnswerToMessage(messageAnswer: MessageAnswer?): Message? {
        return if (messageAnswer == null) {
            null
        } else Message(
                messageAnswer.id,
                messageAnswer.topicId,
                messageAnswer.userId,
                messageAnswer.userName,
                messageAnswer.msgTime,
                messageAnswer.msgText,
                messageAnswer.userEmail,
                messageAnswer.msgStatus,
                messageAnswer.msgRem,
                messageAnswer.reputation,
                messageAnswer.isAdmin,
                messageAnswer.isModerator,
                messageAnswer.msgCount,
                messageAnswer.avatar
        )

    }
}
