package image_submit;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.smartstore.R;

public class attention_dialog extends Dialog {
    private String attention_Title;
    private String attention_content;
    private String attention_button_text;
    private String attention_button_not_text;
    private PriorityListener listener;
    private Context context;
    private TextView attentionTitle;
    private TextView attentionContent;
    private TextView attentionButtonNotText;
    private TextView attentionButtonText;

    public interface PriorityListener {
        public void attention_refresh(boolean isAccept);
    }

    public attention_dialog(String attention_content, String attention_Title, String attention_button_text, String attention_button_not_text, Context context, PriorityListener listener) {
        super(context);
        this.context = context;
        this.attention_Title = attention_Title;
        this.attention_content = attention_content;
        this.attention_button_text = attention_button_text;
        this.attention_button_not_text = attention_button_not_text;
        this.listener = listener;
    }
    public void onCreate_Attention_Dialog() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.attention_dialog, null);
        final Dialog dialog = new Dialog(context, R.style.style_dialog);
        dialog.setContentView(view);

        dialog.show();

        attentionTitle = view.findViewById(R.id.attention_Title);
        attentionContent = view.findViewById(R.id.attention_content);
        attentionButtonText = view.findViewById(R.id.attention_button_text);
        attentionButtonNotText = view.findViewById(R.id.attention_button_not_text);
        attentionTitle.setText(attention_Title);
        attentionContent.setText(attention_content);
        attentionButtonText.setText(attention_button_text);
        attentionButtonNotText.setText(attention_button_not_text);

        attentionButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.attention_refresh(true);  //传回参数
                dialog.cancel();
            }
        });

        attentionButtonNotText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.attention_refresh(false);  //传回参数
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

}
