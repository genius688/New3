package image_submit;

import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.PermissionChecker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageSubmitUtil {

    private  Context context;
    private static final int REQUEST_CODE_CAPTURE_IMAGE = 100;
    private Uri imageUri;

    ImageSubmitUtil(Context context){
        this.context = context;
    }

    public Uri getPic(){
        if (checkSelfPermission(context, Manifest.permission.CAMERA) != PermissionChecker.PERMISSION_GRANTED){
            requestPermissions((Activity)context, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAPTURE_IMAGE);
            return null;
        }
        imageUri = createImageUri();
        openCamera();
        return imageUri;
    }
    public Uri createImageUri() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, imageFileName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);

        return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    public void openCamera() {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); // 设置输出文件的 URI
                startActivityForResult((Activity)context, intent, REQUEST_CODE_CAPTURE_IMAGE,null); // 启动相机应用
    }
    }


}


