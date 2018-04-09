package com.michaelnwani.stackoverflowusers.api;

import com.michaelnwani.stackoverflowusers.fragments.users.models.User;
import com.michaelnwani.stackoverflowusers.fragments.users.models.UserBadgeCounts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class StackOverflowClient {
    private static final String TAG = "StackOverflowClient";
    private static final String BASE_URL = "https://api.stackexchange.com/2.2";
    // the site parameter is always required
    private static final String USERS_URL = BASE_URL + "/users?site=stackoverflow";
    private static final String HEADER_NAME_ACCEPT = "Accept";
    private static final String MEDIA_TYPE_JSON = "application/json";

    private static HttpURLConnection openConnection(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty(HEADER_NAME_ACCEPT, MEDIA_TYPE_JSON);
        return conn;
    }

    public static List<User> getUsers() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        List<User> users = new ArrayList<>();
        HttpURLConnection conn = null;
        BufferedReader bufferedReader = null;

        try {
            conn = openConnection(USERS_URL);
            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String jsonString;
            while ((jsonString = bufferedReader.readLine()) != null) {
                stringBuilder.append(jsonString);
            }

            JSONObject jObject = new JSONObject(stringBuilder.toString());
            JSONArray usersJSONArray = jObject.getJSONArray("items");
            users = parseUsersFromJSONArray(usersJSONArray);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
                conn.disconnect();
            }
        }

        return users;
    }

    private static List<User> parseUsersFromJSONArray(JSONArray jsonArray) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                User user = new User();
                user.setId(jsonObject.getString("user_id"));
                user.setDisplayName(jsonObject.getString("display_name"));
                user.setLastModifiedDate(jsonObject.getString("last_modified_date"));
                user.setProfileImageUrl(jsonObject.getString("profile_image"));

                UserBadgeCounts badgeCounts = userBadgeCountsFromJSONObject(jsonObject.getJSONObject("badge_counts"));
                user.setBadgeCounts(badgeCounts);

                users.add(user);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    private static UserBadgeCounts userBadgeCountsFromJSONObject(JSONObject jsonObject) {
        UserBadgeCounts badgeCounts = new UserBadgeCounts();
        try {
            badgeCounts.setBronze(jsonObject.getInt("bronze"));
            badgeCounts.setSilver(jsonObject.getInt("silver"));
            badgeCounts.setGold(jsonObject.getInt("gold"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return badgeCounts;
    }
}
