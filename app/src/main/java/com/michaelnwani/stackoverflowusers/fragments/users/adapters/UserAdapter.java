package com.michaelnwani.stackoverflowusers.fragments.users.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.michaelnwani.stackoverflowusers.R;
import com.michaelnwani.stackoverflowusers.fragments.users.models.User;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.layout_list_view_row_item_user, parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            // view is being recycled; retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final User user = getItem(position);
        if (user != null) {
            viewHolder.username.setText(user.getDisplayName());

            ProfileImageLoaderTask profileImageLoaderTask = new ProfileImageLoaderTask(viewHolder.profileImage);
            profileImageLoaderTask.execute(user.getProfileImageUrl());
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView username;
        ImageView profileImage;

        ViewHolder(View view) {
            username = (TextView) view.findViewById(R.id.tvUsername);
            profileImage = (ImageView) view.findViewById(R.id.ivProfile);
        }
    }

    private static class ProfileImageLoaderTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;

        private ProfileImageLoaderTask(ImageView imageView) {
            imageViewReference = new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            return loadBitmap(strings[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }

        private Bitmap loadBitmap(String src) {
            InputStream inputStream = null;
            Bitmap bitmap = null;

            try {
                URL url = new URL(src);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(false);
                inputStream = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return bitmap;
        }
    }
}
