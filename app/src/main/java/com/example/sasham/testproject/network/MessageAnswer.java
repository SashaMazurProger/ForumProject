package com.example.sasham.testproject.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageAnswer {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("topic_id")
    @Expose
    private String topicId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("msg_time")
    @Expose
    private String msgTime;
    @SerializedName("msg_text")
    @Expose
    private String msgText;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("msg_status")
    @Expose
    private String msgStatus;
    @SerializedName("msg_rem")
    @Expose
    private String msgRem;
    @SerializedName("reputation")
    @Expose
    private String reputation;
    @SerializedName("isAdmin")
    @Expose
    private String isAdmin;
    @SerializedName("isModerator")
    @Expose
    private String isModerator;
    @SerializedName("MsgCount")
    @Expose
    private String msgCount;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("bonusID")
    @Expose
    private String bonusID;

    /**
     * No args constructor for use in serialization
     *
     */
    public MessageAnswer() {
    }

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
    public MessageAnswer(String id, String topicId, String userId, String userName, String msgTime, String msgText, String userEmail, String msgStatus, String msgRem, String reputation, String isAdmin, String isModerator, String msgCount, String avatar, String bonusID) {
        super();
        this.id = id;
        this.topicId = topicId;
        this.userId = userId;
        this.userName = userName;
        this.msgTime = msgTime;
        this.msgText = msgText;
        this.userEmail = userEmail;
        this.msgStatus = msgStatus;
        this.msgRem = msgRem;
        this.reputation = reputation;
        this.isAdmin = isAdmin;
        this.isModerator = isModerator;
        this.msgCount = msgCount;
        this.avatar = avatar;
        this.bonusID = bonusID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getMsgRem() {
        return msgRem;
    }

    public void setMsgRem(String msgRem) {
        this.msgRem = msgRem;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getIsModerator() {
        return isModerator;
    }

    public void setIsModerator(String isModerator) {
        this.isModerator = isModerator;
    }

    public String getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(String msgCount) {
        this.msgCount = msgCount;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBonusID() {
        return bonusID;
    }

    public void setBonusID(String bonusID) {
        this.bonusID = bonusID;
    }

}
