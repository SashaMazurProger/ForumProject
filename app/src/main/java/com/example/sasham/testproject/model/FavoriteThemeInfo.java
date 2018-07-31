package com.example.sasham.testproject.model;

public class FavoriteThemeInfo {
    private String themeId;
    private int countViewed;
    private long lastMessageTime;

    public FavoriteThemeInfo(String themeId, int countViewed, long lastMessageTime) {
        this.themeId = themeId;
        this.countViewed = countViewed;
        this.lastMessageTime = lastMessageTime;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public int getCountViewed() {
        return countViewed;
    }

    public void setCountViewed(int countViewed) {
        this.countViewed = countViewed;
    }

    public long getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(long lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }
}
