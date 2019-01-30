package com.to8to.app.mvvm;


/**
 * Created by same.li on 2018/7/3.
 * 这个类是TViewModel子类，扩展些公共的TLiveData来用
 */

public  abstract  class TSimpleViewModel extends TViewModel{

    /**
     * 通常用来发送命令通知视图刷新。例如类似handler.sendMessage what的用法
     */
    protected TLiveData<Integer> command;


    /**
     * 通知刷新信息刷新ui
     */
    protected TLiveData<String> message;



    /**
     * 通知任何数据类型刷新ui
     */
    protected TLiveData<Object> any;



    /**
     * 通知跳转页面。startActivity使用
     */
    protected TLiveData<TUITarget> target;


    /**
     * 通知跳转页面.startActivity使用
     */
    protected TLiveData<TUIRequestTarget> targetForResult;

    public void setTargetForResult(TLiveData<TUIRequestTarget> targetForResult) {
        this.targetForResult = targetForResult;
    }

    /**
     * 通知UI刷新当前情况
     */
    protected TLiveData<Boolean> isRefreshing;

    public TLiveData<Integer> getCommand() {
        return command;
    }

    public void setCommand(TLiveData<Integer> command) {
        this.command = command;
    }

    public TLiveData<String> getMessage() {
        return message;
    }

    public void setMessage(TLiveData<String> message) {
        this.message = message;
    }

    public TLiveData<Object> getAny() {
        return any;
    }

    public void setAny(TLiveData<Object> any) {
        this.any = any;
    }



    public <T> void setTarget(TLiveData<TUITarget> target) {
        this.target = target;
    }

    public TLiveData<Boolean> getIsRefreshing() {
        return isRefreshing;
    }

    public void setIsRefreshing(TLiveData<Boolean> isRefreshing) {
        this.isRefreshing = isRefreshing;
    }
}
