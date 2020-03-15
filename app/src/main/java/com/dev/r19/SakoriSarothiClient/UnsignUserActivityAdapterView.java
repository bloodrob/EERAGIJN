package com.dev.r19.SakoriSarothiClient;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class UnsignUserActivityAdapterView extends RecyclerView.Adapter<UnsignUserActivityViewHolder> {
    private Context context1;
    private ArrayList<UnSignUserMainActivityModel> unSignJobList;
    private String myItem;

    public UnsignUserActivityAdapterView(Context context1, ArrayList<UnSignUserMainActivityModel> unSignJobList){
        this.context1 = context1;
        this.unSignJobList = unSignJobList;
    }
    @NonNull
    @Override
    public UnsignUserActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View unSignJobView = LayoutInflater.from(context1).inflate(R.layout.unsign_user_activity_cardview, parent, false);
        UnsignUserActivityViewHolder unSignViewHolder = new UnsignUserActivityViewHolder(unSignJobView);
        return unSignViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UnsignUserActivityViewHolder holder, final int position) {
        final UnSignUserMainActivityModel unsignModel = unSignJobList.get(position);
        holder.jobDateTextView.setText(unsignModel.getLastDate());
        holder.jobNameTextView.setText(unsignModel.getJobName());

        holder.jobNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myItem = String.valueOf(unsignModel.getJobName());
                Toast.makeText(context1, "Value is "+myItem, Toast.LENGTH_SHORT).show();
                checkCurrentUserStatus();
            }
        });

    }

    @Override
    public int getItemCount() {
        return unSignJobList.size();
    }

    private void checkCurrentUserStatus() {
        //check current user status
       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(context1, UserHome.class);
            intent.putExtra("job_name",myItem );
            context1.startActivity(intent);
        }
    }
}
