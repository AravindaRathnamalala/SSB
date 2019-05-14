package com.epic.ssb.network;

import com.epic.ssb.data.AgentModel;
import com.epic.ssb.data.ArakshawaModel;
import com.epic.ssb.data.ResponseBean;
import com.epic.ssb.data.SurekumaModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @POST("login.htm")
    @FormUrlEncoded
    Observable<Response<ResponseBean>> login(@Field("username") String username, @Field("password") String password);

    @POST("agentRegistration.htm")
    @Headers("Content-Type: application/json")
    Observable<Response<ResponseBean>> agentRegister(@Body AgentModel agentModel);

    @POST("agentRegistration.htm")
    @Headers("Content-Type: application/json")
    Observable<Response<ResponseBean>> clientRegisterSurekuma(@Body SurekumaModel surekumaModel);

    @POST("agentRegistration.htm")
    @Headers("Content-Type: application/json")
    Observable<Response<ResponseBean>> clientRegisterArakshawa(@Body ArakshawaModel arakshawaModel);

    class Creator{

        public static ApiService newApiService(){

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.20.222/DFAR_MOBILE/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

            return retrofit.create(ApiService.class);
        }

    }
}
