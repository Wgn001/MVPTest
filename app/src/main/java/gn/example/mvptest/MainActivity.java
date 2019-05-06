package gn.example.mvptest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;

import gn.example.mvptest.fragment.MyFragment;
import gn.example.mvptest.fragment.WeatherFragment;
import gn.example.mvptest.fragment.newsFragment;
import gn.example.mvptest.view.BaseActivity;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    public DrawerLayout mDrawerLayout;
    public BottomTabBar bottomTabBar ;


    private float DownX;
    private float DownY;
    private float MoveX;
    private float MoveY;

//    当前页面位置
    public  int overPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
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
        return false;
    }

    /**
     * 初始化控件
     */
    private void initView() {

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=findViewById(R.id.drawer_layout);

//      底部条目
        bottomTabBar=findViewById(R.id.bottombar);

        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(50,50)
                .setFontSize(20)
                .setTabPadding(4,6,10)
//                .setChangeColor(Color.RED,Color.BLUE)
                .addTabItem("首页",R.drawable.fennec,newsFragment.class)
                .addTabItem("天气",R.drawable.fennec,WeatherFragment.class)
                .addTabItem("娱乐",R.drawable.fennec,MyFragment.class)
                .isShowDivider(true)
                .setCurrentTab(0);
                bottomTabBar.setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {
                        overPosition=position;
                        Log.i(TAG,">---"+overPosition);
                    }
                });

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
                switch (menuItem.getItemId()){
                    case R.id.nav_call:
                        Toast.makeText(getApplicationContext(),"Call",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_friends:
                        Toast.makeText(getApplicationContext(),"Friends",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_location:
                        Toast.makeText(getApplicationContext(),"Location",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_mail:
                        Toast.makeText(getApplicationContext(),"Main",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_task:
                        Toast.makeText(getApplicationContext(),"Task",Toast.LENGTH_SHORT).show();
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                DownX=ev.getX();
                DownY=ev.getY();
                Log.i(TAG,String.valueOf(DownX));
                break;
            case MotionEvent.ACTION_MOVE:
                MoveX=ev.getX();
                MoveY=ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                if((DownX-MoveX)>0 && (Math.abs(DownX-MoveX)>250)){
                    Log.i(TAG,"左滑");
                    bottomTabBar.setCurrentTab(overPosition+1);
                    return true;
                }else if((DownX-MoveX)<0 && (Math.abs(DownX-MoveX)>250)){
                    bottomTabBar.setCurrentTab(overPosition-1);
                    Log.i(TAG,"右滑");
                    return true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
