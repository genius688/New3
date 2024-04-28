package self_edit_layout;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.smartstore.R;

import image_submit.attention_dialog;

public class add_lay_room_stg extends Dialog implements View.OnClickListener {
    private Integer Options;
    private Context context;
    private TextView mainTitle;
    private TextView mainDisciption;
    private CardView attentionButtonNotText;
    private CardView attentionButtonText;
    private EditText tile;
    private EditText layout_dis;
    private LinearLayout select_rooms;
    private ConstraintLayout seletc_stg;
    private PriorityListener listener;
    private TextView preText;
    private String myRoom;

    public add_lay_room_stg(Integer Options, Context context, PriorityListener listener) {
        super(context);
        this.context = context;
        this.Options = Options;
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(Options == 2){
            if(preText != null)
                preText.setTextColor(Color.parseColor("#77000000"));
            preText = (TextView) v;
            ((TextView) v).setTextColor(Color.parseColor("#78CBC0"));
        }
        if(v.getId() == R.id.woshi)
            myRoom = "卧室";
        else if(v.getId() == R.id.keting)
            myRoom = "客厅";
        else if(v.getId() == R.id.cesuo)
            myRoom = "厕所";
        else if(v.getId() == R.id.chufang)
            myRoom = "厨房";
        else if(v.getId() == R.id.cangting)
            myRoom = "餐厅";
        else if(v.getId() == R.id.shufang)
            myRoom = "书房";
        else if(v.getId() == R.id.yangtai)
            myRoom = "阳台";
        else if(v.getId() == R.id.tingyuan)
            myRoom = "庭院";
        else if(v.getId() == R.id.yimaojina)
            myRoom = "衣帽间";
    }
    public interface PriorityListener {
        public void add_refresh(boolean isAccept, String lay_title, String lay_description);
    }
    public void onCreate_Attention_Dialog() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.add_dialog, null);
        final Dialog dialog = new Dialog(context, R.style.style_dialog);
        dialog.setContentView(view);

        dialog.show();

        mainTitle = view.findViewById(R.id.Title);
        mainDisciption = view.findViewById(R.id.discription);
        tile = view.findViewById(R.id.editText2);
        layout_dis = view.findViewById(R.id.editText);
        select_rooms = view.findViewById(R.id.select_rooms);
        seletc_stg = view.findViewById(R.id.seletc_stg);
        attentionButtonText = view.findViewById(R.id.yes_btn);
        attentionButtonNotText = view.findViewById(R.id.no_btn);

        attentionButtonText.setOnClickListener(v -> {

            if(Options == 1){
                if(tile.getText().toString().equals("")){  //场景名称为空
                    Toast.makeText(context,"场景名称不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    listener.add_refresh(true, tile.getText().toString(), layout_dis.getText().toString());
                    dialog.cancel();
                }
            }
            else if(Options == 2){
                String tmp = tile.getText().toString();
                if(tmp == null || tmp.equals(""))
                    Toast.makeText(context,"空间名称不能为空",Toast.LENGTH_SHORT).show();
                else if(!tmp.contains(myRoom)){
                    attention_dialog dd = new attention_dialog("为了便于归纳推荐，建议空间名包含给定的九大标签哦~","空间名规范提醒" ,"我确定了", "我再改改",context, isAccept -> {
                        if(isAccept){
                            listener.add_refresh(true, tile.getText().toString(),"");
                            dialog.cancel();
                        }
                    });
                    dd.onCreate_Attention_Dialog();
                }
                else{
                    listener.add_refresh(true, tile.getText().toString(),"");
                    dialog.cancel();
                }
            }
            else{
                String tmp = tile.getText().toString();
                if(tmp.equals(""))
                    Toast.makeText(context,"储藏点名称不能为空",Toast.LENGTH_SHORT).show();
                else{
                    listener.add_refresh(true, tile.getText().toString(),"");
                    dialog.cancel();
                }
            }
        });
        attentionButtonNotText.setOnClickListener(v -> {
            if(Options == 1)
                listener.add_refresh(false,"","");
            else if(Options == 2)
                listener.add_refresh(false,"","");
            else
                listener.add_refresh(false,"","");
            dialog.cancel();
        });

        if(Options == 1){ //添加场景
            ((TextView)view.findViewById(R.id.textView5)).setText("场景描述");
            mainTitle.setText("新增场景");
            mainDisciption.setText("场景是指家庭、宿舍、办公室、临时酒店房间等有存储需求的生活场景");
            layout_dis.setVisibility(View.VISIBLE);
            select_rooms.setVisibility(View.GONE);
            seletc_stg.setVisibility(View.GONE);
        }
        else if(Options == 2){
            ((TextView)view.findViewById(R.id.textView5)).setText("选择空间类别");
            mainTitle.setText("新增空间");
            mainDisciption.setText("空间是指每一个场景中的各个功能布局，如卧室，客厅，庭院等，空间名建议包含以下九大标签");
            layout_dis.setVisibility(View.GONE);
            select_rooms.setVisibility(View.VISIBLE);
            seletc_stg.setVisibility(View.GONE);
            view.findViewById(R.id.woshi).performClick();
            preText = null;
            view.findViewById(R.id.woshi).setOnClickListener(this);
            view.findViewById(R.id.keting).setOnClickListener(this);
            view.findViewById(R.id.cesuo).setOnClickListener(this);
            view.findViewById(R.id.chufang).setOnClickListener(this);
            view.findViewById(R.id.cangting).setOnClickListener(this);
            view.findViewById(R.id.shufang).setOnClickListener(this);
            view.findViewById(R.id.yangtai).setOnClickListener(this);
            view.findViewById(R.id.tingyuan).setOnClickListener(this);
            view.findViewById(R.id.yimaojina).setOnClickListener(this);
            view.findViewById(R.id.woshi).performClick();
        }
        else{
            ((TextView)view.findViewById(R.id.textView5)).setText("参考储藏点");
            mainTitle.setText("新增储藏点");
            mainDisciption.setText("储藏点是指每一个空间中物品具体存放点，建议包含以下关键词哦");
            layout_dis.setVisibility(View.GONE);
            select_rooms.setVisibility(View.GONE);
            seletc_stg.setVisibility(View.VISIBLE);
        }

        Window window = dialog.getWindow();
        if (window != null) { // 确保window不为null
            WindowManager.LayoutParams lp = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 设置宽度充满屏幕
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setAttributes(lp);
        }
    }
}
