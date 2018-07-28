package com.example.sasham.testproject.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Message implements Parcelable {

    private String id;
    private String topicId;
    private String userId;
    private String userName;
    private String msgTime;
    private String msgText;
    private String userEmail;
    private String msgStatus;
    private String msgRem;
    private String reputation;
    private String isAdmin;
    private String isModerator;
    private String msgCount;
    private String avatar;
    public final static Parcelable.Creator<Message> CREATOR = new Creator<Message>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        public Message[] newArray(int size) {
            return (new Message[size]);
        }

    };

    protected Message(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.topicId = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.msgTime = ((String) in.readValue((String.class.getClassLoader())));
        this.msgText = ((String) in.readValue((String.class.getClassLoader())));
        this.userEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.msgStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.msgRem = ((String) in.readValue((String.class.getClassLoader())));
        this.reputation = ((String) in.readValue((String.class.getClassLoader())));
        this.isAdmin = ((String) in.readValue((String.class.getClassLoader())));
        this.isModerator = ((String) in.readValue((String.class.getClassLoader())));
        this.msgCount = ((String) in.readValue((String.class.getClassLoader())));
        this.avatar = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Message() {
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
    public Message(String id, String topicId, String userId, String userName, String msgTime, String msgText, String userEmail, String msgStatus, String msgRem, String reputation, String isAdmin, String isModerator, String msgCount, String avatar) {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(topicId);
        dest.writeValue(userId);
        dest.writeValue(userName);
        dest.writeValue(msgTime);
        dest.writeValue(msgText);
        dest.writeValue(userEmail);
        dest.writeValue(msgStatus);
        dest.writeValue(msgRem);
        dest.writeValue(reputation);
        dest.writeValue(isAdmin);
        dest.writeValue(isModerator);
        dest.writeValue(msgCount);
        dest.writeValue(avatar);
    }

    public int describeContents() {
        return 0;
    }

}