package com.wazifate.wazifate.HomeScreen.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.wazifate.wazifate.ApiService.ApiService;
import com.wazifate.wazifate.ApiService.RetrofitClient;
import com.wazifate.wazifate.Models.Jobs.Job;
import com.wazifate.wazifate.Models.Jobs.JobAdapter;
import com.wazifate.wazifate.R;
import com.wazifate.wazifate.databinding.FragmentHomeBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private RecyclerView jobsListView;

    private ApiService apiService;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        jobsListView = root.findViewById(R.id.jobs_listview);

        apiService = RetrofitClient.getInstance().getRestApi();

        apiService.getJobsList().enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                List<Job> jobs = response.body();
                JobAdapter adapter = new JobAdapter(jobs);
                jobsListView.setAdapter(adapter);
                jobsListView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                System.out.println("Failed to fetch jobs" + t.getMessage());
                Snackbar.make(root, "Failed to fetch jobs", Snackbar.LENGTH_LONG).show();
            }
        });




//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), R.layout.list_item_job, R.id.text_job_title, jobTitles) {
//            @Override
//            public View getView(final int position, View convertView, ViewGroup parent) {
//                View view = super.getView(position, convertView, parent);
//
//                TextView locationTextView = view.findViewById(R.id.text_location);
//                locationTextView.setText(locations[position]);
//
//                TextView jobTypeTextView = view.findViewById(R.id.text_job_type);
//                jobTypeTextView.setText(jobType[position]);
//
//                TextView salaryRangeTextView = view.findViewById(R.id.text_salary_range);
//                salaryRangeTextView.setText(salaryRanges[position]);
//
//                Button applyNowButton = view.findViewById(R.id.button_apply_now);
//                applyNowButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Handle Apply Now button click, e.g., launch apply activity
//                        // You can use position to identify which job was clicked
//                    }
//                });
//
//                return view;
//            }
//        };
//
//        jobsListView.setAdapter(adapter);





        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}