package gn.example.mvptest.HttpUtils;


import com.google.gson.Gson;

import gn.example.mvptest.bean.Move;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Api {

    public static void sendOkHttpRequest(String url,Callback callback) {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().get().url(url).build();
        client.newCall(request).enqueue(callback);
    }

    public static <T> T StringToJson(String data,Class<T> clazz){

        Gson gson=new Gson();
        T t = gson.fromJson(data, clazz);
        return t;
    }
}
