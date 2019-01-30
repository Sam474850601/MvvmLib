package com.to8to.app.mvvm;

import java.lang.ref.WeakReference;

/**
 * Created by same.li on 2018/6/6.
 * viewmodel和视图交互的数据绑定类
 */

public abstract class TLiveData<T> {

    private    T t ;

    private WeakReference<TLifeView> activityWeakReference;

    public TLiveData(TLifeView view){
        activityWeakReference = new WeakReference<TLifeView>(view);
    }

    /**
     * 如果视图被回收了返回true
     */
    public  boolean isViewRecycled(){
        if(null == activityWeakReference)
            return true;
        TLifeView view = activityWeakReference.get();
        return null == view || view.isFinishing();
    }

    /**
     * 设置数据，通知视图刷新数据。
     * @param t
     */
    public  void setValue(T t){
        if(isViewRecycled())
            return;
        this.t = t;
        onChange(t);
    }

    /**
     * 如果视图没有被回收。调用setValue后那么这个方法将会被回调。
     * @param t
     */
    public   abstract void onChange(T t);


    public    T getValue(){
        return t;
    }

}
