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
import java.util.Objects;

public class add_time extends LinearLayout {
    private Context context;
    private  LinearLayout dateTimeBar;
    private  LinearLayout page;

    public List<ImageView> date_index_list = new ArrayList<>();  //是否点击的图标

    public add_time(Context context, LinearLayout dateTimeBa, LinearLayout page) {
        super(context);
        this.context = context;
        this.page = page;
        this.dateTimeBar = dateTimeBa;
    }

    public void add_date_item(Integer start_idx, List<String> day, List<String> month){

        for(int i = start_idx; i < day.size(); i = i + 1){
            View date_item = LayoutInflater.from(context)
                    .inflate(R.layout.date_item, dateTimeBar, false);

            TextView rcd_day = date_item.findViewById(R.id.rcd_day);
            TextView rcd_mon = date_item.findViewById(R.id.rcd_month);
            View date_index = date_item.findViewById(R.id.date_index);

            rcd_day.setText(day.get(i));
            rcd_mon.setText(month.get(i));
            date_item.setTag(i);   //循环地设置每一天的数据，并为每一天设下一个tag，方便监听点击事件时知道是哪一天被点击

            dateTimeBar.addView(date_item); //把date_item放入日历中

            date_item.setOnClickListener(v -> {  //点击每一天，显示什么东西
                date_index.setVisibility(VISIBLE);
                page.removeAllViews();
                for(int k = 0; k < date_index_list.size(); k++){
                    if((int)v.getTag() == k){

                        if(in_out_rcd.flag){ //入库
                            if(in_out_rcd.IN != null && in_out_rcd.IN.get(k) != null){
                                for(View vv : Objects.requireNonNull(in_out_rcd.IN.get(k))){
                                    page.addView(vv);
                                }
                            }
                        }

                        else{ //出库
                            if(in_out_rcd.OUT!= null && in_out_rcd.OUT.get(k) != null){
                                for(View vv : Objects.requireNonNull(in_out_rcd.OUT.get(k))){
                                    page.addView(vv);
                                }
                            }
                        }
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
