package com.michaelnwani.stackoverflowusers.api;

import com.google.gson.Gson;
import com.michaelnwani.stackoverflowusers.fragments.users.models.User;
import com.michaelnwani.stackoverflowusers.fragments.users.models.UsersInfo;

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
        Gson gson = new Gson();
        StringBuilder stringBuilder = new StringBuilder();
        UsersInfo usersInfo = null;
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

            usersInfo = gson.fromJson(stringBuilder.toString(), UsersInfo.class);
            users = usersInfo.getUsers();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
                conn.disconnect();
            }
        }

        return users;
    }
}
