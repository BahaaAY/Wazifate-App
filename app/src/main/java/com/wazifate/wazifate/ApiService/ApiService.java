package com.wazifate.wazifate.ApiService;

import com.wazifate.wazifate.Models.Jobs.Job;
import com.wazifate.wazifate.Models.auth.AuthResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


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


}
