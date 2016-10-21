package com.example.administrator.myapplication.icompus.utils;

import com.example.administrator.myapplication.icompus.R;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

public class ImgUtil {
    public static final String TAG = ImgUtil.class.getSimpleName();

    public static final int BASE_SIZE = 160;

    public static Bitmap getBitmap(String path) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 只计算几何尺寸，不返回bitmap，不占内存。
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int w = options.outWidth;
        int h = options.outHeight;
        Log.i(TAG, "--img src,w:" + w + " h:" + h);
        int min = w < h ? w : h;

        int rate = min / BASE_SIZE;
        if (rate <= 0) {
            rate = 1;
        }
        options.inSampleSize = rate;
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(path, options);
        Log.i(TAG, "--img dst,w:" + bitmap.getWidth() + " h:" + bitmap.getHeight());
        return bitmap;
    }

    public static String getAlbumImagePath(Context context, Uri uri) {
        String path = "";
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        path = cursor.getString(column_index);
        cursor.close();
        return path;
    }

    public static ImageLoaderConfiguration getImageConfig(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();//开始构建
        return config;
    }

    public static DisplayImageOptions getDisplayOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .showImageOnLoading(R.drawable.ic_book).build();
        return options;
    }

}
