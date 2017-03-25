package com.framework.chen.myframework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.framework.chen.myframework.Entity.VideoInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestNet();
    }

    public interface VideoInfoService {
        @GET("attendance/getAllLocation")
        Observable<VideoInfo> getBlog();
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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        VideoInfoService service = retrofit.create(VideoInfoService.class);
        service.getBlog().subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<VideoInfo>() {
                @Override
                public void onCompleted() {
                    System.out.println("onCompleted");
                }

                @Override
                public void onError(Throwable e) {
                    System.err.println("onError:" + e.getMessage());
                }

                @Override
                public void onNext(VideoInfo blogsResult) {
                    System.out.println(blogsResult.toString());
                }
         });

    }
}
