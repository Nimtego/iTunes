package com.nimtego.itunes.view.toast;

import android.content.Context;
import android.widget.Toast;

public class SimpleToastAlarm implements ToastAlarm {

    private Context mContext;

    public SimpleToastAlarm(Context commonView) {
        mContext = commonView;
    }

    @Override
    public void destroy() {
        mContext = null;
    }

    @Override
    public void message(String message) {
        Toast toast = Toast.makeText(mContext.getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
