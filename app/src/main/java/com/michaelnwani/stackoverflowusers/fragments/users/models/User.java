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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (lastModifiedDate != null ? !lastModifiedDate.equals(user.lastModifiedDate) : user.lastModifiedDate != null)
            return false;
        if (displayName != null ? !displayName.equals(user.displayName) : user.displayName != null)
            return false;
        if (badgeCounts != null ? !badgeCounts.equals(user.badgeCounts) : user.badgeCounts != null)
            return false;
        return profileImageUrl != null ? profileImageUrl.equals(user.profileImageUrl) : user.profileImageUrl == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (badgeCounts != null ? badgeCounts.hashCode() : 0);
        result = 31 * result + (profileImageUrl != null ? profileImageUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", lastModifiedDate='" + lastModifiedDate + '\'' +
                ", displayName='" + displayName + '\'' +
                ", badgeCounts=" + badgeCounts +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                '}';
    }
}
