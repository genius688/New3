package image_submit;

import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.core.content.PermissionChecker;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartstore.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {
    private final Context context;
    private OnItemDeletedListener onItemDeletedListener;
    private ActivityResultLauncher<Intent> imagePickerLauncher;
    public static final int STORAGE_PERMISSION = 1;
    private Uri selectedImageUri;
    private int pic_chg_idx;
    private static final int PICK_IMAGE_REQUEST_CODE = 100; // 用于从图片选择器选择图片
    public HorizontalAdapter(Context context, ActivityResultLauncher<Intent> imagePickerLauncher) {
        System.out.println("??????" + Image_edit.items.get(0).item_title);
        this.imagePickerLauncher = imagePickerLauncher;
        this.context = context;
    }

    public void setSelectedImageUri(Uri selectedImageUri) throws IOException {
        this.selectedImageUri = selectedImageUri;
        ContentResolver contentResolver = context.getContentResolver();
        Image_edit.items.get(pic_chg_idx).item_file = getBytesFromUri(contentResolver, selectedImageUri);
        notifyDataSetChanged(); // 通知适配器数据已更新
    }

    public byte[] getBytesFromUri(ContentResolver contentResolver, Uri uri) throws IOException {
        InputStream inputStream = contentResolver.openInputStream(uri);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

        public interface OnItemDeletedListener {
        void onItemDeleted(int position);
    }

    public void setOnItemDeletedListener(OnItemDeletedListener listener) {
        this.onItemDeletedListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_card, parent, false);
        view.findViewById(R.id.part_30).setOnClickListener(v -> {
            Activity activity = (Activity) context;
            View currentFocus = activity.getCurrentFocus();
            if(currentFocus != null)
                currentFocus.clearFocus();
            v.requestFocus();
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });

        return new ViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // *** 设置初始值 ***
        holder.Title.setText(Image_edit.items.get(position).item_title);
        holder.rcmd_layout_content.setText(Image_edit.items.get(position).item_layout);
        holder.rcmd_rson_content.setText(Image_edit.items.get(position).item_rson);
        holder.dateEditContent.setText(Image_edit.items.get(position).item_date);
        holder.editDescriptionContent.setText(Image_edit.items.get(position).item_description);
        holder.dateEditContent.setFocusable(false);
        Bitmap bitmap = BitmapFactory.decodeByteArray(Image_edit.items.get(position).item_file, 0, Image_edit.items.get(position).item_file.length);
        holder.pic.setImageBitmap(bitmap);
//        holder.pic.setImageURI(Image_edit.items.get(position).item_file);


        holder.pic.setOnClickListener(v -> {
            if (checkSelfPermission(context, Manifest.permission.READ_MEDIA_IMAGES) != PermissionChecker.PERMISSION_GRANTED){
                requestPermissions((Activity)context, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, STORAGE_PERMISSION);
                return;
            }
            pic_chg_idx = position;
            Image_edit.UPLOAD_IMAGE_REQUEST = false;
            imagePickerLauncher.launch(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).setType("image/*"));
        });


        if(!Image_edit.items.get(position).item_star){
            holder.staryes.setVisibility(View.GONE);
            holder.starno.setVisibility(View.VISIBLE);
        }else{
            holder.staryes.setVisibility(View.VISIBLE);
            holder.starno.setVisibility(View.GONE);
        }

        // *** 星标监测与保存 ***
        holder.staryes.setOnLongClickListener(v -> {
            holder.staryes.setVisibility(View.GONE);
            holder.starno.setVisibility(View.VISIBLE);
            Toast.makeText(context, "取消星标成功~", Toast.LENGTH_SHORT).show();
            Image_edit.items.get(position).item_star = false;
            return true;
        });

        // *** 星标监测与保存 ***
        holder.starno.setOnClickListener(v -> {
            holder.starno.setVisibility(View.GONE);
            holder.staryes.setVisibility(View.VISIBLE);
            Toast.makeText(context, "设置星标成功~", Toast.LENGTH_SHORT).show();
            Image_edit.items.get(position).item_star = true;
        });

        // *** 期限监测与保存 ***
        holder.dateEditContent.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year1, month1, dayOfMonth) -> {
                month1 = month1+1;
                String month_ = String.format("%02d", month1);
                String day_ = String.format("%02d", dayOfMonth);
                holder.dateEditContent.setText(year1 + "-" + month_ + "-" + day_);
                Image_edit.items.get(position).item_date = year1 + "-" + (month_) + "-" + day_;
            }, year, month, day);
            datePickerDialog.show();
    });

        // *** 删除监测与保存 ***
        holder.delete.setOnClickListener(v -> {
            final int p = holder.getAdapterPosition(); // 获取正确的位置
            if (p != RecyclerView.NO_POSITION) {
                attention_dialog dd = new attention_dialog("你确定要删除” " +holder.Title.getText()+" “吗？删除后不可恢复哦！","物品移除提醒" ,"确定删除", "不，我点错了",context, isAccept -> {
                if(isAccept){
                    if (p >= 0 && p < Image_edit.items.size()) {
                        System.out.println("delete"+p);
                        holder.bgr.performClick();
                        Image_edit.items.remove(p);
                        notifyItemRemoved(p); // 通知Adapter数据已更改
                        notifyItemChanged(p); //更新后续items
                        onItemDeletedListener.onItemDeleted(p);
                        Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
                dd.onCreate_Attention_Dialog();
            }
        });

        // *** 物品描述监测与保存 ***
        holder.editDescriptionContent.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                Image_edit.items.get(position).item_description = String.valueOf(holder.editDescriptionContent.getText());
            }
        });
    }


    @Override
    public int getItemCount() {
        return Image_edit.items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final EditText dateEditContent;
        private final TextView rcmd_layout_content;
        private final TextView rcmd_rson_content;
        private final EditText editDescriptionContent;
        private final ImageView pic;
        private final TextView Title;
        private final ImageView staryes;
        private final ImageView starno;
        private final Button delete;
        private final ImageView bgr;
        public ViewHolder(View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.star_object_1);
            Title = itemView.findViewById(R.id.scan_res);
            rcmd_layout_content = itemView.findViewById(R.id.rcmd_layout_content);
            rcmd_rson_content = itemView.findViewById(R.id.rcmd_rson_content);
            editDescriptionContent = itemView.findViewById(R.id.edit_description_Content);
            dateEditContent = itemView.findViewById(R.id.dateEdit_Content);
            staryes = itemView.findViewById(R.id.edit_star_yes);
            starno = itemView.findViewById(R.id.edit_star_no);
            delete = itemView.findViewById(R.id.delete);
            bgr = itemView.findViewById(R.id.part_30);
        }
    }
}