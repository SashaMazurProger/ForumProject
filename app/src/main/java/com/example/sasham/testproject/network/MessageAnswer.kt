package com.example.sasham.testproject.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MessageAnswer {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("topic_id")
    @Expose
    var topicId: String? = null
    @SerializedName("user_id")
    @Expose
    var userId: String? = null
    @SerializedName("user_name")
    @Expose
    var userName: String? = null
    @SerializedName("msg_time")
    @Expose
    var msgTime: String? = null
    @SerializedName("msg_text")
    @Expose
    var msgText: String? = null
    @SerializedName("user_email")
    @Expose
    var userEmail: String? = null
    @SerializedName("msg_status")
    @Expose
    var msgStatus: String? = null
    @SerializedName("msg_rem")
    @Expose
    var msgRem: String? = null
    @SerializedName("reputation")
    @Expose
    var reputation: String? = null
    @SerializedName("isAdmin")
    @Expose
    var isAdmin: String? = null
    @SerializedName("isModerator")
    @Expose
    var isModerator: String? = null
    @SerializedName("MsgCount")
    @Expose
    var msgCount: String? = null
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null
    @SerializedName("bonusID")
    @Expose
    var bonusID: String? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
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
     * @param bonusID
     * @param msgCount
     * @param msgRem
     */
    constructor(id: String, topicId: String, userId: String, userName: String, msgTime: String, msgText: String, userEmail: String, msgStatus: String, msgRem: String, reputation: String, isAdmin: String, isModerator: String, msgCount: String, avatar: String, bonusID: String) : super() {
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
        this.bonusID = bonusID
    }

}
