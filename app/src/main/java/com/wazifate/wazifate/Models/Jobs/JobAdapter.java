package com.wazifate.wazifate.Models.Jobs;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.wazifate.wazifate.JobTestScreen;
import com.wazifate.wazifate.R;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private List<Job> jobs;

    // Constructor to initialize the list of jobs
    public JobAdapter(List<Job> jobs) {
        this.jobs = jobs;
    }

    // ViewHolder for each item in the RecyclerView
    public static class JobViewHolder extends RecyclerView.ViewHolder {
        public TextView jobTitleTextView;
        public TextView locationTextView;
        public TextView jobTypeTextView;
        public TextView salaryRangeTextView;
        public Button applyNowButton;

        public JobViewHolder(View itemView) {
            super(itemView);
            jobTitleTextView = itemView.findViewById(R.id.text_job_title);
            locationTextView = itemView.findViewById(R.id.text_location);
            jobTypeTextView = itemView.findViewById(R.id.text_job_type);
            salaryRangeTextView = itemView.findViewById(R.id.text_salary_range);
            applyNowButton = itemView.findViewById(R.id.button_apply_now);
        }
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_job, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, final int position) {
        Job job = jobs.get(position);

        holder.jobTitleTextView.setText(job.getJobtitle());
        holder.locationTextView.setText(job.getLocation());
        holder.jobTypeTextView.setText(job.getJobtype());
        holder.salaryRangeTextView.setText("" + job.getMinSalary() + " - " + job.getMaxSalary());
        if(job.isHasTest())
        {
            holder.applyNowButton.setTag(job.getId());
            holder.applyNowButton.setText("Take Test");
        }else
        {
            holder.applyNowButton.setText("No Test Available");
            holder.applyNowButton.setEnabled(false);
        }

        holder.applyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), JobTestScreen.class);
                i.putExtra("jobId", Integer.parseInt(v.getTag().toString()));
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }
}
