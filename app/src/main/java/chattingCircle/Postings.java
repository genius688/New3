package chattingCircle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartstore.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Postings extends AppCompatActivity {

    ImageView postingimg;
    TextView postingname;
    TextView postingdetail;

    TextView postingtime;
    TextView postinglikes;
    Activity activity;

    ImageView Likes;

    int id;
    String post_title;
    String post_content;
    String post_img;
    String post_time;
    String likes;
    int[]isLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        postingimg=findViewById(R.id.posting_img);
        postingname=findViewById(R.id.posting_name);
        postingdetail=findViewById(R.id.posting_detail);
        postinglikes=findViewById(R.id.postinglikes);
        Likes=findViewById(R.id.posting_likes);
        postingtime=findViewById(R.id.postingtime);
        activity = (Activity)this;

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("post_Id", 0);
            post_img = intent.getStringExtra("post_img");
            post_content = intent.getStringExtra("post_content");
            post_title = intent.getStringExtra("post_name");
            post_time = intent.getStringExtra("post_time");
        }

        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        int serverId = preferences.getInt("uer_id", -1);

        scrollPost(id);

        boolean shouldTriggerSingleitemsClick = getIntent().getBooleanExtra("trigger_single_items_click", false);
        if (!shouldTriggerSingleitemsClick) {
            Likes.setImageResource(R.drawable.star_gray);
            postinglikes.setText(likes);

            postingname.setText(post_title);
            postingdetail.setText(post_content);
            postingimg.setImageBitmap(BitmapFactory.decodeFile(post_img));
            postingtime.setText(post_time);

            postinglikes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        String likes=postinglikes.getText().toString();
                        int like = 1;
                        if(likes != null && !likes.trim().isEmpty()){
                            like = Integer.parseInt(likes);
                        }
                        String after_like=String.valueOf(like)+"";
                        postinglikes.setText(after_like);
                        Likes.setImageResource(R.drawable.star_green);
                        updateLike(id,serverId);
                }
            });
        }
    }

    public void scrollPost(int id){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        StringBuilder queryParams = new StringBuilder();
        queryParams.append("post_id=").append(id);

        RequestBody body = RequestBody.create(JSON, "");
        String url = "http://120.26.248.74:8080/getPostDetail?"+queryParams ;
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Request request = new Request.Builder()
                                .url(url)
                                .post(body)
                                .build();

                        Response response = client.newCall(request).execute();

                        if (response.isSuccessful()) {
                            String js = response.body().string();
                            JSONArray jsonArray = new JSONArray(js);
                            if (jsonArray.length() > 0) {
                                JSONObject jsonObject = jsonArray.getJSONObject(0);

                                String name=jsonObject.getString("post_name");
                                Bitmap media= BitmapFactory.decodeFile(jsonObject.getString("post_media"));
                                String detail=jsonObject.getString("post_detail");
                                String time=jsonObject.getString("post_rls_time");
                                likes=jsonObject.getString("post_likes");

                                activity.runOnUiThread(() -> {
                                    postingname.setText(name);
                                    postingdetail.setText(detail);
                                    postingimg.setImageBitmap(media);
                                    int commaIndex = time.indexOf("T");
                                    String partOfPtime = time.substring(0, commaIndex);
                                    postingtime.setText(partOfPtime);
                                });
                            }
                            else {
                            }
                        } else {
                            System.out.println("响应码: " + response.code());
                            String responseBody = response.body().string();
                            System.out.println("响应体: " + responseBody);
                        }
                        response.body().close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void updateLike(Integer id, Integer uid) {
        OkHttpClient client1 = new OkHttpClient().newBuilder().build();
        MediaType JSON1 = MediaType.parse("application/json; charset=utf-8");

        StringBuilder queryParams1 = new StringBuilder();

        queryParams1.append("post_id=").append(id).append("&");
        queryParams1.append("uid=").append(uid);

        System.out.println("yoyoy" + queryParams1);
        RequestBody body1 = RequestBody.create(JSON1, "");
        String url1 = "http://120.26.248.74:8080/insertLikePost?" + queryParams1;
        try {
            new Thread(() -> {
                try {
                    Request request = new Request.Builder()
                            .url(url1)
                            .post(body1)
                            .build();

                    Response response = client1.newCall(request).execute();
                    if (response.isSuccessful()) {
                        System.out.println("yoyoyoyoyyoyoy");
                    } else {
                        System.out.println("响应码: " + response.code());
                        String responseBody = response.body().string();
                        System.out.println("响应体: " + responseBody);
                    }
                    response.body().close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}