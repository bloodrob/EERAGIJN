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

public class UserSearchCustomJobAdapterView extends RecyclerView.Adapter<UserSearchCustomJobViewHolder> {
    private Context customJobContext;
    private ArrayList<UserSearchCustomJobModel> customJobList;
    private String selectedCustomJob;

    public UserSearchCustomJobAdapterView(Context customJobContext, ArrayList<UserSearchCustomJobModel> customJobList){
        this.customJobContext = customJobContext;
        this.customJobList = customJobList;
    }
    @NonNull
    @Override
    public UserSearchCustomJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View customJobView = LayoutInflater.from(customJobContext).inflate(R.layout.user_search_customjob_cardview, parent, false);
        UserSearchCustomJobViewHolder customJobVHolder = new UserSearchCustomJobViewHolder(customJobView);
        return customJobVHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserSearchCustomJobViewHolder holder, int position) {
        final UserSearchCustomJobModel customJobMod = customJobList.get(position);
        holder.customJobDateTextview.setText(customJobMod.getLastDate());
        holder.customJobNameTextview.setText(customJobMod.getJobName());

        holder.customJobNameTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCustomJob = String.valueOf(customJobMod.getJobName());
                Toast.makeText(customJobContext, "selected Job Is : "+selectedCustomJob, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(customJobContext, UserSearchCustomJobDetails.class);
                intent.putExtra("id", "cusSerJob");
                intent.putExtra("nameJob", selectedCustomJob);
                customJobContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return customJobList.size();
    }
}
