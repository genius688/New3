package in_out_rcd;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartstore.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class in_out_rcd extends AppCompatActivity {

    public static Map<Boolean, ArrayList<inoutClass>> info = new HashMap<>();
    public List<String> day = new ArrayList<>(7);  //用于记录日历中每一块要显示的“日”
    public List<String> month = new ArrayList<>(7);//用于记录日历中每一块要显示的“月”
    private LinearLayout inoutInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_out_rcd);

        inoutInfo = findViewById(R.id.INOUT_info);

        {
//            Thread t1 = new Thread(this::updateDataTime);
////            Thread t2 = new Thread(this::getInOutInfo);
//
//            t1.start();
//            try {
//                t1.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            t2.start();
//            try {
//                t2.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }

//****创建日历*****
        {
//            //获取过去一周内的出入库记录
//            test_information.add("Click 1");
//            test_information.add("Click 2");
//            test_information.add("Click 3");
//            test_information.add("Click 4");
//            test_information.add("Click 5");
//            test_information.add("Click 6");
//            test_information.add("Click 7");  //用于点击各个 “日” 显示的文本

            get_post_days();  //函数定义在下面，用于将今天以前的七天对应的月和日存入上面的day和month链表中

            //at是一个类，里面有一种函数add_date_item，可以动态地在日历中添加每一天
            //findViewById(R.id.date_time_bar表示要添加入的位置，findViewById(R.id.test）表示点击要显示的位置

            add_time at = new add_time(this, findViewById(R.id.date_time_bar), inoutInfo);
            at.add_date_item(0, day, month);//触发添加函数
        }

//****平滑切换****
        {
            //白到黑
            findViewById(R.id.rcd_change_btn_dark).animate().alpha(0).setDuration(1);
            findViewById(R.id.rcd_change_btn_dark).setEnabled(false);
            findViewById(R.id.rcd_change_btn_light).setOnClickListener(v -> {
                findViewById(R.id.rcd_change_btn_light).setEnabled(false);
                findViewById(R.id.rcd_change_btn_dark).setEnabled(true);
                ValueAnimator colorAnimator = ValueAnimator.ofArgb(Color.parseColor("#EFF9F6"), Color.parseColor("#C7F2E3"));
                colorAnimator.setDuration(1000);
                colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        int currentValue = (int) animator.getAnimatedValue();
                        findViewById(R.id.backcolor).setBackgroundColor(currentValue);
                    }
                });
                colorAnimator.start();

                findViewById(R.id.rcd_light).animate().alpha(0).setDuration(1000);

                ObjectAnimator fadeOutAnimator = ObjectAnimator.ofFloat(findViewById(R.id.rcd_change_btn_light), "alpha", 1f, 0f);
                fadeOutAnimator.setDuration(800); // 设置动画持续时间

                ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(findViewById(R.id.rcd_change_btn_light), "translationX", 0f, 1.5f * findViewById(R.id.rcd_change_btn_light).getWidth());
                translateXAnimator.setDuration(800); // 设置动画持续时间

                ObjectAnimator fadeOutAnimator1 = ObjectAnimator.ofFloat(findViewById(R.id.rcd_change_btn_dark), "alpha", 0f, 1f);
                fadeOutAnimator1.setDuration(1000); // 设置动画持续时间

                ObjectAnimator translateXAnimator1 = ObjectAnimator.ofFloat(findViewById(R.id.rcd_change_btn_dark), "translationX", 0f, -(1.5f * findViewById(R.id.rcd_change_btn_light).getWidth()));
                translateXAnimator1.setDuration(1000); // 设置动画持续时间

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(fadeOutAnimator, translateXAnimator, fadeOutAnimator1, translateXAnimator1); // 同时播放两个动画
                animatorSet.start(); // 开始动画
            });
            //黑到白
            findViewById(R.id.rcd_change_btn_dark).setOnClickListener(v -> {
                findViewById(R.id.rcd_change_btn_light).setEnabled(true);
                findViewById(R.id.rcd_change_btn_dark).setEnabled(false);
                ValueAnimator colorAnimator = ValueAnimator.ofArgb(Color.parseColor("#C7F2E3"), Color.parseColor("#EFF9F6"));
                colorAnimator.setDuration(1000);
                colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        int currentValue = (int) animator.getAnimatedValue();
                        findViewById(R.id.backcolor).setBackgroundColor(currentValue);
                    }
                });
                colorAnimator.start();

                findViewById(R.id.rcd_light).animate().alpha(1).setDuration(1300);

                ObjectAnimator fadeOutAnimator = ObjectAnimator.ofFloat(findViewById(R.id.rcd_change_btn_dark), "alpha", 1f, 0f);
                fadeOutAnimator.setDuration(800); // 设置动画持续时间

                ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(findViewById(R.id.rcd_change_btn_dark), "translationX", -1.5f * findViewById(R.id.rcd_change_btn_light).getWidth(), 0f);
                translateXAnimator.setDuration(800); // 设置动画持续时间

                ObjectAnimator fadeOutAnimator1 = ObjectAnimator.ofFloat(findViewById(R.id.rcd_change_btn_light), "alpha", 0f, 1f);
                fadeOutAnimator1.setDuration(1000); // 设置动画持续时间

                ObjectAnimator translateXAnimator1 = ObjectAnimator.ofFloat(findViewById(R.id.rcd_change_btn_light), "translationX", (1.5f * findViewById(R.id.rcd_change_btn_light).getWidth()), 0f);
                translateXAnimator1.setDuration(1000); // 设置动画持续时间

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(fadeOutAnimator, translateXAnimator, fadeOutAnimator1, translateXAnimator1); // 同时播放两个动画
                animatorSet.start(); // 开始动画
            });
        }


//        {
//            findViewById(R.id.rcd_dark_return_btn).setOnClickListener(v -> {
//                test_information.clear();
//                day.clear();
//                month.clear();
//                add_time.date_index_list.clear();
//                finish();
//            });
//        }
    }


//    public void getInOutInfo(){
//        OkHttpClient client = new OkHttpClient().newBuilder().build();
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//
////        String queryParams = String.valueOf(uid);
//
//        RequestBody body = RequestBody.create(JSON, "");
//        String url = "http://120.26.248.74:8080/getLayoutId?uid=" + queryParams;
//
//        try {
//            Request request = new Request.Builder()
//                    .url(url)
//                    .post(body)
//                    .build();
//
//            Response response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                String js = response.body().string();
//                JSONArray jsonArray = new JSONArray(js);
//                for (int k = 0; k < jsonArray.length(); k++) {
//                    JSONObject jsonObject = jsonArray.getJSONObject(k);
//
//                    String lnm = jsonObject.getString("layout_name");
//                    String lid = jsonObject.getString("layout_id");
//                    user_layout.put(lnm,Integer.parseInt(lid));  //获取所有布局
//
//                }
//            } else {
//                System.out.println("响应码: " + response.code());
//                String responseBody = response.body().string();
//                System.out.println("响应体: " + responseBody);
//            }
//            response.body().close();
//        } catch (IOException | JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void updateDataTime(){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, "");
        String url = "http://120.26.248.74:8080/delete7DAgo?";
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                System.out.println("响应码: " + response.code());
                String responseBody = response.body().string();
                System.out.println("响应体: " + responseBody);
            }
            response.body().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void onBackPressed() {
//        test_information.clear();
        day.clear();
        month.clear();
        add_time.date_index_list.clear();
        finish();
    }

    //****获取今天以前七天的数据***
    public void get_post_days() {
        day.clear();
        month.clear();
        // 创建一个SimpleDateFormat对象用于格式化日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 创建一个Calendar实例，并设置为当前日期和时间
        Calendar calendar = Calendar.getInstance();

        // 设置日历为当前日期的前一周
        calendar.add(Calendar.DATE, -6);

        // 遍历过去一周的每一天
        for (int i = 0; i < 7; i++) {
            Integer formatday = calendar.get(Calendar.DAY_OF_MONTH);
            day.add(formatday.toString());
            // 获取当天的月份（注意：月份是从0开始的）
            int formatmonth = calendar.get(Calendar.MONTH) + 1;
            month.add(getMONTH(formatmonth));
            // 将日历向前推一天，准备下一次循环
            calendar.add(Calendar.DATE, 1);
        }
    }

    //****将月份转化为英文显示***
    public String getMONTH(Integer m){
        switch(m){
            case (1): return "Jan.";
            case (2): return "Feb.";
            case (3): return "Mar.";
            case (4): return "Apr.";
            case (5): return "May.";
            case (6): return "Jun.";
            case (7): return "Jul.";
            case (8): return "Aug.";
            case (9): return "Sept.";
            case (10): return "Oct.";
            case (11): return "Nov.";
            case (12): return "Dec.";
        }
        return "";
    }

}

