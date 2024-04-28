package chattingCircle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.smartstore.MainActivity;
import com.example.smartstore.R;
import com.example.smartstore.mine_page;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Chattingcircle_recommend extends AppCompatActivity implements ModuleAdapter.ItemClickListener{

    private ArrayList<String> post_name_ = new ArrayList<>();
    private ArrayList<String> bp_ = new ArrayList<>();
    private ArrayList<String>  post_detail_ = new ArrayList<>();
    private ArrayList<String> post_rls_time_ = new ArrayList<>();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chattingcircle_recommend);
        context = this;
        Button commend = findViewById(R.id.commend);
        Button mine = findViewById(R.id.my_chatting_circle);
        ImageView commend_icon=findViewById(R.id.commend_icon);
        ImageView mine_icon=findViewById(R.id.my_circle_icon);
        TextView goto1 = findViewById(R.id.twoTOone);
        TextView goto3 = findViewById(R.id.twoTOthree);

        goto1.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        });

        goto3.setOnClickListener(v -> {
            Intent intent = new Intent(context, mine_page.class);
            startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        });

        commend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commend.setTextColor(Color.parseColor("#204D36"));
                mine.setTextColor(Color.parseColor("#53856C"));
                commend_icon.setVisibility(View.VISIBLE);
                mine_icon.setVisibility(View.INVISIBLE);
            }
        });

        mine.setOnClickListener(v -> {
            commend.setTextColor(Color.parseColor("#53856C"));
            mine.setTextColor(Color.parseColor("#204D36"));
            commend_icon.setVisibility(View.INVISIBLE);
            mine_icon.setVisibility(View.VISIBLE);

            Intent intent = new Intent(Chattingcircle_recommend.this, ChattingCircle.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        Thread t1 = new Thread(this::getPost);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        RecyclerView recyclerView = findViewById(R.id.list1);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)); // 根据需求调整列数
        List<ModuleItem> moduleItems = getModuleItemsData();
        ModuleAdapter adapter = new ModuleAdapter(moduleItems,this);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(this);
    }

    public void onFixedImageButtonClick(int position) {
        Intent intent = new Intent(this, Post.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private List<ModuleItem> getModuleItemsData() {
        List<ModuleItem> moduleItems = new ArrayList<>();

        ModuleItem fixedModule = new ModuleItem(ModuleItem.Type.FIXED, null, "","","");
        moduleItems.add(fixedModule);

        for (int i = 0; i < bp_.size(); i++) {
            ModuleItem customModule = new ModuleItem(
                    ModuleItem.Type.CUSTOM,
                    bp_.get(i),
                    post_detail_.get(i),
                    post_name_.get(i),
                    post_rls_time_.get(i)
            );

            moduleItems.add(customModule);
        }
        return moduleItems;
    }
    public void getPost() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String url = "http://120.26.248.74:8080/scrollPost";

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String js = response.body().string();
                JSONArray jsonArray = new JSONArray(js);
                for (int k = 0; k < jsonArray.length(); k++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(k);

                    String post_name = jsonObject.getString("post_name");
                    String bp = jsonObject.getString("post_media");
                    String post_detail = jsonObject.getString("post_detail");
                    String post_rls_time = jsonObject.getString("post_rls_time");

                    post_name_.add(post_name);
                    bp_.add(bp);
                    post_detail_.add(post_detail);
                    post_rls_time_.add(post_rls_time);
                }
            } else {
                System.out.println("响应码: " + response.code());
                assert response.body() != null;
                String responseBody = response.body().string();
                System.out.println("响应体: " + responseBody);
            }
            response.body().close();
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


}

