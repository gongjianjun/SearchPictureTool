package com.example.administrator.searchpicturetool.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jude.beam.Beam;
import com.jude.utils.JUtils;

import cn.bmob.v3.Bmob;

/**
 * Created by wenhuaijun on 2015/11/2 0002.
 */
public class APP extends Application{
    public  static APP instance;
    public static  APP getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        Beam.init(this);
        Beam.setActivityLifeCycleDelegateProvider(ActivityDelegate::new);
        Fresco.initialize(this);
      /*  RequestManager.getInstance().init(this);
        RequestManager.getInstance().setDebugMode(true, "heheda");*/
        Bmob.initialize(this,"633edd745d4d8630d88c73a16440cb9a");
        JUtils.initialize(this);
        JUtils.setDebug(true, "heheda");
        //JFileManager.getInstance().init(this,Dir.values());
    }
}
