package com.to8to.app.mvvm;

import android.app.Activity;

/**
 * Activity实例引用类
 */

public final class ActivityReference extends TemporaryReference<Activity> {


    public ActivityReference(Activity reference) {
        super(reference);
    }

    @Override
    protected boolean intercept(Activity reference) {
        return reference.isFinishing();
    }
}