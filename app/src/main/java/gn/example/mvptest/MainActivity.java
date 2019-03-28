package gn.example.mvptest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import gn.example.mvptest.presenter.BasePresenter;
import gn.example.mvptest.presenter.NewsPresenter;
import gn.example.mvptest.view.BaseActivity;
import gn.example.mvptest.view.IView;

public class MainActivity extends BaseActivity implements IView {

    private TextView news_show;
    private Button news_get;

    BasePresenter presenter=new NewsPresenter(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Map<String,String> map=new HashMap<String,String>();
        map.put("key","7a2730b1413cf41a7cc0c51f434b44ca");
        map.put("num","10");
        presenter.getData(map);
        news_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }



    /**
     * 初始化控件
     */
    private void initView() {

        news_show=findViewById(R.id.news_show);
        news_get=findViewById(R.id.news_get);

    }

    @Override
    public void success(final Object o) {
        if(o instanceof String){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    news_show.setText(o.toString());
                }
            });

        }

    }

    @Override
    public void Failes(Exception e) {

    }
}
