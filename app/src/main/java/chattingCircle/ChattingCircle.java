package chattingCircle;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartstore.Circle;
import com.example.smartstore.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import image_submit.Utils;
import image_submit.attention_dialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChattingCircle extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button commend;
    private Button mine;
    Context context;
    ImageView postimage;
    TextView postname;
    TextView postrlstime;
    TextView postlikes;
    public Circle user_img1;
    private static final int STORAGE_PERMISSION = 1;
    int REQUEST_CODE_CAPTURE_IMAGE = 100;
    private File file;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private Activity activity;
    ImageView icon1;
    ImageView icon2;
    EditText user_sg;
    EditText user_name;
    TextView thumbs;
    TextView work;
    ImageView commend_icon;
    ImageView mine_icon;
    int works=0;
    int like=0;
    int serverId;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_circle);
        activity = (Activity)this;
        context=(Context)this;

        preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        editor = preferences.edit();

        serverId = preferences.getInt("uer_id",-1);
        thumbs=findViewById(R.id.thumbs);
        work=findViewById(R.id.concerns);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        icon2 = findViewById(R.id.icon2);
        icon1 = findViewById(R.id.icon1);
        commend = findViewById(R.id.commend);
        mine = findViewById(R.id.my_chatting_circle);
        commend_icon=findViewById(R.id.commend_icon);
        mine_icon=findViewById(R.id.my_circle_icon);
        user_sg = findViewById(R.id.user_signature);
        user_name = findViewById(R.id.user_name);
        postimage=findViewById(R.id.postmedia);
        postname=findViewById(R.id.postname);
        postrlstime=findViewById(R.id.postrlstime);
        postlikes=findViewById(R.id.postlikes);
        user_img1=findViewById(R.id.user_img1);

        getInfo(serverId);
        getworks(serverId);
        like=0;
        getlikesnum(serverId);
        user_sg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
        user_sg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @SuppressLint("ResourceType")
            @Override
            public void afterTextChanged(Editable s) {
                String userSg = s.toString();
                editor.putString("usersg", userSg);
                editor.apply();
                int updateId = serverId;
                String updatesg = preferences.getString("usersg", null);
                updateInfosg(updateId,updatesg);
            }
        });
        user_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
        user_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @SuppressLint("ResourceType")
            @Override
            public void afterTextChanged(Editable s) {
                String userName = s.toString();
                editor.putString("username", userName);
                editor.apply();
                int updateId = serverId;
                String updatename = preferences.getString("username", null);
                updateInfoname(updateId,updatename);
            }
        });

        ViewGroup rootView = (ViewGroup) findViewById(R.id.myCircle_root);
        if (rootView != null) {
            rootView.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!(v instanceof EditText)) {
                        user_name.clearFocus();
                        user_sg.clearFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
                        }
                    }
                }
                return false;
            });
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setTextColor(Color.BLACK);
                button2.setTextColor(Color.parseColor("#cccccc"));
                icon1.setVisibility(View.VISIBLE);
                icon2.setVisibility(View.GONE);
                LinearLayout linearLayout = findViewById(R.id.container);
                linearLayout.removeAllViews();
                getshare(serverId);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setTextColor(Color.parseColor("#cccccc"));
                button2.setTextColor(Color.BLACK);
                icon1.setVisibility(View.GONE);
                icon2.setVisibility(View.VISIBLE);
                LinearLayout linearLayout = findViewById(R.id.container);
                linearLayout.removeAllViews();
                getlike(serverId);
            }
        });
        boolean shouldTriggerButton2Click = getIntent().getBooleanExtra("trigger_button2_click", false);
        if (shouldTriggerButton2Click) {
            button2.performClick();
        }
        else{
            button1.performClick();
        }
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mine.setTextColor(Color.parseColor("#204D36"));
                commend.setTextColor(Color.parseColor("#53856C"));
                mine_icon.setVisibility(View.VISIBLE);
                commend_icon.setVisibility(View.INVISIBLE);
            }
        });
        commend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mine.setTextColor(Color.parseColor("#53856C"));
                commend.setTextColor(Color.parseColor("#204D36"));
                mine_icon.setVisibility(View.INVISIBLE);
                commend_icon.setVisibility(View.VISIBLE);
                Intent intent = new Intent(ChattingCircle.this, Chattingcircle_recommend.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        user_img1.setOnClickListener(v -> xzImage());
    }

    protected void onResume() {
        super.onResume();
        like=0;
        getlikesnum(serverId);
        getworks(serverId);
    }

    private ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    String realPath = Utils.getRealPath(this, data);
                    file = new File(realPath);
                    System.out.println("成功"+file );
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    System.out.println("成功l"+bitmap );
                    user_img1.setImageBitmap(bitmap);
                    int serverId = preferences.getInt("uer_id", -1);
                    final_upload(file,serverId);

                }
            });
    public void final_upload(File file,int id){
        File it_img = file;
        JSONObject json = new JSONObject();
        try {
            json.put("u_img",it_img);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
        Request request = new Request.Builder()
                .url("http://120.26.248.74:8080/updateUserInfo?uid="+id)
                .post(body)
                .build();

        OkHttpClient client1 = new OkHttpClient();
        client1.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(ChattingCircle.this, "网络问题", Toast.LENGTH_SHORT).show());
            }
            @Override
            public void onResponse(Call call, Response response) {
                assert response.body() != null;
                if (!response.isSuccessful()) {
                    ChattingCircle.this.runOnUiThread(() -> {
                        try {
                            String responseBody = response.body().string();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                else{
                    runOnUiThread(() -> Toast.makeText(ChattingCircle.this, "提交成功！", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
    private void xzImage() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CAMERA}, STORAGE_PERMISSION);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        imagePickerLauncher.launch(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
            else {
                attention_dialog dd = new attention_dialog("开启读取照片权限，\n就可以上传头像啦!" ,"获取照片权限未开启哦！","去开启", "下次再来",this, isAccept -> {
                    if(isAccept){
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                });
                dd.onCreate_Attention_Dialog();
            }
        }
        if(requestCode == REQUEST_CODE_CAPTURE_IMAGE){
            attention_dialog dd = new attention_dialog("开启相机和照片权限，\n才能上传头像哦~" ,"获取相机权限开启！","去开启", "下次再来",this, isAccept -> {
                if(isAccept){
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            });
            dd.onCreate_Attention_Dialog();
        }
    }
    public void getInfo(int id){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        StringBuilder queryParams = new StringBuilder();
        queryParams.append("uid=").append(id);

        RequestBody body = RequestBody.create(JSON, "");
        String url = "http://120.26.248.74:8080/getUserInfo?" + queryParams;
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

                                String uname = jsonObject.getString("uname");
                                String u_signature = jsonObject.getString("u_signature");
                                String u_img=jsonObject.getString("u_img");

                                activity.runOnUiThread(() -> {
                                    String serverUname = uname;
                                    String serverUsignature = u_signature;
                                    String serverimg=u_img;

                                    editor.putString("username",serverUname);
                                    editor.putString("usersg",serverUsignature);
                                    editor.apply();

                                    user_name.setText(serverUname);
                                    user_sg.setText(serverUsignature);
                                    new AsyncTask<Void, Void, Bitmap>() {
                                        @Override
                                        protected Bitmap doInBackground(Void... voids) {
                                            try {
                                                URL imageUrl = new URL(serverimg);
                                                HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
                                                connection.setDoInput(true);
                                                connection.connect();
                                                InputStream input = connection.getInputStream();
                                                return BitmapFactory.decodeStream(input);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                                return null;
                                            }
                                        }
                                        @Override
                                        protected void onPostExecute(Bitmap bitmap) {
                                            if (bitmap != null) {
                                                user_img1.setImageBitmap(bitmap);
                                            } else {
                                                //Toast.makeText(activity, "加载头像失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }.execute();
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
    public void getshare(int id){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        StringBuilder queryParams = new StringBuilder();
        queryParams.append("uid=").append(id);
        RequestBody body = RequestBody.create(JSON, "");
        String url = "http://120.26.248.74:8080/getUserShared?" + queryParams;
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
                            String[] names=new String [jsonArray.length()];
                            Bitmap[] medias=new Bitmap[jsonArray.length()];

                            String[] times=new String [jsonArray.length()];
                            int[] likes=new int [jsonArray.length()];
                            int[] post_ids=new int [jsonArray.length()];
                            for (int k = 0; k < jsonArray.length(); k++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(k);

                                medias[k]=BitmapFactory.decodeFile(jsonObject.getString("post_media"));
                                String pname = jsonObject.getString("post_name");
                                names[k]=pname;
                                String ptime = jsonObject.getString("post_rls_time");
                                int commaIndex = ptime.indexOf("T");
                                String partOfPtime = ptime.substring(0, commaIndex);
                                times[k]=partOfPtime;
                                int plikes = jsonObject.getInt("post_likes");
                                likes[k]=plikes;
                                int pId=jsonObject.getInt("post_id");
                                post_ids[k]=pId;
                                System.out.println("我帖子呢" + jsonObject);
                            }
                            activity.runOnUiThread(() -> {
                                for (int k = 0; k < jsonArray.length(); k++) {
                                    int finalK = k;
                                    LinearLayout sub_interface_container = activity.findViewById(R.id.container);

                                    View single_items = LayoutInflater.from(sub_interface_container.getContext())
                                            .inflate(R.layout.chatting_my_content, sub_interface_container, false);

                                    ((TextView) single_items.findViewById(R.id.postname)).setText(names[finalK]);
                                    ((TextView) single_items.findViewById(R.id.postrlstime)).setText(times[finalK]);
                                    ((TextView) single_items.findViewById(R.id.postlikes)).setText(""+likes[finalK]);
                                    ((ImageView) single_items.findViewById(R.id.postmedia)).setImageBitmap(medias[finalK]);

                                    single_items.setOnClickListener(v -> {
                                        Intent intent = new Intent(context, Postings.class);
                                        intent.putExtra("trigger_single_items_click", true);
                                        intent.putExtra("post_Id", post_ids[finalK]);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                    });
                                    sub_interface_container.addView(single_items);
                                }
                            });
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
    public void getworks(int id){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        StringBuilder queryParams = new StringBuilder();
        queryParams.append("uid=").append(id);

        RequestBody body = RequestBody.create(JSON, "");
        String url = "http://120.26.248.74:8080/getUserShared?" + queryParams;
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
                            works=jsonArray.length();
                            activity.runOnUiThread(() -> {
                                work.setText(""+works);
                            });
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
    public void getlikesnum(int id){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        StringBuilder queryParams = new StringBuilder();
        queryParams.append("uid=").append(id);
        RequestBody body = RequestBody.create(JSON, "");
        String url = "http://120.26.248.74:8080/getUserShared?" + queryParams;
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
                            like=0;
                            for (int k = 0; k < jsonArray.length(); k++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(k);
                                int plikes = jsonObject.getInt("post_likes");
                                like=like+plikes;
                            }
                            activity.runOnUiThread(() -> {
                                thumbs.setText(like+"");
                            });
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
    public void getlike(int id){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        StringBuilder queryParams = new StringBuilder();
        queryParams.append("uid=").append(id);

        RequestBody body = RequestBody.create(JSON, "");
        String url = "http://120.26.248.74:8080/getUserLiked?" + queryParams;
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
                            String[] names=new String [jsonArray.length()];
                            Bitmap[] medias=new Bitmap[jsonArray.length()];

                            String[] times=new String [jsonArray.length()];
                            int[] likes=new int [jsonArray.length()];
                            int[] post_ids=new int [jsonArray.length()];
                            for (int k = 0; k < jsonArray.length(); k++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(k);

                                medias[k]=BitmapFactory.decodeFile(jsonObject.getString("post_media"));
                                String pname = jsonObject.getString("post_name");
                                names[k]=pname;
                                String ptime = jsonObject.getString("post_rls_time");
                                int commaIndex = ptime.indexOf("T");
                                String partOfPtime = ptime.substring(0, commaIndex);
                                times[k]=partOfPtime;
                                int plikes = jsonObject.getInt("post_likes");
                                likes[k]=plikes;
                                int pId=jsonObject.getInt("post_id");
                                post_ids[k]=pId;
                                System.out.println("我帖子呢" + jsonObject);
                            }
                            activity.runOnUiThread(() -> {
                                for (int k = 0; k < jsonArray.length(); k++) {
                                    int finalK = k;
                                    LinearLayout sub_interface_container = activity.findViewById(R.id.container);

                                    View single_items = LayoutInflater.from(sub_interface_container.getContext())
                                            .inflate(R.layout.chatting_my_content, sub_interface_container, false);

                                    ((TextView) single_items.findViewById(R.id.postname)).setText(names[finalK]);
                                    ((TextView) single_items.findViewById(R.id.postrlstime)).setText(times[finalK]);
                                    ((TextView) single_items.findViewById(R.id.postlikes)).setText(""+likes[finalK]);
                                    System.out.println("-------+"+medias[finalK]);
                                    ((ImageView) single_items.findViewById(R.id.postmedia)).setImageBitmap(medias[finalK]);

                                    single_items.setOnClickListener(v -> {
                                        Intent intent = new Intent(context, Postings.class);
                                        intent.putExtra("trigger_single_items_click", true);
                                        intent.putExtra("post_Id", post_ids[finalK]);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                    });
                                    sub_interface_container.addView(single_items);
                                }
                            });
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

    public void updateInfoname(Integer uid, String uname) {
        OkHttpClient client1 = new OkHttpClient().newBuilder().build();
        MediaType JSON1 = MediaType.parse("application/json; charset=utf-8");
        StringBuilder queryParams1 = new StringBuilder();
        queryParams1.append("uid=").append(uid).append("&");
        queryParams1.append("uname=").append(uname);
        RequestBody body1 = RequestBody.create(JSON1, "");
        String url1 = "http://120.26.248.74:8080/updateUserInfo?" + queryParams1;
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
    public void updateInfosg(Integer uid, String u_sg) {
        OkHttpClient client1 = new OkHttpClient().newBuilder().build();
        MediaType JSON1 = MediaType.parse("application/json; charset=utf-8");
        StringBuilder queryParams1 = new StringBuilder();
        queryParams1.append("uid=").append(uid).append("&");
        queryParams1.append("u_signature=").append(u_sg);
        RequestBody body1 = RequestBody.create(JSON1, "");
        String url1 = "http://120.26.248.74:8080/updateUserInfo?" + queryParams1;
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