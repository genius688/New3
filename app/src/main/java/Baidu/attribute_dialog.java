package Baidu;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.smartstore.R;

import java.util.ArrayList;

public class attribute_dialog extends Dialog implements View.OnClickListener {

    public ArrayList<Float> attributes;
    public String spaces;
    public String keywords;
    Context context;
    private CardView attentionButtonText;
    private CardView attentionButtonNotText;
    private attribute_dialog.PriorityListener listener;
    private TextView a11;
    private TextView a12;
    private TextView a13;
    private TextView a21;
    private TextView a22;
    private TextView a23;
    private TextView a31;
    private TextView a32;
    private TextView a33;
    private TextView a41;
    private TextView a42;
    private TextView a43;
    private TextView a51;
    private TextView a52;
    private TextView a53;
    private TextView a61;
    private TextView a62;
    private TextView a63;
    private EditText item_keyword;
    private ConstraintLayout parent_layout;
    private TextView space;
    private RadioGroup chose_room;
    private TextView save_select;
    private ArrayList<String> rooms;

    public attribute_dialog(String keyword, String space, ArrayList<Float> attribute , Context context, PriorityListener listener) {
        super(context);
        this.context = context;

        this.attributes = new ArrayList<>(attribute.size());
        attributes.addAll(attribute);

        this.spaces = space;
        this.keywords = keyword;
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.a11){
            mySetTextStyle(a11,a12,a13);
            mySetAttribute(0,0);
        }
        else if(v.getId() == R.id.a12){
            mySetTextStyle(a12,a11,a13);
            mySetAttribute(0,1);
        }
        else if(v.getId() == R.id.a13) {
            mySetTextStyle(a13,a11,a12);
            mySetAttribute(0,2);
        }
        else if(v.getId() == R.id.a21){
            mySetTextStyle(a21,a22,a23);
            mySetAttribute(1,0);
        }
        else if(v.getId() == R.id.a22){
            mySetTextStyle(a22,a21,a23);
            mySetAttribute(1,1);
        }
        else if(v.getId() == R.id.a23){
            mySetTextStyle(a23,a22,a21);
            mySetAttribute(1,2);
        }
        else if(v.getId() == R.id.a31){
            mySetTextStyle(a31,a32,a33);
            mySetAttribute(2,0);
        }
        else if(v.getId() == R.id.a32){
            mySetTextStyle(a32,a33,a31);
            mySetAttribute(2,1);
        }
        else if(v.getId() == R.id.a33){
            mySetTextStyle(a33,a32,a31);
            mySetAttribute(2,2);
        }
        else if(v.getId() == R.id.a41){
            mySetTextStyle(a41,a42,a43);
            mySetAttribute(3,0);
        }
        else if(v.getId() == R.id.a42){
            mySetTextStyle(a42,a43,a41);
            mySetAttribute(3,1);
        }
        else if(v.getId() == R.id.a43){
            mySetTextStyle(a43,a42,a41);
            mySetAttribute(3,2);
        }
        else if(v.getId() == R.id.a53){
            mySetTextStyle(a53,a52,a51);
            mySetAttribute(4,2);
        }
        else if(v.getId() == R.id.a51){
            mySetTextStyle(a51,a52,a53);
            mySetAttribute(4,0);
        }
        else if(v.getId() == R.id.a52){
            mySetTextStyle(a52,a53,a51);
            mySetAttribute(4,1);
        }
        else if(v.getId() == R.id.a61){
            mySetTextStyle(a61,a62,a63);
            mySetAttribute(5,0);
        }
        else if(v.getId() == R.id.a62){
            mySetTextStyle(a62,a63,a61);
            mySetAttribute(5,1);
        }
        else if(v.getId() == R.id.a63){
            mySetTextStyle(a63,a61,a62);
            mySetAttribute(5,2);
        }
    }

    //t1凸显，t2,t3弱显
    public void mySetTextStyle(TextView t1, TextView t2, TextView t3){
        t1.setTextColor(Color.parseColor("#33B3A3"));
        t1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL), Typeface.BOLD);

        t2.setTextColor(Color.parseColor("#8800445E"));
        t2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL), Typeface.NORMAL);

        t3.setTextColor(Color.parseColor("#8800445E"));
        t3.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL), Typeface.NORMAL);
    }

    public void mySetAttribute(Integer a, Integer x){
        switch (a){
            case 0:{
                if(x == 0)  attributes.set(0, 1.0f);
                else if(x == 1) attributes.set(0, 2.0f);
                else attributes.set(0, 3.0f);
                return;
            }
            case 1:{
                if(x == 0)  attributes.set(1, 1.0f);
                else if(x == 1) attributes.set(1, 2.0f);
                else attributes.set(1, 3.0f);
                return;
            }
            case 2:{
                if(x == 0)  attributes.set(2, 1.0f);
                else if(x == 1) attributes.set(2, 2.0f);
                else attributes.set(2, 3.0f);
                return;
            }
            case 3:{
                if(x == 0)  attributes.set(3, 1.0f);
                else if(x == 1) attributes.set(3, 2.0f);
                else attributes.set(3, 3.0f);
                return;
            }
            case 4:{
                if(x == 0)  attributes.set(4, 1.0f);
                else if(x == 1) attributes.set(4, 2.0f);
                else attributes.set(4, 3.0f);
                return;
            }
            case 5:{
                if(x == 0)  attributes.set(5, 1.0f);
                else if(x == 1) attributes.set(5, 2.0f);
                else attributes.set(5, 3.0f);
                return;
            }
        }
    }
    public interface PriorityListener {
        public void attribute_Fresh(boolean isAccept, ArrayList<Float> single_attribute, String sapces, String keywords);
    }

    public void setInitial(){
        if(attributes.get(0) <= 1) mySetTextStyle(a11,a12,a13);
        else if(attributes.get(0) > 1 && attributes.get(0) <= 2) mySetTextStyle(a12,a11,a13);
        else mySetTextStyle(a13,a11,a12);

        if(attributes.get(1) <= 1) mySetTextStyle(a21,a22,a23);
        else if(attributes.get(1) > 1 && attributes.get(1) <= 2) mySetTextStyle(a22,a21,a23);
        else mySetTextStyle(a23,a21,a22);

        if(attributes.get(2) <= 1) mySetTextStyle(a31,a32,a33);
        else if(attributes.get(2) > 1 && attributes.get(2) <= 2) mySetTextStyle(a32,a31,a33);
        else mySetTextStyle(a33,a31,a32);

        if(attributes.get(3) <= 1) mySetTextStyle(a41,a42,a43);
        else if(attributes.get(3) > 1 && attributes.get(3) <= 2) mySetTextStyle(a42,a41,a43);
        else mySetTextStyle(a43,a41,a42);

        if(attributes.get(4) <= 1) mySetTextStyle(a51,a52,a53);
        else if(attributes.get(4) > 1 && attributes.get(4) <= 2) mySetTextStyle(a52,a51,a53);
        else mySetTextStyle(a53,a51,a52);

        if(attributes.get(5) <= 1) mySetTextStyle(a61,a62,a63);
        else if(attributes.get(5) > 1 && attributes.get(5) <= 2) mySetTextStyle(a62,a61,a63);
        else mySetTextStyle(a63,a61,a62);

        item_keyword.setText(keywords);
        space.setText(spaces);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void onCreate_Attention_Dialog() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.select_dialog, null);
        final Dialog dialog = new Dialog(context, R.style.style_dialog);
        dialog.setContentView(view);

        dialog.show();

        attentionButtonText = dialog.findViewById(R.id.yes_btn);
        attentionButtonNotText = dialog.findViewById(R.id.no_btn);

        a11 = dialog.findViewById(R.id.a11);
        a12 = dialog.findViewById(R.id.a12);
        a13 = dialog.findViewById(R.id.a13);

        a21 = dialog.findViewById(R.id.a21);
        a22 = dialog.findViewById(R.id.a22);
        a23 = dialog.findViewById(R.id.a23);

        a31 = dialog.findViewById(R.id.a31);
        a32 = dialog.findViewById(R.id.a32);
        a33 = dialog.findViewById(R.id.a33);

        a41 = dialog.findViewById(R.id.a41);
        a42 = dialog.findViewById(R.id.a42);
        a43 = dialog.findViewById(R.id.a43);

        a51 = dialog.findViewById(R.id.a51);
        a52 = dialog.findViewById(R.id.a52);
        a53 = dialog.findViewById(R.id.a53);

        a61 = dialog.findViewById(R.id.a61);
        a62 = dialog.findViewById(R.id.a62);
        a63 = dialog.findViewById(R.id.a63);

        item_keyword = dialog.findViewById(R.id.textView2);
        space = dialog.findViewById(R.id.textView3);
        parent_layout = dialog.findViewById(R.id.parent_layout);
        chose_room = dialog.findViewById(R.id.chose_room);
        save_select = dialog.findViewById(R.id.save_select);
        rooms = Baidu.layout_room;

        for (int i = 0; i < rooms.size(); i++) {
            ContextThemeWrapper themeWrapper = new ContextThemeWrapper(context, R.style.MyRadioButton);
            RadioButton radioButton = new RadioButton(themeWrapper);
            radioButton.setText(rooms.get(i));
            radioButton.setId(View.generateViewId());
            chose_room.addView(radioButton);
        }

        setInitial();

        a11.setOnClickListener(this);
        a12.setOnClickListener(this);
        a13.setOnClickListener(this);

        a21.setOnClickListener(this);
        a22.setOnClickListener(this);
        a23.setOnClickListener(this);

        a31.setOnClickListener(this);
        a32.setOnClickListener(this);
        a33.setOnClickListener(this);

        a41.setOnClickListener(this);
        a42.setOnClickListener(this);
        a43.setOnClickListener(this);

        a51.setOnClickListener(this);
        a52.setOnClickListener(this);
        a53.setOnClickListener(this);

        a61.setOnClickListener(this);
        a62.setOnClickListener(this);
        a63.setOnClickListener(this);

        space.setOnClickListener(v -> {
            int radioButtonCount = chose_room.getChildCount();
            for (int i = 0; i < radioButtonCount; i++) {
                View child = chose_room.getChildAt(i);
                if (child instanceof RadioButton) {
                    RadioButton radioButton = (RadioButton) child;
                    String buttonText = radioButton.getText().toString();
                    if(buttonText.equals(spaces)){
                        radioButton.setChecked(true);}
                }
            }

            chose_room.setVisibility(View.VISIBLE);
        });

        save_select.setOnClickListener(v -> {
            chose_room.clearCheck();
            chose_room.setVisibility(View.GONE);
        });

        chose_room.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1) {
                RadioButton checkedRadioButton = dialog.findViewById(checkedId);
                String selectedOption = checkedRadioButton.getText().toString();
                space.setText(selectedOption);
                spaces = selectedOption;
            } else {
                Log.d("RadioGroup", "No option selected");
            }
        });


        attentionButtonText.setOnClickListener(v -> {
            keywords = String.valueOf(item_keyword.getText()).trim();
            listener.attribute_Fresh(true ,attributes, spaces, keywords);  //传回参数
            dialog.cancel();
        });

        attentionButtonNotText.setOnClickListener(v -> {
            keywords = String.valueOf(item_keyword.getText()).trim();
            listener.attribute_Fresh(false,null, null, null);  //传回参数
            dialog.cancel();

        });

        parent_layout.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                keywords = String.valueOf(item_keyword.getText()).trim();
                item_keyword.clearFocus();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(item_keyword.getWindowToken(), 0);
            }
            return false;
        });

        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setAttributes(lp);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }
    }
}
