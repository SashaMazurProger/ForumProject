package com.example.sasham.testproject.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class Message : Parcelable {

    var id: String? = null
    var topicId: String? = null
    var userId: String? = null
    var userName: String? = null
    var msgTime: String? = null
    var msgText: String? = null
    var userEmail: String? = null
    var msgStatus: String? = null
    var msgRem: String? = null
    var reputation: String? = null
    var isAdmin: String? = null
    var isModerator: String? = null
    var msgCount: String? = null
    var avatar: String? = null

    protected constructor(`in`: Parcel) {
        this.id = `in`.readValue(String::class.java.classLoader) as String
        this.topicId = `in`.readValue(String::class.java.classLoader) as String
        this.userId = `in`.readValue(String::class.java.classLoader) as String
        this.userName = `in`.readValue(String::class.java.classLoader) as String
        this.msgTime = `in`.readValue(String::class.java.classLoader) as String
        this.msgText = `in`.readValue(String::class.java.classLoader) as String
        this.userEmail = `in`.readValue(String::class.java.classLoader) as String
        this.msgStatus = `in`.readValue(String::class.java.classLoader) as String
        this.msgRem = `in`.readValue(String::class.java.classLoader) as String
        this.reputation = `in`.readValue(String::class.java.classLoader) as String
        this.isAdmin = `in`.readValue(String::class.java.classLoader) as String
        this.isModerator = `in`.readValue(String::class.java.classLoader) as String
        this.msgCount = `in`.readValue(String::class.java.classLoader) as String
        this.avatar = `in`.readValue(String::class.java.classLoader) as String
    }

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    constructor(id: String?, topicId: String?, userId: String?, userName: String?, msgTime: String?, msgText: String?, userEmail: String?, msgStatus: String?, msgRem: String?, reputation: String?, isAdmin: String?, isModerator: String?, msgCount: String?, avatar: String?) {
        this.id = id
        this.topicId = topicId
        this.userId = userId
        this.userName = userName
        this.msgTime = msgTime
        this.msgText = msgText
        this.userEmail = userEmail
        this.msgStatus = msgStatus
        this.msgRem = msgRem
        this.reputation = reputation
        this.isAdmin = isAdmin
        this.isModerator = isModerator
        this.msgCount = msgCount
        this.avatar = avatar
    }

    /**
     * @param msgTime
     * @param userEmail
     * @param avatar
     * @param msgStatus
     * @param msgText
     * @param id
     * @param isModerator
     * @param userId
     * @param reputation
     * @param topicId
     * @param userName
     * @param isAdmin
     * @param msgCount
     * @param msgRem
     */


    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(id)
        dest.writeValue(topicId)
        dest.writeValue(userId)
        dest.writeValue(userName)
        dest.writeValue(msgTime)
        dest.writeValue(msgText)
        dest.writeValue(userEmail)
        dest.writeValue(msgStatus)
        dest.writeValue(msgRem)
        dest.writeValue(reputation)
        dest.writeValue(isAdmin)
        dest.writeValue(isModerator)
        dest.writeValue(msgCount)
        dest.writeValue(avatar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Message> = object : Creator<Message> {


            override fun createFromParcel(`in`: Parcel): Message {
                return Message(`in`)
            }

            override fun newArray(size: Int): Array<Message?> {
                return arrayOfNulls(size)
            }

        }
    }

}