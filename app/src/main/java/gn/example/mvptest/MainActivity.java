package gn.example.mvptest;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gn.example.mvptest.adapter.NewsAdapter;
import gn.example.mvptest.bean.Move;
import gn.example.mvptest.presenter.BasePresenter;
import gn.example.mvptest.presenter.NewsPresenter;
import gn.example.mvptest.view.BaseActivity;
import gn.example.mvptest.view.IView;

public class MainActivity extends BaseActivity implements IView {

    private RecyclerView news_show_recycler;

    private List<Move.NewsList> listList;

    BasePresenter presenter=new NewsPresenter(this);
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Map<String,String> map=new HashMap<String,String>();
        map.put("key","7a2730b1413cf41a7cc0c51f434b44ca");
        map.put("num","10");
        presenter.getData(map);
    }



    /**
     * 初始化控件
     */
    private void initView() {

        news_show_recycler=findViewById(R.id.news_show);
        linearLayoutManager=new LinearLayoutManager(this);

    }

    @Override
    public void success(final Object o) {
        if(o instanceof Move){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listList=((Move) o).getNewslist();
                    NewsAdapter newsAdapter=new NewsAdapter(getApplication(),listList);
                    news_show_recycler.setLayoutManager(linearLayoutManager);
                    news_show_recycler.setAdapter(newsAdapter);
                }
            });

        }

    }

    @Override
    public void Failes(Exception e) {

    }
}
