package com.lg.dome.mvc;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lg.dome.R;

/**
 * Created by lqj on 2020/7/20.
 */
public class MvcActivity  extends AppCompatActivity implements Callback {
    private ImageView imageView;
    private static final String path = "http://photocdn.sohu.com/20130416/Img372885486.jpg";
    private ProgressBar progressBar;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case ImageDownloader.SUCCESS://请求成功
                    hideProgress();
                    imageView.setImageBitmap((Bitmap) msg.obj);
                    break;
                case ImageDownloader.ERROR://请求失败
                    hideProgress();
                    Toast.makeText(MvcActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        imageView = findViewById(R.id.iv_picture);
        progressBar=findViewById(R.id.progress);
    }
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }


    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    /**
     * 点击事件，获取图片
     *
     * @param view
     */
    public void getImage(View view) {
        showProgress();
        ImageBean imageBean=new ImageBean();
        imageBean.setRequestPath(path);
        ImageDownloader.down(this,imageBean);
    }

    @Override
    public void callback(int resultCode, ImageBean bitmap) {
        Message message = handler.obtainMessage(resultCode);
        message.obj = bitmap.getBitmap();
        handler.sendMessageDelayed(message, 500);
    }
}
