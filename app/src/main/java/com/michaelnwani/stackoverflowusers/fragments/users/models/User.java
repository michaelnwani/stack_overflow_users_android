package com.michaelnwani.stackoverflowusers.fragments.users.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("display_name")
    private String displayName;

    @SerializedName("badge_counts")
    private UserBadgeCounts badgeCounts;

    @SerializedName("profile_image")
    private String profileImageUrl;

    public String getDisplayName() {
        return displayName;
    }

    public UserBadgeCounts getBadgeCounts() {
        return badgeCounts;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
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
}
