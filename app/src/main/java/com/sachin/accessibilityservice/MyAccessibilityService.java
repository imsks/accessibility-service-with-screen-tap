package com.sachin.accessibilityservice;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class MyAccessibilityService extends AccessibilityService {
    private final AccessibilityServiceInfo info = new AccessibilityServiceInfo();
    private static final String TAG = "MyAccessibilityService";
    private static final String TAGEVENTS = "TAGEVENTS";
    private String currntApplicationPackage = "";

//    private WindowPositionController windowController;
    private WindowManager windowManager;
    private boolean showWindow = false;

    //Getting events
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Log.d(TAG, "onAccessibilityEvent");
        final String sourcePackageName = (String) accessibilityEvent.getPackageName();
        currntApplicationPackage = sourcePackageName;
        Log.d(TAG, "sourcePackageName:" + sourcePackageName);
        Log.d(TAG, "parcelable:" + accessibilityEvent.getText().toString());

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
//
//        // check for which events you want
        if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            Log.d(TAGEVENTS, "TYPE_VIEW_TEXT_CHANGED");
        }
    }

    //interrupt service
    @Override
    public void onInterrupt() {

    }

    // Once service connected
    @Override
    protected void onServiceConnected() {
        info.flags = AccessibilityServiceInfo.FLAG_REQUEST_TOUCH_EXPLORATION_MODE;
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        info.notificationTimeout = 100;

        this.setServiceInfo(info);

        super.onServiceConnected();
        Log.d(TAG, "service is connected");
    }

    // here you can intercept the keyevent
    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        return handleKeyEvent(event);
    }

    private boolean handleKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        if (action == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_VOLUME_DOWN:
                    //do something
                    return true;

                case KeyEvent.KEYCODE_VOLUME_UP: {
                    //do something
                    return true;
                }
            }
        }
        return false;
    }
}