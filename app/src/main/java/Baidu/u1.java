package Baidu;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class u1 {
    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    public static void getAccessToken(String api_key, String secret_key, final OnAccessTokenReceivedListener listener) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");

        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token?client_id=" + api_key + "&client_secret=" + secret_key + "&grant_type=client_credentials")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        HTTP_CLIENT.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (listener != null) {
                    listener.onFailure(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody);
                        String accessToken = jsonObject.getString("access_token");
                        if (listener != null) {
                            listener.onSuccess(accessToken);
                        }
                    } catch (JSONException e) {
                        if (listener != null) {
                            listener.onFailure(e);
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.onFailure(new IOException("Unexpected code " + response));
                    }
                }
            }
        });
    }

    public interface OnAccessTokenReceivedListener {
        void onSuccess(String accessToken);
        void onFailure(Exception e);
    }
}
