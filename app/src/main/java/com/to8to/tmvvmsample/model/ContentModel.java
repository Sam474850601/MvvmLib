package com.to8to.tmvvmsample.model;

/**
 * Created by same.li on 2018/6/7.
 */

public class ContentModel {

    public  interface Callback{
        void result(String text);

    }

    public void loadText(final Callback callback){

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                callback.result("惊不惊喜？意不意外？傻吊");

            }
        }.start();
    }
}
