package gn.example.mvptest.adapter;

import android.content.Context;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.Touch;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import gn.example.mvptest.HttpUtils.ActivityCollector;
import gn.example.mvptest.MainActivity;
import gn.example.mvptest.R;
import gn.example.mvptest.bean.Move;
import gn.example.mvptest.view.WebActivity;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHoder> {

    private static final String TAG = "NewsAdapter";
    
    private List<Move.NewsList> listList;

    private Context context;

    public NewsAdapter(Context context,List<Move.NewsList> listList) {
        this.listList = listList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view=View.inflate(viewGroup.getContext(),R.layout.news_item,null);
            ViewHoder viewHoder=new ViewHoder(view);
            return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHoder viewHoder, final int i) {

            Glide.with(context).load(listList.get(i).getPicUrl()).into(viewHoder.news_img);
            viewHoder.news_type.setText(listList.get(i).getDescription());
            viewHoder.news_show.setText(listList.get(i).getTitle());

            viewHoder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //            新闻网页的Web
                    String url=listList.get(i).getUrl();
                    Intent intent=new Intent(context,WebActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("url",url);
                    context.startActivity(intent);
                }
            });


    }



    @Override
    public int getItemCount() {
        return listList.size();
    }

    class ViewHoder extends RecyclerView.ViewHolder{
        private ImageView news_img;
        private TextView news_type;
        private TextView news_show;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            news_img=itemView.findViewById(R.id.news_img);
            news_type=itemView.findViewById(R.id.news_type);
            news_show=itemView.findViewById(R.id.news_title);
        }
    }

}
