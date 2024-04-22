package com.example.myapplication;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

public class ToastUtil {
    public static void showToast(Context context, String message, int duration) {
        Toast toast = Toast.makeText(context, message, duration);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            toast.addCallback(new Toast.Callback() {
//                @Override
//                public void onToastShown() {
//                    super.onToastShown();
//                    if (onShow != null) onShow.run();
//                }
//
//                @Override
//                public void onToastHidden() {
//                    super.onToastHidden();
//                    if (onDismiss != null) onDismiss.run();
//                }
//            });
//        }
        toast.show();
    }
}

