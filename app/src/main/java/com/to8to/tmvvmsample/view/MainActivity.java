package com.to8to.tmvvmsample.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.to8to.app.mvvm.TLiveData;
import com.to8to.app.mvvm.TUIRequestTarget;
import com.to8to.app.mvvm.TUITarget;
import com.to8to.tmvvmsample.R;
import com.to8to.tmvvmsample.viewmodel.ContentViewModel;

public class MainActivity extends BaseActivity {


    TextView tvContent;
    ContentViewModel contentViewModel;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }


    private void initView() {
        tvContent = findViewById(R.id.tv_content);
        swipeRefreshLayout = findViewById(R.id.freshLayout);
    }



    private void initData() {
        contentViewModel = new ContentViewModel();
        contentViewModel.setContent(new TLiveData<String>(this) {
            @Override
            public void onChange(String text) {
                tvContent.setText(text);
            }
        });
        contentViewModel.setRefreshing(new TLiveData<Boolean>(this) {
            @Override
            public void onChange(Boolean isRefreshing) {
                swipeRefreshLayout.setRefreshing(isRefreshing);
            }
        });
        contentViewModel.setTargetForResult(new TLiveData<TUIRequestTarget>(this) {
            @Override
            public void onChange(TUIRequestTarget tuiRequestTarget) {
                tuiRequestTarget.forward(MainActivity.this);
            }
        });
        contentViewModel.setTarget(new TLiveData<TUITarget>(this) {
            @Override
            public void onChange(TUITarget tuiTarget) {
                tuiTarget.forward(MainActivity.this);
            }
        });
        contentViewModel.attach(this);
    }




    public void  onGetTextClick(View view){
        contentViewModel.loadContentTextFromServer();
    }




}
