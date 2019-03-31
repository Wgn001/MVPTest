package gn.example.mvptest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
    private DrawerLayout mDrawerLayout;
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
     * 加载TooBar菜单栏
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.back:
                Toast.makeText(this,"Back",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"Delet",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this,"Setting",Toast.LENGTH_SHORT).show();
                break;
//               左侧菜单按钮固定id
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    /**
     * 初始化控件
     */
    private void initView() {

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=findViewById(R.id.drawer_layout);

//       添加左侧菜单栏按钮
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }

//       侧拉界面控件
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

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
