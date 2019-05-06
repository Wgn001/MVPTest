package gn.example.mvptest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gn.example.mvptest.R;
import gn.example.mvptest.bean.Weather;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private Context context;
    private List<Weather.WeatherList> weatherList;

    public WeatherAdapter(Context context, List<Weather.WeatherList> weatherList) {
        this.context = context;
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.weather_item, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String temp=weatherList.get(i).getLowest()+"~"+weatherList.get(i).getHighest();
        viewHolder.week.setText(weatherList.get(i).getWeek());
        viewHolder.date.setText(weatherList.get(i).getDate());
        viewHolder.conditions.setText(weatherList.get(i).getWeather());
        viewHolder.temp.setText(temp);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView week,date,conditions,temp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            week=itemView.findViewById(R.id.week);
            date=itemView.findViewById(R.id.date);
            conditions=itemView.findViewById(R.id.weather_conditions);
            temp=itemView.findViewById(R.id.temp);
        }
    }

}
