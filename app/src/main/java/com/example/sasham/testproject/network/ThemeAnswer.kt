package com.example.sasham.testproject.network

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ThemeAnswer : Parcelable {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("forum_section_id")
    @Expose
    var forumSectionId: Int? = null
    @SerializedName("topic_icon")
    @Expose
    var topicIcon: Int? = null
    @SerializedName("topic_text")
    @Expose
    var topicText: String? = null
    @SerializedName("topic_updated")
    @Expose
    var topicUpdated: Int? = null
    @SerializedName("topic_view_count")
    @Expose
    var topicViewCount: Int? = null
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null
    @SerializedName("user_name")
    @Expose
    var userName: String? = null
    @SerializedName("user_ip")
    @Expose
    var userIp: String? = null
    @SerializedName("msg_text")
    @Expose
    var msgText: String? = null
    @SerializedName("msg_time")
    @Expose
    var msgTime: Int? = null

    protected constructor(`in`: Parcel) {
        this.id = `in`.readValue(Int::class.java.classLoader) as Int
        this.forumSectionId = `in`.readValue(Int::class.java.classLoader) as Int
        this.topicIcon = `in`.readValue(Int::class.java.classLoader) as Int
        this.topicText = `in`.readValue(String::class.java.classLoader) as String
        this.topicUpdated = `in`.readValue(Int::class.java.classLoader) as Int
        this.topicViewCount = `in`.readValue(Int::class.java.classLoader) as Int
        this.userId = `in`.readValue(Int::class.java.classLoader) as Int
        this.userName = `in`.readValue(String::class.java.classLoader) as String
        this.userIp = `in`.readValue(String::class.java.classLoader) as String
        this.msgText = `in`.readValue(String::class.java.classLoader) as String
        this.msgTime = `in`.readValue(Int::class.java.classLoader) as Int
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param id
     * @param forumSectionId
     * @param userIp
     * @param topicUpdated
     * @param topicIcon
     * @param userId
     * @param msgTime
     * @param userName
     * @param topicViewCount
     * @param topicText
     * @param msgText
     */
    constructor(id: Int?, forumSectionId: Int?, topicIcon: Int?, topicText: String, topicUpdated: Int?, topicViewCount: Int?, userId: Int?, userName: String, userIp: String, msgText: String, msgTime: Int?) : super() {
        this.id = id
        this.forumSectionId = forumSectionId
        this.topicIcon = topicIcon
        this.topicText = topicText
        this.topicUpdated = topicUpdated
        this.topicViewCount = topicViewCount
        this.userId = userId
        this.userName = userName
        this.userIp = userIp
        this.msgText = msgText
        this.msgTime = msgTime
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(id)
        dest.writeValue(forumSectionId)
        dest.writeValue(topicIcon)
        dest.writeValue(topicText)
        dest.writeValue(topicUpdated)
        dest.writeValue(topicViewCount)
        dest.writeValue(userId)
        dest.writeValue(userName)
        dest.writeValue(userIp)
        dest.writeValue(msgText)
        dest.writeValue(msgTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ThemeAnswer> = object : Creator<ThemeAnswer> {


            override fun createFromParcel(`in`: Parcel): ThemeAnswer {
                return ThemeAnswer(`in`)
            }

            override fun newArray(size: Int): Array<ThemeAnswer?> {
                return arrayOfNulls(size)
            }

        }
    }

}