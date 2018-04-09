package com.michaelnwani.stackoverflowusers.fragments.users.models;


public class User {
    private String id;

    private String lastModifiedDate;

    private String displayName;

    private UserBadgeCounts badgeCounts;

    private String profileImageUrl;

    public void setId(String id) {
        this.id = id;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setBadgeCounts(UserBadgeCounts badgeCounts) {
        this.badgeCounts = badgeCounts;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

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
