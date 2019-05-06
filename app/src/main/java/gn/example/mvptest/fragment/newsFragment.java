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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gn.example.mvptest.R;
import gn.example.mvptest.adapter.NewsAdapter;
import gn.example.mvptest.bean.Move;
import gn.example.mvptest.presenter.NewsPresenter;
import gn.example.mvptest.view.IView;

public class newsFragment extends Fragment implements IView{
    private static final String TAG = "NewsFragment";
    private List<Move.NewsList> newsLists;
    private NewsAdapter newsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.news_fragment, container, false);
        recyclerView=view.findViewById(R.id.news_recycler_view);
        linearLayoutManager=new LinearLayoutManager(getContext());
        NewsPresenter newsPresenter=new NewsPresenter(this);
        Map<String,String> map=new HashMap<String,String>();
        map.put("key","7a2730b1413cf41a7cc0c51f434b44ca");
        map.put("num","10");
//      请求数据参数
        newsPresenter.getData(map);
         return view;
    }

    @Override
    public void success(Object o) {
        if(o instanceof Move){
            newsLists=((Move) o).getNewslist();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    newsAdapter=new NewsAdapter(getContext(),newsLists);
                    Log.i(TAG,newsLists.toString());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(newsAdapter);

                }
            });

        }


    }

    @Override
    public void Failes(Exception e) {

    }
    
}
