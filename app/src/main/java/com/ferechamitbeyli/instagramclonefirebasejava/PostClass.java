package com.ferechamitbeyli.instagramclonefirebasejava;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostClass extends ArrayAdapter<String > {
    private final Activity context;
    private final ArrayList<String> userEmailsList;
    private final ArrayList<String> userCommentsList;
    private final ArrayList<String> userImageList;

    public PostClass(ArrayList<String> userEmailsList, ArrayList<String> userCommentsList, ArrayList<String> userImageList, Activity context) {
        super(context, R.layout.custom_view, userEmailsList);
        this.userEmailsList = userEmailsList;
        this.userCommentsList = userCommentsList;
        this.userImageList = userImageList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_view, null, true);

        TextView userEmailText = view.findViewById(R.id.CustomViewUserEmailTextViewId);
        TextView commentText = view.findViewById(R.id.CustomViewCommentTextViewId);
        ImageView imageView = view.findViewById(R.id.CustomViewImageViewId);

        userEmailText.setText(userEmailsList.get(position));
        commentText.setText(userCommentsList.get(position));
        Picasso.get().load(userImageList.get(position)).into(imageView);
        return view;
    }
}
