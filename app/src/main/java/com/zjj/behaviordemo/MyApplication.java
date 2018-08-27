package com.zjj.behaviordemo;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.util.IInnerImageSetter;

/**
 * Created by zjj on 2018/7/31.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TangramBuilder.init(this, new IInnerImageSetter() {
            @Override
            public <IMAGE extends ImageView> void doLoadImageUrl(@NonNull IMAGE view,
                                                                 @Nullable String url) {
                //假设你使用 Picasso 加载图片
                Picasso.with(getApplicationContext()).load(url).into(view);
            }
        }, ImageView.class);

    }
}
