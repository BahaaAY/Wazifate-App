package com.wazifate.wazifate.ApiService;

import com.wazifate.wazifate.Models.Jobs.Job;
import com.wazifate.wazifate.Models.Quiz.Quiz;
import com.wazifate.wazifate.Models.Quiz.QuizQuestion;
import com.wazifate.wazifate.Models.auth.AuthResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {
    @GET("job-posts.php")
    Call<List<Job>> getJobsList();




    @FormUrlEncoded
    @POST ("auth/login.php")
    Call<AuthResponse> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST ("auth/signup.php")
    Call<AuthResponse> signupUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("major") String major,
            @Field("phone") String phone,
            @Field("address") String address
    );


    @GET("quiz/quizzes.php")
    Call<List<Quiz>> getQuizzes();

    // get quiz questions quiz/quiz-questions.php?quizId=1
    @GET("quiz/quiz-questions.php?")
    Call<List<QuizQuestion>> getQuizQuestions(
            @Query("quizId") int quizId
    );
    @GET("test/test-questions.php?")
    Call<List<QuizQuestion>> getJobQuestions(
            @Query("jobId") int jobId
    );

    @GET("save-score.php?")
    Call<String> saveScore(
            @Query("score") int score,
            @Query("username") String username
    );

}
