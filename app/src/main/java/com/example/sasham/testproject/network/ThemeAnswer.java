package com.example.sasham.testproject.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThemeAnswer {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("forum_row")
    @Expose
    private String forumRow;
    @SerializedName("forum_col")
    @Expose
    private String forumCol;
    @SerializedName("topic_icon")
    @Expose
    private String topicIcon;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("topic_text")
    @Expose
    private String topicText;
    @SerializedName("msg_text")
    @Expose
    private String msgText;
    @SerializedName("msg_time")
    @Expose
    private String msgTime;
    @SerializedName("user_ip")
    @Expose
    private String userIp;
    @SerializedName("topic_updated")
    @Expose
    private String topicUpdated;
    @SerializedName("msg_status")
    @Expose
    private String msgStatus;
    @SerializedName("msg_rem")
    @Expose
    private String msgRem;
    @SerializedName("isAdmin")
    @Expose
    private String isAdmin;
    @SerializedName("isModerator")
    @Expose
    private String isModerator;
    @SerializedName("bonusID")
    @Expose
    private String bonusID;
    @SerializedName("topic_view")
    @Expose
    private String topicView;
    @SerializedName("forumName")
    @Expose
    private String forumName;

    /**
     * No args constructor for use in serialization
     */
    public ThemeAnswer() {
    }

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
    public ThemeAnswer(String id, String forumRow, String forumCol, String topicIcon, String userId, String userName, String topicText, String msgText, String msgTime, String userIp, String topicUpdated, String msgStatus, String msgRem, String isAdmin, String isModerator, String bonusID, String topicView, String forumName) {
        super();
        this.id = id;
        this.forumRow = forumRow;
        this.forumCol = forumCol;
        this.topicIcon = topicIcon;
        this.userId = userId;
        this.userName = userName;
        this.topicText = topicText;
        this.msgText = msgText;
        this.msgTime = msgTime;
        this.userIp = userIp;
        this.topicUpdated = topicUpdated;
        this.msgStatus = msgStatus;
        this.msgRem = msgRem;
        this.isAdmin = isAdmin;
        this.isModerator = isModerator;
        this.bonusID = bonusID;
        this.topicView = topicView;
        this.forumName = forumName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getForumRow() {
        return forumRow;
    }

    public void setForumRow(String forumRow) {
        this.forumRow = forumRow;
    }

    public String getForumCol() {
        return forumCol;
    }

    public void setForumCol(String forumCol) {
        this.forumCol = forumCol;
    }

    public String getTopicIcon() {
        return topicIcon;
    }

    public void setTopicIcon(String topicIcon) {
        this.topicIcon = topicIcon;
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

    public String getTopicText() {
        return topicText;
    }

    public void setTopicText(String topicText) {
        this.topicText = topicText;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getTopicUpdated() {
        return topicUpdated;
    }

    public void setTopicUpdated(String topicUpdated) {
        this.topicUpdated = topicUpdated;
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

    public String getBonusID() {
        return bonusID;
    }

    public void setBonusID(String bonusID) {
        this.bonusID = bonusID;
    }

    public String getTopicView() {
        return topicView;
    }

    public void setTopicView(String topicView) {
        this.topicView = topicView;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

}