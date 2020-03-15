package com.dev.r19.SakoriSarothiClient;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserSearchAllJobViewHolder extends RecyclerView.ViewHolder {
    public TextView allJobNameTextView;
    public TextView allJobDateTextView;
    public UserSearchAllJobViewHolder(@NonNull View itemView) {
        super(itemView);

        allJobDateTextView = (TextView)itemView.findViewById(R.id.all_jobdate_textview);
        allJobNameTextView = (TextView)itemView.findViewById(R.id.all_jobname_textview);
    }
}
