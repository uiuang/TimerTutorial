package com.zsc7454.seekbarpreference;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

/**
 * Created by Pavel Sikun on 22.05.16.
 */

public class SeekBarPreferenceCompat extends Preference implements View.OnClickListener, PreferenceControllerDelegate.ViewStateListener, PersistValueListener, ChangeValueListener {

    private PreferenceControllerDelegate controllerDelegate;



    public SeekBarPreferenceCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public SeekBarPreferenceCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SeekBarPreferenceCompat(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        setLayoutResource(R.layout.seekbar_view_layout);
        controllerDelegate = new PreferenceControllerDelegate(getContext(), false);

        controllerDelegate.setViewStateListener(this);
        controllerDelegate.setPersistValueListener(this);
        controllerDelegate.setChangeValueListener(this);

        controllerDelegate.loadValuesFromXml(attrs);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder viewRoot) {
        super.onBindViewHolder(viewRoot);
        controllerDelegate.onBind(viewRoot.itemView);
    }

    @Override
    public void setDefaultValue(Object defaultValue) {
        super.setDefaultValue(defaultValue);
    }

    @Override
    protected void onSetInitialValue(@Nullable Object defaultValue) {
        super.onSetInitialValue(defaultValue);
        controllerDelegate.setCurrentValue(getPersistedInt(controllerDelegate.getCurrentValue()));

    }



    @Override
    public boolean persistInt(int value) {
        return super.persistInt(value);
    }

    @Override
    public boolean onChange(int value) {
        return callChangeListener(value);
    }

    @Override
    public void onClick(final View v) {
        controllerDelegate.onClick(v);
    }

    public int getMaxValue() {
        return controllerDelegate.getMaxValue();
    }

    public void setMaxValue(int maxValue) {
        controllerDelegate.setMaxValue(maxValue);
    }

    public int getMinValue() {
        return controllerDelegate.getMinValue();
    }

    public void setMinValue(int minValue) {
        controllerDelegate.setMinValue(minValue);
    }

    public int getInterval() {
        return controllerDelegate.getInterval();
    }

    public void setInterval(int interval) {
        controllerDelegate.setInterval(interval);
    }

    public int getCurrentValue() {
        return controllerDelegate.getCurrentValue();
    }

    public void setCurrentValue(int currentValue) {
        controllerDelegate.setCurrentValue(currentValue);
        persistInt(controllerDelegate.getCurrentValue());
    }

    public String getMeasurementUnit() {
        return controllerDelegate.getMeasurementUnit();
    }

    public void setMeasurementUnit(String measurementUnit) {
        controllerDelegate.setMeasurementUnit(measurementUnit);
    }

    public boolean isDialogEnabled() {
        return controllerDelegate.isDialogEnabled();
    }

    public void setDialogEnabled(boolean dialogEnabled) {
        controllerDelegate.setDialogEnabled(dialogEnabled);
    }

    public void setDialogStyle(int dialogStyle) {
        controllerDelegate.setDialogStyle(dialogStyle);
    }
}
