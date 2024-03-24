package in_out_rcd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartstore.R;

import java.util.ArrayList;
import java.util.List;

public class add_time extends LinearLayout {
    private Context context;
    private  LinearLayout dateTimeBar;
    private TextView test_info;

    public static List<ImageView> date_index_list = new ArrayList<>();
    public add_time(Context context, LinearLayout dateTimeBa, TextView text_info) {
        super(context);
        this.context = context;
        this.dateTimeBar = dateTimeBa;
        this.test_info = text_info;
    }

    public void add_date_item(Integer start_idx, List<String> day, List<String> month){

        for(int i = start_idx; i < day.size(); i = i + 1){
            //新建一个视图对象date_item，这个对应日历上的每一天，就是要把一个一个的date_item添加进日历里
            //R.layout.date_item表示每一天的格式（上面一个半圆 + 中间日 + 下面月份）
            //dateTimeBar表示要把date_item放入的地方
            View date_item = LayoutInflater.from(context)
                    .inflate(R.layout.date_item, dateTimeBar, false);


            TextView rcd_day = date_item.findViewById(R.id.rcd_day);
            TextView rcd_mon = date_item.findViewById(R.id.rcd_month);
            View date_index = date_item.findViewById(R.id.date_index);
            rcd_day.setText(day.get(i));
            rcd_mon.setText(month.get(i));
            date_item.setTag(i);   //循环地设置每一天的数据，并为每一天设下一个tag，方便监听点击事件时知道是哪一天被点击

            dateTimeBar.addView(date_item); //把date_item放入日历中
            //每个日期的点击事件
            date_item.setOnClickListener(v -> {
                date_index.setVisibility(VISIBLE);
                for(int k = 0; k < date_index_list.size(); k++){
                    if((int)v.getTag() == k){
                        test_info.setText(in_out_rcd.test_information.get(k));
                    }
                    else{
                        date_index_list.get(k).setVisibility(INVISIBLE);
                    }
                }
            });
            date_index_list.add(date_item.findViewById(R.id.date_index));
        }
    }
}
