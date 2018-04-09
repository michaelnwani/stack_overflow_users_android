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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserAdapter extends ArrayAdapter<User> {
    private static final String TAG = "UserAdapter";
    private static WeakReference<Context> contextWeakReference;

    public UserAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.contextWeakReference = new WeakReference<>(context);
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
            profileImageLoaderTask.execute(user);
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

    private static class ProfileImageLoaderTask extends AsyncTask<User, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;

        private ProfileImageLoaderTask(ImageView imageView) {
            imageViewReference = new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(User... users) {
            User user = users[0];
            return fetchBitmap(user);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    private static Bitmap fetchBitmap(User user) {
        final String imageName = user.getId() + "_" + user.getLastModifiedDate() + ".png";
        Bitmap bitmap = fetchStoredProfileImage(imageName);
        if (bitmap == null) {
            bitmap = loadProfileImage(user.getProfileImageUrl());
            storeProfileImage(bitmap, imageName);
        }
        return bitmap;
    }

    private static Bitmap loadProfileImage(String profileImageUrl) {
        InputStream inputStream = null;
        Bitmap bitmap = null;

        try {
            URL url = new URL(profileImageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            inputStream = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return bitmap;
    }

    private static Bitmap fetchStoredProfileImage(String imageName) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = getMediaStorageDirectory();

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            return null;
        }

        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + imageName);
        return BitmapFactory.decodeFile(mediaFile.getAbsolutePath());
    }

    private static void storeProfileImage(Bitmap bitmap, String imageName) {
        File mediaStorageDir = getMediaStorageDirectory();
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            return;
        }
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + imageName);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(mediaFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getMediaStorageDirectory() {
        Context context = contextWeakReference.get();
        return new File(context.getFilesDir().getPath());
    }
}
