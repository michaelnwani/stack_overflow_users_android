package com.michaelnwani.stackoverflowusers.fragments.users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.michaelnwani.stackoverflowusers.R;
import com.michaelnwani.stackoverflowusers.fragments.users.adapters.UserAdapter;
import com.michaelnwani.stackoverflowusers.fragments.users.loaders.UserLoader;
import com.michaelnwani.stackoverflowusers.fragments.users.models.User;

import java.util.List;

public class UsersFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<User>> {
    private static final String TAG = "UsersFragment";
    public static final int OPERATION_USERS_LOADER = 1;
    private UserLoader mUserLoader;
    private UserAdapter mUserAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        mUserAdapter = new UserAdapter(getContext(), R.layout.layout_list_view_row_item_user);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(OPERATION_USERS_LOADER, null, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_users, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.list_item_user);
        listView.setAdapter(mUserAdapter);

        return rootView;
    }

    @Override
    public Loader<List<User>> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader");
        if (mUserLoader == null) {
            mUserLoader = new UserLoader(getContext());
        }
        return mUserLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<User>> loader, List<User> data) {
        Log.d(TAG, "onLoadFinished");
        // after getting result we will update our UI here
        if (!data.isEmpty()) {
            Log.d(TAG, "data[0]: " + data.get(0));
        }
        mUserAdapter.addAll(data);
        mUserAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<User>> loader) {}

    public static UsersFragment newInstance() {
        return new UsersFragment();
    }
}
