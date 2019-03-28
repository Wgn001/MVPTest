package gn.example.mvptest.model;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import gn.example.mvptest.HttpUtils.Api;
import gn.example.mvptest.HttpUtils.Https;
import gn.example.mvptest.bean.Move;
import gn.example.mvptest.presenter.NewsPresenter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsModel implements IMode {

    private static final String TAG = "NewsModel";
    
    private NewsPresenter newsPresenter;
    private String data="";

    public NewsModel(NewsPresenter newsPresenter) {
        this.newsPresenter = newsPresenter;
    }

    @Override
    public void getData(Map<String, String> map) {
        String url= Https.newsApi+"?key="+map.get("key")+"&num="+map.get("num");
        Log.i(TAG,url);
        Api.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                data=response.body().string();
                Move move = Api.StringToJson(data, Move.class);
                newsPresenter.getDatas(move);
                Log.i(TAG,data);
            }
        });


    }
}
