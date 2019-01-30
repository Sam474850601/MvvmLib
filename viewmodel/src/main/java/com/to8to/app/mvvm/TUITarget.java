package com.to8to.app.mvvm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;


/**
 * Created by same.li on 2018/7/3.
 *
 */

public class TUITarget{
    /**
     * expect to forward target  activity
     */
    public  Class<? extends Activity> targetActivity;

    public void setTargetActivity(Class<? extends Activity> targetActivity) {
        this.targetActivity = targetActivity;
    }


     public final Bundle param = new Bundle();

    public final Intent intent = new Intent();

    public Intent getIntent() {
        return intent;
    }

    public TUITarget putExtras(String key, int value){
         intent.putExtra(key, value);
         return this;

     }

    public TUITarget putExtras(String key, int[] value){
        intent.putExtra(key, value);
        return this;

    }

    public TUITarget putExtras(String key, boolean value){
        intent.putExtra(key, value);
        return this;

    }



    public TUITarget putExtras(String key, long value){
        intent.putExtra(key, value);
        return this;

    }
    public TUITarget putExtras(String key, long[] value){
        intent.putExtra(key, value);
        return this;

    }


    public TUITarget putExtras(String key, String value){
        intent.putExtra(key, value);
        return this;
    }
    public TUITarget putExtras(String key, String[] value){
        intent.putExtra(key, value);
        return this;
    }

    public TUITarget putExtras(String key, char value){
        intent.putExtra(key, value);
        return this;
    }

    public TUITarget putExtras(String key, char[] value){
        intent.putExtra(key, value);
        return this;
    }



    public TUITarget putExtras(String key, Serializable value){
        intent.putExtra(key, value);
        return this;
    }



    /**
     * 跳转页面
     */
    public void forward(Activity  activity){
         intent.setClass(activity, targetActivity);
         activity.startActivity(intent);
     }



}
