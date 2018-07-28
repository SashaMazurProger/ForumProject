package com.example.sasham.testproject.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Theme implements Parcelable {

    private String id;
    private String userId;
    private String userName;
    private String topicText;
    private String msgText;
    private String msgTime;
    private String topicUpdated;
    private String isAdmin;
    private String isModerator;
    private String forumName;
    public final static Parcelable.Creator<Theme> CREATOR = new Creator<Theme>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Theme createFromParcel(Parcel in) {
            return new Theme(in);
        }

        public Theme[] newArray(int size) {
            return (new Theme[size]);
        }

    };

    protected Theme(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.topicText = ((String) in.readValue((String.class.getClassLoader())));
        this.msgText = ((String) in.readValue((String.class.getClassLoader())));
        this.msgTime = ((String) in.readValue((String.class.getClassLoader())));
        this.topicUpdated = ((String) in.readValue((String.class.getClassLoader())));
        this.isAdmin = ((String) in.readValue((String.class.getClassLoader())));
        this.isModerator = ((String) in.readValue((String.class.getClassLoader())));
        this.forumName = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Theme() {
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
    public Theme(String id, String userId, String userName, String topicText, String msgText, String msgTime, String topicUpdated, String isAdmin, String isModerator, String forumName) {
        super();
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.topicText = topicText;
        this.msgText = msgText;
        this.msgTime = msgTime;
        this.topicUpdated = topicUpdated;
        this.isAdmin = isAdmin;
        this.isModerator = isModerator;
        this.forumName = forumName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTopicUpdated() {
        return topicUpdated;
    }

    public void setTopicUpdated(String topicUpdated) {
        this.topicUpdated = topicUpdated;
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

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(userId);
        dest.writeValue(userName);
        dest.writeValue(topicText);
        dest.writeValue(msgText);
        dest.writeValue(msgTime);
        dest.writeValue(topicUpdated);
        dest.writeValue(isAdmin);
        dest.writeValue(isModerator);
        dest.writeValue(forumName);
    }

    public int describeContents() {
        return 0;
    }

}