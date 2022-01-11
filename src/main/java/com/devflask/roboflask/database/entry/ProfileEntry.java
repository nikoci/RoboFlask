package com.devflask.roboflask.database.entry;

import net.dv8tion.jda.api.entities.Member;

public class ProfileEntry implements Entry{

    private final Member member;

    //stats
    private int level;
    private int xp;
    private int xpToNextLevel;
    private int coins;
    private int messages;

    //statics
    private String profileBanner;
    private String profileBorder;
    private int profileColor;

    // private Reward[] rewards;

    public ProfileEntry(Member member, int level, int xp, int xpToNextLevel, int coins, int messages, String profileBanner, String profileBorder, int profileColor) {
        this.member = member;
        this.level = level;
        this.xp = xp;
        this.xpToNextLevel = xpToNextLevel;
        this.coins = coins;
        this.messages = messages;
        this.profileBanner = profileBanner;
        this.profileBorder = profileBorder;
        this.profileColor = profileColor;
    }

    public Member getMember() {
        return member;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getXpToNextLevel() {
        return xpToNextLevel;
    }

    public int getCoins() {
        return coins;
    }

    public int getMessages() {
        return messages;
    }

    public String getProfileBanner() {
        return profileBanner;
    }

    public String getProfileBorder() {
        return profileBorder;
    }

    public int getProfileColor() {
        return profileColor;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setXpToNextLevel(int xpToNextLevel) {
        this.xpToNextLevel = xpToNextLevel;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }

    public void setProfileBanner(String profileBanner) {
        this.profileBanner = profileBanner;
    }

    public void setProfileBorder(String profileBorder) {
        this.profileBorder = profileBorder;
    }

    public void setProfileColor(int profileColor) {
        this.profileColor = profileColor;
    }

    // public void addReward(Reward reward) {

}
