package com.example.chongeryuan.codingassignment.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {

    CircleImageView imageView;

    public ImageDownloadTask(CircleImageView imageView){
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urlImage = urls[0];
        Bitmap logo = null;
        try{
            InputStream iS = new URL(urlImage).openStream();

            logo = BitmapFactory.decodeStream(iS);
        } catch (Exception e){
            e.printStackTrace();
        }
        return logo;
    }

    protected void onPostExecute(Bitmap result){
        imageView.setImageBitmap(result);
    }
}
