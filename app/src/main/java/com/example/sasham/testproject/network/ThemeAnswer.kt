package com.example.sasham.testproject.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ThemeAnswer {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("forum_row")
    @Expose
    var forumRow: String? = null
    @SerializedName("forum_col")
    @Expose
    var forumCol: String? = null
    @SerializedName("topic_icon")
    @Expose
    var topicIcon: String? = null
    @SerializedName("user_id")
    @Expose
    var userId: String? = null
    @SerializedName("user_name")
    @Expose
    var userName: String? = null
    @SerializedName("topic_text")
    @Expose
    var topicText: String? = null
    @SerializedName("msg_text")
    @Expose
    var msgText: String? = null
    @SerializedName("msg_time")
    @Expose
    var msgTime: String? = null
    @SerializedName("user_ip")
    @Expose
    var userIp: String? = null
    @SerializedName("topic_updated")
    @Expose
    var topicUpdated: String? = null
    @SerializedName("msg_status")
    @Expose
    var msgStatus: String? = null
    @SerializedName("msg_rem")
    @Expose
    var msgRem: String? = null
    @SerializedName("isAdmin")
    @Expose
    var isAdmin: String? = null
    @SerializedName("isModerator")
    @Expose
    var isModerator: String? = null
    @SerializedName("bonusID")
    @Expose
    var bonusID: String? = null
    @SerializedName("topic_view")
    @Expose
    var topicView: String? = null
    @SerializedName("forumName")
    @Expose
    var forumName: String? = null

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    /**
     * @param userIp
     * @param topicUpdated
     * @param forumName
     * @param topicIcon
     * @param msgTime
     * @param msgStatus
     * @param forumCol
     * @param msgText
     * @param id
     * @param isModerator
     * @param forumRow
     * @param userId
     * @param userName
     * @param isAdmin
     * @param topicView
     * @param bonusID
     * @param topicText
     * @param msgRem
     */
    constructor(id: String, forumRow: String, forumCol: String, topicIcon: String, userId: String, userName: String, topicText: String, msgText: String, msgTime: String, userIp: String, topicUpdated: String, msgStatus: String, msgRem: String, isAdmin: String, isModerator: String, bonusID: String, topicView: String, forumName: String) : super() {
        this.id = id
        this.forumRow = forumRow
        this.forumCol = forumCol
        this.topicIcon = topicIcon
        this.userId = userId
        this.userName = userName
        this.topicText = topicText
        this.msgText = msgText
        this.msgTime = msgTime
        this.userIp = userIp
        this.topicUpdated = topicUpdated
        this.msgStatus = msgStatus
        this.msgRem = msgRem
        this.isAdmin = isAdmin
        this.isModerator = isModerator
        this.bonusID = bonusID
        this.topicView = topicView
        this.forumName = forumName
    }

}