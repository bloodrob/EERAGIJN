package com.dev.r19.SakoriSarothiClient;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserSearchAllJobAdapterView extends RecyclerView.Adapter<UserSearchAllJobViewHolder> {
    private Context allJobContext;
    private ArrayList<UserSearchAllJobModel> allJobList;
    private String selectedAllJobName;

    public UserSearchAllJobAdapterView(Context allJobContext, ArrayList<UserSearchAllJobModel> allJobList){
        this.allJobContext = allJobContext;
        this.allJobList = allJobList;
    }
    @NonNull
    @Override
    public UserSearchAllJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View allJobView = LayoutInflater.from(allJobContext).inflate(R.layout.all_job_cardview, parent, false);
        UserSearchAllJobViewHolder allJobViewHolder = new UserSearchAllJobViewHolder(allJobView);
        return allJobViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserSearchAllJobViewHolder holder, final int position) {
        final UserSearchAllJobModel allJobModel1 = allJobList.get(position);
        holder.allJobDateTextView.setText(allJobModel1.getLastDate());
        holder.allJobNameTextView.setText(allJobModel1.getJobName());

        holder.allJobNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAllJobName = String.valueOf(allJobModel1.getJobName());
                Toast.makeText(allJobContext, "Selected Job Is : "+selectedAllJobName, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(allJobContext, UserSearchCustomJobDetails.class);
                intent.putExtra("id", "allSerJob");
                intent.putExtra("nameOfJob", selectedAllJobName);
                allJobContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allJobList.size();
    }
}
