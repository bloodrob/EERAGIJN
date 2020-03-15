package com.dev.r19.SakoriSarothiClient;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UnsignUserActivityViewHolder extends RecyclerView.ViewHolder {
    public TextView jobDateTextView;
    public TextView jobNameTextView;
    public UnsignUserActivityViewHolder(@NonNull View itemView) {
        super(itemView);

        jobDateTextView = (TextView)itemView.findViewById(R.id.jobdate_textview);
        jobNameTextView = (TextView)itemView.findViewById(R.id.jobname_textview);
    }
}
