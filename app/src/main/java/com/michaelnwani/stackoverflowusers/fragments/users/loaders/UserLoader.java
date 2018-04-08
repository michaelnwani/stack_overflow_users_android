package com.michaelnwani.stackoverflowusers.fragments.users.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.michaelnwani.stackoverflowusers.api.StackOverflowClient;
import com.michaelnwani.stackoverflowusers.fragments.users.models.User;

import java.io.IOException;
import java.util.List;

public class UserLoader extends AsyncTaskLoader<List<User>> {
    private static final String TAG = "UserLoader";

    public UserLoader(Context context) {
        super(context);
    }

    @Override
    public List<User> loadInBackground() {
        List<User> users = null;
        try {
            users = StackOverflowClient.getUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void deliverResult(List<User> data) {
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        if (isStarted()) {
            forceLoad();
        }
    }
}
