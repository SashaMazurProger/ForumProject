package com.example.sasham.testproject.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class Theme : Parcelable {

    var id: String? = null
    var userId: String? = null
    var userName: String? = null
    var topicText: String? = null
    var msgText: String? = null
    var msgTime: String? = null
    var topicUpdated: String? = null
    var isFavorite: Boolean? = null

    constructor(parcel: Parcel) {
        id = parcel.readString()
        userId = parcel.readString()
        userName = parcel.readString()
        topicText = parcel.readString()
        msgText = parcel.readString()
        msgTime = parcel.readString()
        topicUpdated = parcel.readString()
        isFavorite = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    }


    constructor(id: String?, userId: String?, userName: String?, topicText: String?, msgText: String?, msgTime: String?, topicUpdated: String?, isFavorite: Boolean?) {
        this.id = id
        this.userId = userId
        this.userName = userName
        this.topicText = topicText
        this.msgText = msgText
        this.msgTime = msgTime
        this.topicUpdated = topicUpdated
        this.isFavorite = isFavorite
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(userId)
        parcel.writeString(userName)
        parcel.writeString(topicText)
        parcel.writeString(msgText)
        parcel.writeString(msgTime)
        parcel.writeString(topicUpdated)
        parcel.writeValue(isFavorite)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<Theme> {

        override fun createFromParcel(parcel: Parcel): Theme {
            return Theme(parcel)
        }

        override fun newArray(size: Int): Array<Theme?> {
            return arrayOfNulls(size)
        }

        fun copy(theme: FavoriteTheme): Theme {
            return Theme(theme.themeId.toString(), theme.userId, theme.userName, theme.topicText, theme.msgText, theme.msgTime, theme.topicUpdated, true)
        }
    }


}