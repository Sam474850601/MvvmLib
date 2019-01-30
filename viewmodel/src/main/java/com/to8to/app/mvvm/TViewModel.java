package com.to8to.app.mvvm;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by same.li on 2018/6/7.
 */

public abstract class TViewModel {

    /**
     * 视图活跃度。如果视图停止或者结束后，为false,否则为true
     */
    private volatile boolean isActived = false;
    private volatile boolean isRecycled = false;

    /**
     * Activity
     */
    private volatile ActivityReference activityReference;


    /**
     * 获取Activity实例，如果Activity出栈后，那么返回为空。
     *
     * @return
     */
    public final Activity getActivity() {
        if (null == activityReference)
            return null;
        return activityReference.get();
    }


    /**
     * 设置活跃度
     *
     * @param actived
     */
    private void setActived(boolean actived) {
        isActived = actived;
    }

    /**
     * 如果视图回收了是true.否则是false
     *
     * @param recycled
     */
    private void setRecycled(boolean recycled) {
        isRecycled = recycled;
    }


    public TViewModel() {
    }


    /**
     * 如果你想视图接受到数据通知的回调，那么就调用这个方法和视图绑定。这个方法会启动TViewModel生命周期，和视图的生命周期同步。
     */
    public void attach(Activity activity) {
        if (null == activity)
            throw new RuntimeException("you have to pass by  instance  of activity   that is not null");
        if (activity.isFinishing()) {
            setActived(false);
            setRecycled(true);
            release();
            return;
        }
        activityReference = new ActivityReference(activity);
        if (activity instanceof AppCompatActivity) {
            AppCompatActivity tactivity = (AppCompatActivity) activity;
            final LifeSupportFragment lifeFragment = new LifeSupportFragment();
            lifeFragment.setModle(this);
            FragmentManager supportFragmentManager = tactivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            fragmentTransaction.add(lifeFragment, fragmentTransaction.toString());
            fragmentTransaction.commitAllowingStateLoss();
            return;
        }
        LifeFragment lifeFragment = new LifeFragment();
        lifeFragment.setModle(this);
        android.app.FragmentManager fragmentManager = activity.getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(lifeFragment, lifeFragment.toString());
        fragmentTransaction.commitAllowingStateLoss();
    }


    /**
     * 和activity生命周期onCreate同步
     */
    protected abstract void onCreate(Context k);


    /**
     * 和activity生命周期onStart同步
     */
    protected void onStart() {

    }


    /**
     * 和activity生命周期onResume同步
     */
    protected void onResume() {

    }


    /**
     * 和activity生命周期onStop同步
     */
    protected void onStop() {

    }


    /**
     * 和activity生命周期onPause同步
     */
    protected void onPause() {

    }


    /**
     * 和activity生命周期onDestroy同步
     */
    protected void onDestroy() {
        release();
    }


    /**
     * 置空所有livedata的引用,以及释放activity引用
     */
    public void release() {
        Class<? extends TViewModel> aClass = getClass();
        Field[] fields = aClass.getDeclaredFields();
        if (null == fields)
            return;
        for (Field field : fields) {

            if (TLiveData.class.isAssignableFrom(field.getType())) {
                try {
                    field.setAccessible(true);
                    field.set(this, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        if (null != activityReference) {
            activityReference.clear();
            activityReference = null;
        }

    }


    /**
     * 视图活跃度。如果视图停止或者结束后，为false,否则为true
     *
     * @return
     */
    public boolean isActived() {
        return isActived;
    }

    /**
     * 如果视图回收了是true.否则是false
     */
    public boolean isRecycled() {
        return isRecycled;
    }


    public final static class LifeSupportFragment extends Fragment {

        private TViewModel modle;

        public void setModle(TViewModel modle) {
            this.modle = modle;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (null != modle) {
                modle.setActived(true);
                modle.onCreate(getActivity().getApplicationContext());
            }

        }

        @Override
        public void onResume() {
            super.onResume();
            if (null != modle) {
                modle.setActived(true);
                modle.onResume();
            }

        }


        @Override
        public void onStart() {
            super.onStart();
            if (null != modle) {
                modle.setActived(true);
                modle.onStart();
            }

        }


        @Override
        public void onPause() {
            if (null != modle) {
                modle.setActived(false);
                modle.onPause();
            }
            super.onPause();

        }

        @Override
        public void onStop() {
            if (null != modle) {
                modle.setActived(false);
                modle.onStop();
            }

            super.onStop();
        }

        @Override
        public void onDestroy() {
            if (null != modle) {
                modle.setActived(false);
                modle.setRecycled(true);
                modle.onDestroy();
            }

            super.onDestroy();
        }


    }

    public final static class LifeFragment extends android.app.Fragment {
        private TViewModel modle;

        public void setModle(TViewModel modle) {
            this.modle = modle;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (null != modle) {
                modle.setActived(true);
                modle.onCreate(getActivity().getApplicationContext());
            }

        }

        @Override
        public void onResume() {
            super.onResume();
            if (null != modle) {
                modle.setActived(true);
                modle.onResume();
            }

        }


        @Override
        public void onStart() {
            super.onStart();
            if (null != modle) {
                modle.setActived(true);
                modle.onStart();
            }

        }


        @Override
        public void onPause() {
            if (null != modle) {
                modle.setActived(false);
                modle.onPause();
            }
            super.onPause();

        }

        @Override
        public void onStop() {
            if (null != modle) {
                modle.setActived(false);
                modle.onStop();
            }

            super.onStop();
        }

        @Override
        public void onDestroy() {
            if (null != modle) {
                modle.setActived(false);
                modle.setRecycled(true);
                modle.onDestroy();
            }
            super.onDestroy();
        }

    }


}
