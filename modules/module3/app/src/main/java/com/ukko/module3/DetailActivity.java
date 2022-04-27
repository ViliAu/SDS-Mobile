package com.ukko.module3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

import com.ukko.module3.databinding.ActivityDetailBinding;
import com.ukko.module3.databinding.ActivityMainBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int index = getIntent().getIntExtra("index", 0);
        int pic = getImg(index);
        scaleImage(binding.ivMain, pic);

    }

    // Peach is the failsafe img
    private int getImg(int index) {
        switch (index) {
            case 1:
                return R.drawable.tomato;
            case 2:
                return R.drawable.squash;
            default:
                return R.drawable.peach;
        }
    }

    private void scaleImage(ImageView img, int pic) {
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);

        int imageWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if (imageWidth > screenWidth) {
            int ratio = Math.round((float)imageWidth / screenWidth);
            options.inSampleSize = ratio;
        }

        options.inJustDecodeBounds = false;
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaledImg);
    }
}