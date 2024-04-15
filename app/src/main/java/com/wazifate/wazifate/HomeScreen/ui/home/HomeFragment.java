package com.wazifate.wazifate.HomeScreen.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.wazifate.wazifate.R;
import com.wazifate.wazifate.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private ListView jobsListView;

    String[] jobTitles = {
            "Software Engineer",
            "Marketing Manager",
            "Accountant",
            "Graphic Designer",
            "Project Manager",
            "Sales Representative",
            "Data Analyst",
            "Human Resources Manager",
            "Financial Analyst",
            "Customer Service Representative",
            "Web Developer",
            "Administrative Assistant",
            "Software Developer",
            "Operations Manager",
            "Content Writer",
            "Executive Assistant",
            "Business Analyst",
            "Product Manager",
            "UX/UI Designer",
            "IT Support Specialist",
            "Social Media Manager",
            "Network Engineer",
            "Mechanical Engineer",
            "Electrical Engineer",
            "Civil Engineer",
            "Medical Assistant",
            "Registered Nurse",
            "Pharmacist"};
    String[] jobType = {
            "Full-time",
            "Part-time",
            "Full-time",
            "Part-time",
            "Full-time",
            "Part-time",
            "Full-time",
            "Part-time",
            "Full-time",
            "Part-time",
            "Full-time",
            "Part-time",
            "Full-time",
            "Part-time",
            "Full-time",
            "Part-time",
            "Full-time",
            "Part-time",
            "Full-time",
            "Part-time",
            "Full-time",
            "Part-time",
            "Full-time",
            "Part-time",
            "Full-time",
            "Part-time",
            "Full-time",
            "Part-time"
    };
    String[] locations = {
            "Beirut",
            "Riyadh",
            "Dubai",
            "Cairo",
            "Amman",
            "Jeddah",
            "Abu Dhabi",
            "Alexandria",
            "Sharjah",
            "Giza",
            "Zarqa",
            "Ajman",
            "Irbid",
            "Mecca",
            "Luxor",
            "Sidon",
            "Tripoli",
            "Jounieh",
            "Irbid",
            "Medina",
            "Gaza",
            "Fayoum",
            "Aswan",
            "Taif",
            "Heliopolis",
            "Nasr City",
            "Fujairah",
            "Dibba",
            "Abha",
            "Madaba"
    };

    String[] salaryRanges = {
            "$60,000 - $100,000",
            "$40,000 - $70,000",
            "$50,000 - $80,000",
            "$70,000 - $120,000",
            "$45,000 - $75,000",
            "$55,000 - $90,000",
            "$80,000 - $130,000",
            "$35,000 - $60,000",
            "$65,000 - $110,000",
            "$50,000 - $85,000",
            "$75,000 - $125,000",
            "$40,000 - $65,000",
            "$70,000 - $115,000",
            "$90,000 - $150,000",
            "$55,000 - $95,000",
            "$30,000 - $50,000",
            "$85,000 - $140,000",
            "$60,000 - $100,000",
            "$70,000 - $120,000",
            "$40,000 - $70,000",
            "$50,000 - $80,000",
            "$65,000 - $110,000",
            "$75,000 - $125,000",
            "$85,000 - $140,000",
            "$90,000 - $150,000",
            "$55,000 - $95,000",
            "$60,000 - $100,000",
            "$75,000 - $125,000",
            "$70,000 - $120,000",
            "$85,000 - $140,000",
            "$50,000 - $85,000",
            "$65,000 - $110,000",
            "$45,000 - $75,000",
            "$55,000 - $90,000",
            "$70,000 - $115,000",
            "$90,000 - $150,000",
            "$80,000 - $130,000",
            "$40,000 - $65,000",
            "$75,000 - $125,000",
            "$70,000 - $120,000",
            "$50,000 - $80,000",
            "$60,000 - $100,000",
            "$85,000 - $140,000",
            "$65,000 - $110,000",
            "$55,000 - $95,000",
            "$30,000 - $50,000",
            "$40,000 - $70,000",
            "$75,000 - $125,000",
            "$55,000 - $90,000"
    };



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        jobsListView = root.findViewById(R.id.jobs_listview);

//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), R.layout.list_item_job, R.id.text_job_title, jobTitles) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView locationTextView = view.findViewById(R.id.text_location);
                locationTextView.setText(locations[position]);

                TextView jobTypeTextView = view.findViewById(R.id.text_job_type);
                jobTypeTextView.setText(jobType[position]);

                TextView salaryRangeTextView = view.findViewById(R.id.text_salary_range);
                salaryRangeTextView.setText(salaryRanges[position]);

                Button applyNowButton = view.findViewById(R.id.button_apply_now);
                applyNowButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle Apply Now button click, e.g., launch apply activity
                        // You can use position to identify which job was clicked
                    }
                });

                return view;
            }
        };

        jobsListView.setAdapter(adapter);





        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}