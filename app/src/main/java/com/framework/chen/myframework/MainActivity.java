package com.framework.chen.myframework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.framework.chen.myframework.Entity.VideoInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Response;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestNet();
    }

    public interface BlogService {
        @GET("attendance/getAllLocation")
        Call<VideoInfo> getBlog();
    }

    public void requestNet() {
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://amidgame.cn/api/")
                //可以接收自定义的Gson，当然也可以不传
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        BlogService service = retrofit.create(BlogService.class);
        Call<VideoInfo> call = service.getBlog();
        call.enqueue(new Callback<VideoInfo>() {
            @Override
            public void onResponse(Call<VideoInfo> call, Response<VideoInfo> response) {
                // 已经转换为想要的类型了
                VideoInfo result = response.body();
                System.out.println(result.toString());
            }

            @Override
            public void onFailure(Call<VideoInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
