package com.neighbor.retailer_android;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.neighbor.retailer_android.common.utils.MLog;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.L;
/**
 * Created by vic on 2015/8/19.
 *
 * to load image for listview
 */
public class MyApplication extends Application {

    public static RequestQueue mQueue;
    public Context context = null;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        mQueue = Volley.newRequestQueue(context);
        MLog.setIsLog(true);
        initImageLoader(getApplicationContext());
    }




    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
//                .memoryCacheExtraOptions(500, 500) // default = device screen dimensions
                .threadPriority(Thread.NORM_PRIORITY - 2)
//                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .memoryCache(new LruMemoryCache(50*1024*1024))
                .memoryCacheSize(50*1024*1024)
                .discCacheFileCount(100)
                .discCacheSize(100 * 1024 * 1024)
                .discCacheFileCount(100)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
        L.disableLogging();
    }

    public static RequestQueue getQueue(){
        return mQueue;
    }
}
