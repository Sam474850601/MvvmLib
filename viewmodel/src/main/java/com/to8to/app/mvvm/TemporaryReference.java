package com.to8to.app.mvvm;


/**
 * Created by same.li on 2018/8/22.
 * 这是个临时引用,  通常用来操作避免引用被持有时间过长来使用.
 */

public class TemporaryReference<T> {

    private  T mReference;

    public TemporaryReference(T reference) {

        this.mReference = reference;
    }

    //如果想要满足条件情况下，不让获取到实例并置空，那么实现这个方法
    protected boolean  intercept(T reference){
        return false;
    }


    /**
     * 获取引用
     * @return
     */
    public  T get() {
        synchronized (this) {
            if(null != mReference){
                if (intercept(mReference)) {
                    _clear();
                }
            }
            return mReference;
        }
    }

    /**
     * 清理引用使用。
     */
    public  void clear() {
        synchronized (this) {
            if(null != mReference){
                _clear();
            }
        }
    }

    private void _clear(){
        mReference = null;
        System.runFinalization();
        System.gc();
    }
}
