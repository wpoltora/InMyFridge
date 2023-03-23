package com.example.inmyfridge.data.converters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class BitmapConverter {
    @TypeConverter
    public static Bitmap fromByteArray(byte[] value) {
        if (value == null) {
            return null;
        }
        return BitmapFactory.decodeByteArray(value, 0, value.length);
    }

    @TypeConverter
    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
