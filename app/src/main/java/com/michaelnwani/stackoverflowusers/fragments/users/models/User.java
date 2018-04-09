package com.michaelnwani.stackoverflowusers.fragments.users.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("user_id")
    private String id;

    @SerializedName("last_modified_date")
    private String lastModifiedDate;

    @SerializedName("display_name")
    private String displayName;

    @SerializedName("badge_counts")
    private UserBadgeCounts badgeCounts;

    @SerializedName("profile_image")
    private String profileImageUrl;

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UserBadgeCounts getBadgeCounts() {
        return badgeCounts;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!displayName.equals(user.displayName)) {
            return false;
        }

        if (!badgeCounts.equals(user.badgeCounts)) {
            return false;
        }

        return profileImageUrl.equals(user.profileImageUrl);
    }

    @Override
    public int hashCode() {
        int result = displayName != null ? displayName.hashCode() : 0;
        result = 31 * result + (badgeCounts != null ? badgeCounts.hashCode() : 0);
        result = 31 * result + (profileImageUrl != null ? profileImageUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "displayName='" + displayName + '\'' +
                ", badgeCounts=" + badgeCounts +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                '}';
    }
}
