package com.example.android;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIServices {

    @POST("cervicalcancer/")
    Call<AuthResponseCancer> getCancerDetails(@Body CancerDetails details);

}
