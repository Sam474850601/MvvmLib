package com.to8to.app.mvvm;

import android.app.Activity;

/**
 * Created by same.li on 2018/7/9.
 */

public class TUIRequestTarget  extends TUITarget{
    /**
     *  startActivityForResult 的请求码
     */
    public int reuqestCode = -1;


    /**
     *
     * @param reuqestCode
     */
    public void setReuqestCode(int reuqestCode) {
        this.reuqestCode = reuqestCode;
    }

    /**
     * 跳转页面
     * @param activity
     */
    @Override
    public void forward(Activity activity){
        intent.setClass(activity, targetActivity);
        activity.startActivityForResult(intent,reuqestCode);
    }


}
