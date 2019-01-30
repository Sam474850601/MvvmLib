package com.to8to.tmvvmsample.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.to8to.app.mvvm.TLiveData;
import com.to8to.app.mvvm.TSimpleViewModel;
import com.to8to.app.mvvm.TViewModel;
import com.to8to.tmvvmsample.model.ContentModel;


/**
 * Created by same.li on 2018/6/7.
 */

public class ContentViewModel extends TSimpleViewModel {

    private TLiveData<String> content;

    public void setContent(TLiveData<String> content) {
        this.content = content;
    }


    public void setRefreshing(TLiveData<Boolean> isRefreshing) {
        this.isRefreshing = isRefreshing;
    }
    private Handler handler;



    @Override
    public void onCreate(Context appContext) {
        loadSimpleText();
        handler = new Handler(appContext.getMainLooper());
    }

    final ContentModel contentService = new ContentModel();


    public void loadSimpleText() {
        if(null != content){
            content.setValue("你好啊，傻逼");
        }
    }

    //模拟加载服务器的信息
    public void loadContentTextFromServer() {
        isRefreshing.setValue(true);
        contentService.loadText(new ContentModel.Callback() {
            @Override
            public void result(final String text) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isRecycled())
                            return;
                        //为什么要这样写，用临时变量承接？避免异步执行时候ContentViewModel.this.content被回收了，空指针异常。
                        // 回收意义在于有可能开发人员，在视图上设置TLiveData时候使用匿名内部类持有了视图的引用导致释放失败。
                        final TLiveData<String> content = ContentViewModel.this.content;
                        final TLiveData<Boolean> isRefreshing = ContentViewModel.this.isRefreshing;
                        if(null == isRefreshing || null == content)
                            return;

                        content.setValue(text);
                        isRefreshing.setValue(false);

                    }
                });

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("ContentViewModel", "onStart");
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.e("ContentViewModel", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("ContentViewModel", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("ContentViewModel", "onStop");
    }

    @Override
    public void onDestroy() {
        Log.e("ContentViewModel", "onDestroy");
        handler.removeCallbacks(null);
        super.onDestroy();
        //释放所有livedata是为了释放livedata中的潜在持有的引用。如果livedata是个匿名内部类。那么就会持有activiy引用.
        //执行 super.onDestroy()后isRefreshing， content 会被回收。再调用就报错。如content.setValue(). content是个null
    }

}
