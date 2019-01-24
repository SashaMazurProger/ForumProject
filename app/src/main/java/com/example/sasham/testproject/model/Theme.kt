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

    protected constructor(`in`: Parcel) {
        this.id = `in`.readValue(String::class.java.classLoader) as String?
        this.userId = `in`.readValue(String::class.java.classLoader) as String?
        this.userName = `in`.readValue(String::class.java.classLoader) as String?
        this.topicText = `in`.readValue(String::class.java.classLoader) as String?
        this.msgText = `in`.readValue(String::class.java.classLoader) as String?
        this.msgTime = `in`.readValue(String::class.java.classLoader) as String?
        this.topicUpdated = `in`.readValue(String::class.java.classLoader) as String?

    }

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    constructor(id: String?, userId: String?, userName: String?, topicText: String?, msgText: String?, msgTime: String?, topicUpdated: String?) {
        this.id = id
        this.userId = userId
        this.userName = userName
        this.topicText = topicText
        this.msgText = msgText
        this.msgTime = msgTime
        this.topicUpdated = topicUpdated

    }


    /**
     * @param id
     * @param isModerator
     * @param topicUpdated
     * @param forumName
     * @param userId
     * @param msgTime
     * @param userName
     * @param isAdmin
     * @param topicText
     * @param msgText
     */


    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(id)
        dest.writeValue(userId)
        dest.writeValue(userName)
        dest.writeValue(topicText)
        dest.writeValue(msgText)
        dest.writeValue(msgTime)
        dest.writeValue(topicUpdated)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Theme> = object : Creator<Theme> {


            override fun createFromParcel(`in`: Parcel): Theme {
                return Theme(`in`)
            }

            override fun newArray(size: Int): Array<Theme?> {
                return arrayOfNulls(size)
            }

        }
    }

}