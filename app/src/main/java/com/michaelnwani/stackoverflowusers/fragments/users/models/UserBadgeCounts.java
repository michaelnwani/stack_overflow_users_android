package com.michaelnwani.stackoverflowusers.fragments.users.models;

public class UserBadgeCounts {
    private int bronze;
    private int silver;
    private int gold;

    public int getBronze() {
        return bronze;
    }

    public int getSilver() {
        return silver;
    }

    public int getGold() {
        return gold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserBadgeCounts that = (UserBadgeCounts) o;

        if (bronze != that.bronze) {
            return false;
        }

        if (silver != that.silver) {
            return false;
        }

        return gold == that.gold;
    }

    @Override
    public int hashCode() {
        int result = bronze;
        result = 31 * result + silver;
        result = 31 * result + gold;
        return result;
    }

    public String textViewText() {
        return "Bronze: " + getBronze() + ", Silver: " + getSilver() + ", Gold: " + getGold();
    }

    @Override
    public String toString() {
        return "UserBadgeCounts{" +
                "bronze=" + bronze +
                ", silver=" + silver +
                ", gold=" + gold +
                '}';
    }
}
