package gn.example.mvptest.model;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import gn.example.mvptest.HttpUtils.Api;
import gn.example.mvptest.HttpUtils.Https;
import gn.example.mvptest.bean.Weather;
import gn.example.mvptest.presenter.NewsPresenter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherModel implements IModel {

    private static final String TAG = "WeatherModel";
    private NewsPresenter weatherPresenter;


    public WeatherModel(NewsPresenter weatherPresenter) {
        this.weatherPresenter = weatherPresenter;
    }

    @Override
    public void getData(Map<String, String> map) {
        String url= Https.Weather_Api+"?";
        for(String s:map.keySet()){
            url=url+s+"="+map.get(s)+"&";
        }
        url=url.substring(0,url.length()-1);
        Log.i(TAG,url);
        Api.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String datas=response.body().string();
                Weather weather=Api.StringToJson(datas,Weather.class);
                Log.i(TAG,datas);
                Log.i(TAG,weather.toString());
                weatherPresenter.getWeatherData(weather);

            }
        });
    }


}
