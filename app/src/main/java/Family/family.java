package Family;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartstore.MainActivity;
import com.example.smartstore.R;
import com.example.smartstore.mine_page;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import chattingCircle.Chattingcircle_recommend;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class family extends AppCompatActivity {

    public List<String> day3 = new ArrayList<>(16);  //用于记录日历中每一块要显示的“日”
    public List<String> week3 = new ArrayList<>(16);//用于记录日历中每一块要显示的“星期”
    public List<String> month3 = new ArrayList<>(16);//用于记录日历中每一块要显示的“月份”
    private LinearLayout containerLayout;
    public static List<Task> taskList = new ArrayList<>();//任务列表
    private int select_year;//用户选择的日期
    private int select_month;
    private int select_day;
    private int msn_id;//任务id
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.family);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        //*****任务清单的线性布局******
        containerLayout = findViewById(R.id.containerLayout);
        //*****任务清单的线性布局******
        Thread t2= new Thread(() -> getinfo(0));
        t2.start();
        try {
            t2.join();
        }catch (InterruptedException e){
            throw  new RuntimeException(e);
        }
        System.out.println("任务列表的大小 "+taskList.size());
        for (Task task:taskList){
            View customView = getLayoutInflater().inflate(R.layout.task, null);
            TextView textView1 = customView.findViewById(R.id.textView1);
            TextView textView2 = customView.findViewById(R.id.textView2);
            TextView textView3 = customView.findViewById(R.id.textView3);
            TextView textViewDate = customView.findViewById(R.id.task_date);
            Button actionbutton=customView.findViewById(R.id.task_op);
            // 设置TextView的文本
            textView1.setText(task.getTitle());
            textView2.setText(task.getContent());
            textView3.setText(task.getPerson());
            textViewDate.setText(task.getDate()); // 设置日期TextView的文本
            // 将Task对象作为标签附加到视图上
            customView.setTag(task); // newTask是刚刚创建的Task对象
            // 将自定义布局的实例添加到containerLayout中
            containerLayout.addView(customView);
            printTaskList();
            actionbutton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTaskOptionsDialog(customView); // 显示任务选项弹窗
                }
            });
        }
        Button urgentButton =  findViewById(R.id.urgent_task);
        // 判断今天是否有未完成的任务且时间不足五小时
        if (unfinished_task(taskList)) {
            ImageView warn=findViewById(R.id.warning);
            warn.setVisibility(View.VISIBLE);
            urgentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //****提醒弹窗
                    AlertDialog.Builder builder = new AlertDialog.Builder(family.this);
                    builder.setMessage("      距离今天结束不足五小时，还有待完成的任务，请尽快完成");
                    builder.setPositiveButton("确定", null); // 设置确定按钮，点击后对话框消失
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
        //*****设置大月份字体
        //设置月份
        TextView monthTextView = findViewById(R.id.month);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "演示镇魂行楷.ttf");
        monthTextView.setTypeface(typeface);
        Date currentDate = new Date();// 获取当前日期
        Calendar calendar = Calendar.getInstance(Locale.CHINA);// 使用Calendar获取月份
        calendar.setTime(currentDate);
        int month = calendar.get(Calendar.MONTH) + 1; // Calendar的月份是从0开始的，所以需要+1
        int day =calendar.get(Calendar.DAY_OF_WEEK);
        // 汉字月份数组
        String[] chineseMonths = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
        String[] chineseDays ={"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
        // 根据月份获取汉字月份
        if (month <= 12) {
            String chineseMonth = chineseMonths[month - 1]; // 数组索引从0开始，所以需要-1
            monthTextView.setText(chineseMonth);
        } else {
            monthTextView.setText("月份错误");
        }
        monthTextView.setTextSize(40);
        String days =chineseDays[day-2];
        //设置日期
        TextView date=findViewById(R.id.date);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy年MM月dd日");
        Date currentDate2=new Date();
        String formattedDate=dateFormat.format(currentDate);
        date.setText(formattedDate);
        date.setTextColor(0xff2F979C);
        date.setTextSize(18);
        //设置星期几
        TextView day2=findViewById(R.id.day);
        day2.setText(days);
        day2.setTextColor(0xff2F979C);
        day2.setTextSize(15);
        //****创建日历*****
//        test_information.add("Click 1");//用于点击各个 “日” 显示的文本
        get_days();  //函数定义在下面，用于将今天以后的十五天对应的星期和日存入上面的day和month链表中
        //at是一个类，里面有一种函数add_date_item，可以动态地在日历中添加每一天
        //findViewById(R.id.date_time_bar表示要添加入的位置，findViewById(R.id.test）表示点击要显示的位置
        add_time1 at = new add_time1(this,findViewById(R.id.date_time_bar),findViewById(R.id.test));
        at.add_date_item(0, day3,week3);//触发添加函数
//****创建日历*****

        //*****动态添加任务*****
        TextView inputTextView = findViewById(R.id.add_task);
        inputTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        //*****动态添加任务*****

        //*****清空任务*****
        Button clearButton = (Button) findViewById(R.id.clear_task);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(family.this);
                builder.setTitle("清空任务");
                builder.setMessage("是否要清空当天所有任务？");

                // 设置确定按钮的点击事件
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确定后，清空所有任务
                        Date currentDate = new Date();
                        // 设置日期格式
                        SimpleDateFormat sdf = new SimpleDateFormat("M月dd日", Locale.CHINA);
                        // 将当前日期转换为指定格式的字符串
                        String formattedDate = sdf.format(currentDate);
                        Iterator<Task> iterator = taskList.iterator();
                        while (iterator.hasNext()){
                            Task task=iterator.next();
                            //****遍历taskList找到今天的任务
                            if (task.getDate().equals(formattedDate)){
                                System.out.println("任务已找到当天任务");
                                View task_view = findViewByTask(containerLayout,task);//遍历任务视图，找到对应的任务视图
                                containerLayout.removeView(task_view);//删除任务视图
                                deleteTask(task.getId());//从数据库中删除
                                iterator.remove();//从任务链表中删除
                            }else {System.out.println("任务不是当天的");}
                        }
                    }
                });
                // 设置取消按钮的点击事件
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击取消后，不执行任何操作，对话框自动关闭
                    }
                });

                // 显示对话框
                builder.show();
            }
        });
        //*****清空任务*****
        //*****帮助界面的跳转
        Button btnGoToSecondActivity = findViewById(R.id.help);
        btnGoToSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(family.this, help.class);
                startActivity(intent);
            }
        });

        TextView goto1 = findViewById(R.id.twoTOone);
        TextView goto3 = findViewById(R.id.twoTOthree);
        TextView goto4 = findViewById(R.id.twoTOfour);

        goto1.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        });

        goto3.setOnClickListener(v -> {
            Intent intent = new Intent(this, Chattingcircle_recommend.class);
            startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        });

        goto4.setOnClickListener(v -> {
            Intent intent = new Intent(this, mine_page.class);
            startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        });
    }




    //****回调接口
    public interface TaskListCallback {
        void onTaskListLoaded(List<Task> tasks);
    }
    //****获取十六天的数据***
    public void get_days() {
        day3.clear();
        week3.clear();
        month3.clear();
        // 创建一个SimpleDateFormat对象用于格式化日期
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 创建一个Calendar实例，并设置为当前日期和时间
        Calendar calendar = Calendar.getInstance();

        // 设置日历为今天
        calendar.add(Calendar.DATE, 0);

        // 遍历未来十六天的每一天
        for (int i = 0; i < 16; i++) {
            int formatday = calendar.get(Calendar.DAY_OF_MONTH);
            day3.add(Integer.toString(formatday));
            // 获取当天的月份（注意：月份是从0开始的）
            int formatweek = calendar.get(Calendar.DAY_OF_WEEK);
            week3.add(getWEEK(formatweek));
            // 将日历向前推一天，准备下一次循环
            calendar.add(Calendar.DATE, 1);
        }
    }
//****获取十六天的数据***

    //****将星期几转化为“周几”***
    public String getWEEK(Integer m){
        switch(m){
            case (1): return "周日";
            case (2): return "周一";
            case (3): return "周二";
            case (4): return "周三";
            case (5): return "周四";
            case (6): return "周五";
            case (7): return "周六";
        }
        return "";
    }
    //****将星期几转化为“周几”***
    //*****动态添加任务*****
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog, null);
        final EditText editText1 = dialogView.findViewById(R.id.editText1);
        final EditText editText2 = dialogView.findViewById(R.id.editText2);
        final EditText editText3 = dialogView.findViewById(R.id.editText3);
        final TextView taskDateTextView = dialogView.findViewById(R.id.select_date);
        // 设置日期选择监听器
        taskDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        family.this, // 将此处的ShowDialogActivity替换为你的Activity名
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // 设置日期，注意月份是从0开始的，所以+1
                                String date = (monthOfYear + 1) + "月" + dayOfMonth + "日";
                                taskDateTextView.setText(date);
                                // 如果需要，可以将选择的日期保存在某个变量中，供后续使用
                                select_year=year;
                                select_month=monthOfYear;
                                select_day=dayOfMonth;
                            }
                        }, year, month, day
                );
                // 设置DatePickerDialog的最小日期为今天
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                // 设置最大可选日期为今天起15天后
                calendar.add(Calendar.DAY_OF_MONTH, 15);
                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

                datePickerDialog.show();
            }
        });

        builder.setView(dialogView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String input1 = editText1.getText().toString();
                        String input2 = editText2.getText().toString();
                        String input3 = editText3.getText().toString();
                        String date = taskDateTextView.getText().toString(); // 获取用户选择的日期

                        String month_ = String.format("%02d", select_month);
                        String day_ = String.format("%02d", select_day);
                        String format_date = select_year + "-" + month_ + "-" + day_;
                        //*****通过api上传数据
                        //********记得改回来
                        // 通过继承Thread类并重写run方法
                        System.out.println("日期"+format_date);

                        Thread t1= new Thread(() -> updateInfo(input1,input2,input3,format_date,1));
                        t1.start();
                        try {
                            t1.join();
                        }catch (InterruptedException e){
                            throw  new RuntimeException(e);
                        }
                        System.out.println("你好世界 已返回任务id"+" "+msn_id);
                        // 创建自定义布局的实例
                        View customView = getLayoutInflater().inflate(R.layout.task, null);
                        TextView textView1 = customView.findViewById(R.id.textView1);
                        TextView textView2 = customView.findViewById(R.id.textView2);
                        TextView textView3 = customView.findViewById(R.id.textView3);
                        TextView textViewDate = customView.findViewById(R.id.task_date);
                        Button actionbutton=customView.findViewById(R.id.task_op);
                        // 设置TextView的文本
                        textView1.setText(input1);
                        textView2.setText(input2);
                        textView3.setText(input3);
                        textViewDate.setText(date); // 设置日期TextView的文本
                        //将这个任务添加到任务类储存
                        //*****默认任务启动
                        Task newTask=new Task(msn_id,date,input1,input2,input3,1);
                        addNewTask(msn_id,date,input1,input2,input3,1);
                        // 将Task对象作为标签附加到视图上
                        customView.setTag(newTask); // newTask是刚刚创建的Task对象
                        // 将自定义布局的实例添加到containerLayout中
                        containerLayout.addView(customView);
                        printTaskList();
                        // 设置按钮的点击事件监听器
                        actionbutton.setOnClickListener(new Button.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showTaskOptionsDialog(customView); // 显示任务选项弹窗
                            }
                        });
                    }
                })
                .setNegativeButton("取消", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //*****动态添加任务*****
    //******添加到任务类
    public void addNewTask(int id,String date, String title,String content,String person,int state) {
        Task newTask = new Task(id,date, title,content,person,state);
        taskList.add(newTask);
    }
    //******添加到任务类

    // ******显示任务选项弹窗
    private void showTaskOptionsDialog(View customView) {
        //*****利用标签获取当前视图对应的任务
        Task currenttask1 = (Task) customView.getTag();
        Task currenttask=findTaskById(currenttask1.getId());
        Log.d("任务当前", currenttask.getId()+currenttask.getTitle() + currenttask.getContent() + currenttask.getPerson() + currenttask.getDate());
        //*****编辑任务弹窗
        int currentTask_ID=currenttask.getId();
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("编辑任务");
        //*****任务功能弹窗
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("任务操作");
        builder.setItems(new String[]{"启动任务", "暂缓任务", "删除任务"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ImageView startTaskButton = customView.findViewById(R.id.start_task);
                ImageView stopTaskButton = customView.findViewById(R.id.stop_task);
                switch (which) {
                    case 0://启动任务逻辑
                        startTaskButton.setVisibility(View.VISIBLE);
                        stopTaskButton.setVisibility(View.INVISIBLE);
                        //******api更新任务状态
                        updateTask_state(currentTask_ID,1);
                        //*****更新taskList的中任务的状态
                        currenttask.setState(1);
                        printTaskList();
                        break;
                    case 1:// 暂缓任务逻辑
                        startTaskButton.setVisibility(View.INVISIBLE);
                        stopTaskButton.setVisibility(View.VISIBLE);
                        //******api更新任务状态
                        updateTask_state(currentTask_ID,0);
                        currenttask.setState(0);
                        printTaskList();
                        break;
                    case 2:// 删除任务逻辑，从LinearLayout中移除视图等
                        Log.d("任务删除前", currenttask.getId()+currenttask.getTitle() + currenttask.getContent() + currenttask.getPerson() + currenttask.getDate());
                        taskList.remove(currenttask);
                        containerLayout.removeView(customView);
                        //****在任务列表中删除该任务
                        printTaskList();
                        //从数据库删除任务
                        deleteTask(currentTask_ID);
                        break;
                }
            }
        });
        builder.show();
    }
    boolean unfinished_task(List<Task> tasks){
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时间 "+now);
        // 设置夜晚12点的时间
        LocalDateTime midnight = LocalDateTime.of(now.toLocalDate(), LocalTime.MIDNIGHT);
        // 计算当前时间到夜晚12点的时间差
        long hoursUntilMidnight = (24+ChronoUnit.HOURS.between(now, midnight));
        System.out.println("当前距离夜晚12点 "+hoursUntilMidnight);
        // 判断是否不足5个小时
        if (hoursUntilMidnight <=5) {
            System.out.println("当前距离夜晚12点不足5个小时");
        } else {
            System.out.println("当前距离夜晚12点还有 " + hoursUntilMidnight + " 个小时");
            return false;
        }
        Date currentDate = new Date();
        // 设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("M月dd日", Locale.CHINA);
        // 将当前日期转换为指定格式的字符串
        String formattedDate = sdf.format(currentDate);
        System.out.println("当前日期 "+formattedDate);
        System.out.println("当前任务列表大小"+tasks.size());
        for (Task task : tasks) {
            System.out.println("当前开始查找任务");
            //****遍历taskList找到今天的任务
            System.out.println("当前任务日期 " + task.getDate());
            if (task.getDate().equals(formattedDate)) {
                System.out.println("当前还有未完成的任务");
                return true;
            }
        }System.out.println("当前没有未完成的任务");
        return false;
    }
    //****打印任务链表
    private void printTaskList() {
        for (Task task : taskList) {
            Log.d("任务"+"TaskList", "Task ID: " + task.getId() + ", Title: " + task.getTitle() + ", Content: " + task.getContent() + ", Person: " + task.getPerson() + ", Date: " + task.getDate()+", State: "+task.getState());
        }
    }
    //*****通过任务id找到该任务
    public Task findTaskById(int ID) {
        for (Task task : taskList) {
            if (task.getId() == ID) {
                Log.d("任务当前","在" );
                return task;
            }
        }
        Log.d("任务当前","不在" );
        return null; // 如果没有找到任务，返回null
    }
    // ******显示任务选项弹窗
    // ******通过task标签找到对应的任务视图
    public View findViewByTask(ViewGroup root, Task task) {
        for (int i = 0; i < root.getChildCount(); i++) {
            View child = root.getChildAt(i);
            if (child.getTag().equals(task)) {
                return child;
            }
        }
        return null;
    }
    //******通过task标签找到对应的任务视图
    //*****上传任务api
    public void updateInfo(String task_name,String task_content,String person,String date,int layout_id) {
        OkHttpClient client1 = new OkHttpClient().newBuilder().build();
        MediaType JSON1 = MediaType.parse("application/json; charset=utf-8");
        StringBuilder queryParams1 = new StringBuilder();
        queryParams1.append("uid_assigned=").append(person).append("&");
        queryParams1.append("msn_name=").append(task_name).append("&");
        queryParams1.append("msn_desc=").append(task_content).append("&");
        queryParams1.append("layout_id=").append(layout_id).append("&");
        //uid记得改回来
        queryParams1.append("uid_msn_starter=").append(1).append("&");
        queryParams1.append("dispatch_time=").append(date);
        System.out.println("你好世界 上传任务参数" + queryParams1);
        RequestBody body1 = RequestBody.create(JSON1, "");
        String url1 = "http://120.26.248.74:8080/insetMsn?" + queryParams1;
        try {
            Request request = new Request.Builder()
                    .url(url1)
                    .post(body1)
                    .build();
            Response response = client1.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("你好世界 已上传任务");
                String responseBody = response.body().string();
                msn_id=Integer.parseInt(responseBody);
            } else {
                System.out.println("响应码: " + response.code());
                String responseBody = response.body().string();
                System.out.println("响应体: " + responseBody);
            }
            response.body().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //*****上传任务api
    //*****删除任务api
    public void deleteTask(int task_id) {
        OkHttpClient client1 = new OkHttpClient().newBuilder().build();
        MediaType JSON1 = MediaType.parse("application/json; charset=utf-8");

        StringBuilder queryParams1 = new StringBuilder();

        queryParams1.append("msn_id=").append(task_id);
        System.out.println("你好世界 删除任务参数" + queryParams1);
        RequestBody body1 = RequestBody.create(JSON1, "");
        String url1 = "http://120.26.248.74:8080/deleteOneMsn?" + queryParams1;
        try {
            new Thread(() -> {
                try {
                    Request request = new Request.Builder()
                            .url(url1)
                            .post(body1)
                            .build();

                    Response response = client1.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        System.out.println("你好世界 "+responseBody);
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
    //*****删除任务api

    //*****更新任务状态
    public void updateTask_state(int task_id,int state){
        OkHttpClient client1 = new OkHttpClient().newBuilder().build();
        MediaType JSON1 = MediaType.parse("application/json; charset=utf-8");

        StringBuilder queryParams1 = new StringBuilder();

        queryParams1.append("msn_id=").append(task_id).append("&");
        queryParams1.append("msn_name=").append("").append("&");
        queryParams1.append("msn_desc=").append("").append("&");
        queryParams1.append("msn_flag=").append(state);

        System.out.println("你好世界 编辑任务状态参数" + queryParams1);
        RequestBody body1 = RequestBody.create(JSON1, "");
        String url1 = "http://120.26.248.74:8080/editMsn?" + queryParams1;
        try {
            new Thread(() -> {
                try {
                    Request request = new Request.Builder()
                            .url(url1)
                            .post(body1)
                            .build();

                    Response response = client1.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        System.out.println("你好世界"+responseBody);
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
    //*****更新任务状态
    //******拉取任务
    public void getinfo(int layout_id){
        OkHttpClient client1 = new OkHttpClient().newBuilder().build();
        MediaType JSON1 = MediaType.parse("application/json; charset=utf-8");

        StringBuilder queryParams1 = new StringBuilder();

        queryParams1.append("layout_id=").append(layout_id);

        System.out.println("你好世界 拉取任务参数 " + queryParams1);
        RequestBody body1 = RequestBody.create(JSON1, "");
        String url1 = "http://120.26.248.74:8080/selectMsn?" + queryParams1;
        try {
            Request request = new Request.Builder()
                    .url(url1)
                    .post(body1)
                    .build();

            Response response = client1.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("你好世界 成功拉取任务");
                String js = response.body().string();
                JSONArray jsonArray = new JSONArray(js);
                for (int k = 0; k < jsonArray.length(); k++){
                    JSONObject jsonObject = jsonArray.getJSONObject(k);

                    String task_name=jsonObject.getString("msn_name");
                    String task_content = jsonObject.getString("msn_desc");
                    String date = jsonObject.getString("dispatch_time");
                    int id = Integer.parseInt(jsonObject.getString("msn_id"));
                    int state = Integer.parseInt(jsonObject.getString("msn_flag"));
                    String person=jsonObject.getString("uid_assigned");
                    //****将获取的日期转化为 M月dd日
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
                    SimpleDateFormat outputFormat = new SimpleDateFormat("M月dd日", Locale.CHINA);
                    Date date2 = null;
                    try {
                        date2 = inputFormat.parse(date);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    String formattedDate = outputFormat.format(date2);
                    System.out.println("你好世界 格式化日期 " +formattedDate) ;
                    addNewTask(id,formattedDate,task_name,task_content,person,state);
                    //******在主线程上更新视图
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        //*****将拉取的任务添加到视图
//                                        View customView = getLayoutInflater().inflate(R.layout.task, null);
//                                        TextView textView1 = customView.findViewById(R.id.textView1);
//                                        TextView textView2 = customView.findViewById(R.id.textView2);
//                                        TextView textView3 = customView.findViewById(R.id.textView3);
//                                        TextView textViewDate = customView.findViewById(R.id.task_date);
//                                        Button actionbutton=customView.findViewById(R.id.task_op);
//                                        // 设置TextView的文本
//                                        textView1.setText(task_name);
//                                        textView2.setText(task_content);
//                                        //****未拉取指派人员
//                                        textView3.setText(person);
//                                        textViewDate.setText(formattedDate); // 设置日期TextView的文本
//                                        // 将Task对象作为标签附加到视图上
//                                        customView.setTag(newTask); // newTask是刚刚创建的Task对象
//                                        // 将自定义布局的实例添加到containerLayout中
//                                        containerLayout.addView(customView);
//                                        printTaskList();
//                                        actionbutton.setOnClickListener(new Button.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//                                                showTaskOptionsDialog(customView); // 显示任务选项弹窗
//                                            }
//                                        });
//                                    }
//                                });
                }} else {
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
    //******拉取任务
}