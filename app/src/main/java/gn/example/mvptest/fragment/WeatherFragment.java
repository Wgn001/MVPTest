package gn.example.mvptest.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedHashMap;
import gn.example.mvptest.R;
import gn.example.mvptest.adapter.WeatherAdapter;
import gn.example.mvptest.bean.Weather;
import gn.example.mvptest.presenter.NewsPresenter;
import gn.example.mvptest.view.IView;

public class WeatherFragment extends Fragment implements IView {

    private RecyclerView weather_recycler;
    private WeatherAdapter weatherAdapter;
    private NewsPresenter weatherPresenter;

    private static final String TAG = "WeatherFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.weather_fragment,container,false);
        weather_recycler=view.findViewById(R.id.weather_recycler_view);
        weatherPresenter=new NewsPresenter(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        weather_recycler.setLayoutManager(linearLayoutManager);
        LinkedHashMap<String ,String> map=new LinkedHashMap<>();
        map.put("key","7a2730b1413cf41a7cc0c51f434b44ca");
        map.put("ip","116.234.222.36");
        map.put("city","广州");
        weatherPresenter.getWeatherData(map);
        return view;
    }

    @Override
    public void success(final Object o) {
        if(o instanceof Weather){
            weatherAdapter=new WeatherAdapter(getContext(),((Weather) o).getNewsList());
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG,((Weather) o).getNewsList().toString());
                    weather_recycler.setAdapter(weatherAdapter);
                }
            });
        }

    }

    @Override
    public void Failes(Exception e) {

    }
}
