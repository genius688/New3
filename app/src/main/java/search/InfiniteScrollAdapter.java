package search;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartstore.R;

import java.util.ArrayList;
import java.util.List;

public class InfiniteScrollAdapter extends RecyclerView.Adapter<InfiniteScrollAdapter.ViewHolder> {

    //数据列表（Data List）：
    //适配器内部通常维护一个数据列表（如ArrayList），该列表包含了要在RecyclerView中显示的数据项
    public List<info> dataList;
    static public   List<List<View>> tmp = new ArrayList<>();
    public int infiniteItemCount;


    //构造函数（Constructor）：
    //适配器通常需要一个构造函数来接收数据列表或其他必要的信息，以便能够创建和绑定视图。
    public InfiniteScrollAdapter(List<info> dataList, int infiniteItemCount) {
        this.dataList = new ArrayList<>(dataList);
        // 复制数据以创建无限循环效果！！！
        for (int i = 0; i < infiniteItemCount; i++) {
            this.dataList.addAll(dataList);  //改变数据
        }
        this.infiniteItemCount = infiniteItemCount;
    }

    //onCreateViewHolder(ViewGroup parent, int viewType) 方法：
    //这个方法负责创建RecyclerView的每一项的布局的根视图。通常在这里，你会基于一个布局文件来实例化一个视图，并创建一个ViewHolder来持有这个视图的引用。
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }
    //onBindViewHolder(VH holder, int position) 方法：
    //这个方法用于将数据绑定到ViewHolder所持有的视图上。在这里，你根据数据列表中的位置position获取相应的数据项，并将其显示在holder的视图上。
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.v.invalidate(); // 标记View为需要重新绘制
        holder.v.requestLayout(); // 请求重新测量和布局


        holder.cardLayout.removeAllViews();

        holder.room_title.setText(dataList.get(position).room_title);
        holder.room_discription.setText((dataList.get(position).room_discription));
        if(position == dataList.size() - 1){
             holder.next_room_title.setText("暂时没有了");
        }
        else{
            holder.next_room_title.setText(dataList.get(position).room_title);
        }

        if(position % 2 != 0){
            holder.search_space_blue.setVisibility(View.VISIBLE);
            holder.search_space_green.setVisibility(View.GONE);
            holder.search_arrow_blue.setVisibility(View.VISIBLE);
            holder.search_arrow_green.setVisibility(View.GONE);
        }
        else{
            holder.search_space_green.setVisibility(View.VISIBLE);
            holder.search_space_blue.setVisibility(View.GONE);
            holder.search_arrow_green.setVisibility(View.VISIBLE);
            holder.search_arrow_blue.setVisibility(View.GONE);
        }

        if (tmp.size() - 1 < position){
            tmp.add(new ArrayList<>());
        }

        int numberOfCards = getNumberOfCardsForPosition(position); // 确保这个方法返回正确的卡片数量

        String[] title = {"小衣柜", "书桌抽屉第二格", "书架第三格", "左床头柜"};

        for (int i = 0; i < numberOfCards; i++) {
            View itemCard = LayoutInflater.from(holder.cardLayout.getContext())
                    .inflate(R.layout.item_card_item, holder.cardLayout, false);

            if(position % 2 == 0) {
                ((ImageView) itemCard.findViewById(R.id.search_card_green)).setVisibility(View.VISIBLE);
                ((ImageView) itemCard.findViewById(R.id.search_card_blue)).setVisibility(View.GONE);
            }
            else{
                ((ImageView) itemCard.findViewById(R.id.search_card_green)).setVisibility(View.GONE);
                ((ImageView) itemCard.findViewById(R.id.search_card_blue)).setVisibility(View.VISIBLE);
            }

            ((TextView) itemCard.findViewById(R.id.textViewCardName)).setText("title[i]");
            itemCard.setTag(String.valueOf(i));
            itemCard.setOnClickListener(v -> {
                holder.cardLayout.expandItem(Integer.parseInt((String) v.getTag()));
                if(tmp.get(position).size() >= 1){
                    for (int k = 0; k < tmp.get(position).size(); k = k + 1) {
                        if (Integer.parseInt((String) v.getTag()) != k && k != tmp.get(position).size()-1) {
                            (tmp.get(position)).get(k).findViewById(R.id.display).setVisibility(View.GONE);
                        } else
                            (tmp.get(position)).get(k).findViewById(R.id.display).setVisibility(View.VISIBLE);
                    }
                }
            });

            //设置星标收纳点(非星标状态下点击 -> 设置为星标状态)
            itemCard.findViewById(R.id.set_into_star_btn).setOnClickListener(v -> {
                if(position % 2 == 0){
                        itemCard.findViewById(R.id.set_into_star_btn_green).setVisibility(View.VISIBLE);
                        itemCard.findViewById(R.id.set_into_star_btn).setVisibility(View.GONE);
                }
                else{
                        itemCard.findViewById(R.id.set_into_star_btn_blue).setVisibility(View.VISIBLE);
                        itemCard.findViewById(R.id.set_into_star_btn).setVisibility(View.GONE);
                }
                    //弹窗提醒成功
                    Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(v.getContext(), "设置星标收纳点成功！", Toast.LENGTH_SHORT).show();
                        }
                    };
                    handler.postDelayed(runnable, 0); // 3000毫秒 = 3秒
            });
            //取消星标收纳点(星标状态下点击 -> 取消星标状态)
            itemCard.findViewById(R.id.set_into_star_btn_green).setOnLongClickListener(v -> {
                    itemCard.findViewById(R.id.set_into_star_btn_green).setVisibility(View.GONE);
                    itemCard.findViewById(R.id.set_into_star_btn).setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(v.getContext(), "取消成功！", Toast.LENGTH_SHORT).show();
                    }
                };
                handler.postDelayed(runnable, 0); // 3000毫秒 = 3秒
                    return true;
            });
            itemCard.findViewById(R.id.set_into_star_btn_blue).setOnLongClickListener(v -> {
                    itemCard.findViewById(R.id.set_into_star_btn_blue).setVisibility(View.GONE);
                    itemCard.findViewById(R.id.set_into_star_btn).setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(v.getContext(), "取消成功！", Toast.LENGTH_SHORT).show();
                    }
                };
                handler.postDelayed(runnable, 0); // 3000毫秒 = 3秒
                    return true;
            });


            // 确保itemCard中有一个ID为R.id.linear_layout的LinearLayout
            LinearLayout linearLayout = itemCard.findViewById(R.id.linear_layout);

                // 创建single_items视图，并添加到linearLayout中
                View single_items = LayoutInflater.from(linearLayout.getContext())
                        .inflate(R.layout.single_item, linearLayout, false);
                ((ImageView) single_items.findViewById(R.id.star_object_1)).setImageResource(R.drawable.star_thing_example_1);
                ((TextView) single_items.findViewById(R.id.itemtitle)).setText("阿司匹林");
                ((TextView) single_items.findViewById(R.id.item_disciption)).setText("卧室 - 1号床头柜 - 第3抽屉");
                ((TextView) single_items.findViewById(R.id.item_deadline)).setText("2024.12.12");

                // 将single_items添加到linearLayout中
                linearLayout.addView(single_items);
                (tmp.get(position)).add(itemCard);

            // 最后将itemCard添加到holder.cardLayout中
            holder.cardLayout.addView(itemCard);
            for (int k = 0; k < tmp.get(position).size()-1; k = k + 1) {
                    (tmp.get(position)).get(k).findViewById(R.id.display).setVisibility(View.GONE);
            }
        }
    }

    // 这个方法需要根据您的业务逻辑来实现，返回每个位置应该有的itemCard数量
    private int getNumberOfCardsForPosition(int position) {
        // 示例逻辑：位置0有3个itemCard，位置1有2个，其他位置有1个
        if (position == 0) {
            return 10;
        } else if (position == 1) {
            return 2;
        } else if(position == 2) {
            return 1;
        }
        else { return 4;
        }
        // 在实际应用中，您可能需要根据数据源来确定这个数量
    }

        //getItemCount() 方法：
    //这个方法返回数据列表中的项数，告诉RecyclerView有多少项需要显示。
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    static public class ViewHolder extends RecyclerView.ViewHolder {
        TextView room_title;
        TextView room_discription;
        ImageView search_space_green;
        ImageView search_space_blue;
        ImageView search_arrow_green;
        ImageView search_arrow_blue;
        TextView next_room_title;
        CardLinearLayout cardLayout;
        ImageView set_into_star_btn;
        View v;
        public ViewHolder(View itemView) {
                super(itemView);
                search_space_green  = itemView.findViewById(R.id.search_space_green);
                search_space_blue  = itemView.findViewById(R.id.search_space_blue);
                room_title = itemView.findViewById(R.id.room_title);
                room_discription = itemView.findViewById(R.id.room_discription);
                search_arrow_blue = itemView.findViewById(R.id.search_arrow_blue);
                search_arrow_green = itemView.findViewById(R.id.search_arrow_green);
                next_room_title = itemView.findViewById(R.id.next_room_title);
                cardLayout = itemView.findViewById(R.id.cardLayout);
                set_into_star_btn = itemView.findViewById(R.id.set_into_star_btn);
                v = itemView.findViewById(R.id.cardLayout);
        }
    }
}