package com.example.android.themoviecompanion.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class DbBitmapUtility {

    // convert from bitmap to byte array if not null
    public static byte[] getBytes(Bitmap bitmap) {
        if (bitmap == null){return null;}
        else{
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            return stream.toByteArray();
        }

    }

    // convert from byte array to bitmap if not null
    public static Bitmap getImage(byte[] image) {
        if (image == null){return null;}
        else{return BitmapFactory.decodeByteArray(image, 0, image.length);}
    }
}