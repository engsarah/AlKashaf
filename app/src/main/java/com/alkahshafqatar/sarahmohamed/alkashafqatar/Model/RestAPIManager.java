package com.alkahshafqatar.sarahmohamed.alkashafqatar.Model;

import android.net.wifi.hotspot2.pps.Credential;

import com.alkahshafqatar.sarahmohamed.alkashafqatar.DataModel.Project;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.DataModel.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestAPIManager {

    @GET("/api/Projects/")
    Call<List<Project>> fetchProjects(@Query("USER_KEY") String status);

    @POST("/api/LoginData/Login/")
    Call<String> authenticateUser(@Body User user);
}
