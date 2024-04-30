package com.wazifate.wazifate.HomeScreen.ui.quiz_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wazifate.wazifate.ApiService.ApiService;
import com.wazifate.wazifate.ApiService.RetrofitClient;
import com.wazifate.wazifate.Models.Quiz.Quiz;
import com.wazifate.wazifate.Models.Quiz.QuizAdapter;
import com.wazifate.wazifate.R;
import com.wazifate.wazifate.databinding.FragmentQuizHomeBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizHomeFragment extends Fragment {

    private FragmentQuizHomeBinding binding;
    private RecyclerView quizListView;

    private ApiService apiService;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        QuizHomeViewModel quizHomeViewModel =
                new ViewModelProvider(this).get(QuizHomeViewModel.class);

        binding = FragmentQuizHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        apiService = RetrofitClient.getInstance().getRestApi();
        quizListView = root.findViewById(R.id.quizzes_listview);

        // get the list of quizzes from the server
        apiService.getQuizzes().enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, Response<List<Quiz>> response) {
                List<Quiz> quizzes = response.body();
                System.out.println("Quizzes: " + quizzes.get(0).toString());
                QuizAdapter adapter = new QuizAdapter(quizzes);
                quizListView.setAdapter(adapter);
                quizListView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {
                System.out.println("Failed to fetch quizzes" + t.getMessage());
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}