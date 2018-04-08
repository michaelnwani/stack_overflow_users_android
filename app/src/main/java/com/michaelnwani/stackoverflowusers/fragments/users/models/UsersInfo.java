package com.michaelnwani.stackoverflowusers.fragments.users.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersInfo {
    @SerializedName("items")
    List<User> users;

    @SerializedName("has_more")
    Boolean hasMore;

    @SerializedName("quota_max")
    int quotaMax;

    @SerializedName("quota_remaining")
    int quotaRemaining;

    public List<User> getUsers() {
        return users;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public int getQuotaMax() {
        return quotaMax;
    }

    public int getQuotaRemaining() {
        return quotaRemaining;
    }
}
