package gn.example.mvptest.presenter;

import java.util.Map;

import gn.example.mvptest.bean.Move;
import gn.example.mvptest.bean.Weather;
import gn.example.mvptest.model.IModel;
import gn.example.mvptest.model.NewsModel;
import gn.example.mvptest.model.WeatherModel;
import gn.example.mvptest.view.IView;

public class NewsPresenter implements BasePresenter {


    private IView iv;

    private IModel iModel=new NewsModel(this);
    private IModel weatherMoedl=new WeatherModel(this);
    public NewsPresenter(IView iv) {
        this.iv=iv;
    }

    /**
     * 根据参数获取网络数据
     * @param map
     */
    @Override
    public void getData(Map<String, String> map) {

        iModel.getData(map);
    }

    public void getWeatherData(Map<String,String> map){
        weatherMoedl.getData(map);
    }

    /**
     * 将接收的数据传递给Activity显示
     * @param data
     */
    public void getDatas(Move data){

        iv.success(data);
    }

    /**
     *将接收到的天气数据传递给RecyclerView显示
     * @param weather
     */
    public void getWeatherData(Weather weather){

        iv.success(weather);
    }


}
