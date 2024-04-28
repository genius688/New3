package search;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartstore.R;

import java.util.ArrayList;

public class search_dialog extends Dialog implements View.OnClickListener {
    private Context context;
    private String target;

    public ArrayList<String> content = new ArrayList<>();
    public search_dialog(Context context, String target) {
        super(context);
        this.context = context;
        this.content = search.res;
        this.target = target;
    }

    public void onCreate_Attention_Dialog() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_dialog, null);
        final Dialog dialog = new Dialog(context, R.style.style_dialog);
        dialog.setContentView(view);

        TextView tv = view.findViewById(R.id.Title);
        tv.setText(target);
        for(String s : content){
            LinearLayout sc = view.findViewById(R.id.content);
            TextView textView = new TextView(context);
            textView.setText(s);
            textView.setTextSize(15);
            textView.setTextColor(Color.parseColor("#06A28F"));
            sc.addView(textView);
        }

        dialog.show();

        view.findViewById(R.id.returnB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

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

    @Override
    public void onClick(View v) {

    }
}
