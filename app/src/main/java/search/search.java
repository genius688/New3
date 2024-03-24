package search;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartstore.R;

import java.util.ArrayList;
import java.util.List;
//import cn.student0.manager.RepeatLayoutManager;


public class search extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InfiniteScrollAdapter adapter;
    private List<info> dataList;
    private Button search_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        recyclerView = findViewById(R.id.recyclerView);
        search_btn = findViewById(R.id.search_return_btn);

        recyclerView.invalidate(); // 标记View为需要重新绘制
        recyclerView.requestLayout(); // 请求重新测量和布局

//动态添加收纳点 ***************
        InfiniteScrollLayoutManager layoutManager = new InfiniteScrollLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //存储某一场景下，所拥有的空间数目
        dataList = new ArrayList<>();
        dataList.add(new info("小女儿的房间","可可爱爱的小女儿"));
        dataList.add(new info("阳台","适合休息，有花有草有小鸟"));
        dataList.add(new info("客厅","重要的门面，一定要保持干净"));
        dataList.add(new info("厕所","容易滋生细菌，要定期打扫"));

        // 设置无限循环的项数
        int infiniteItemCount = 0;
        adapter = new InfiniteScrollAdapter(dataList, infiniteItemCount);

        // 设置适配器
        recyclerView.setAdapter(adapter);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        //更新界面
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    recyclerView.invalidate(); // 标记View为需要重新绘制
                    recyclerView.requestLayout(); // 请求重新测量和布局
                }

                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    recyclerView.invalidate(); // 标记View为需要重新绘制
                    recyclerView.requestLayout(); // 请求重新测量和布局
            }
            });


//动态添加收纳点 ***************

//返回主界面 ***************
        search_btn.setOnClickListener(v -> {
            InfiniteScrollAdapter.tmp.clear();
            finish();
        });
    }
    public void onBackPressed(){
        InfiniteScrollAdapter.tmp.clear();
        finish();
    }
//返回主界面 ***************

}