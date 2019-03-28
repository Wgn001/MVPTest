package gn.example.mvptest.presenter;

import android.app.Activity;

import java.util.Map;

import gn.example.mvptest.model.IMode;
import gn.example.mvptest.model.NewsModel;
import gn.example.mvptest.view.IView;

public class NewsPresenter implements BasePresenter {


    private IView iv;

    private IMode iMode=new NewsModel(this);

    public NewsPresenter(IView iv) {
        this.iv=iv;
    }

    /**
     * 根据参数获取网络数据
     * @param map
     */
    @Override
    public void getData(Map<String, String> map) {
        iMode.getData(map);
    }

    /**
     * 将接收的数据传递给Activity显示
     * @param data
     */
    public void getDatas(String data){

        iv.success(data);
    }

}
