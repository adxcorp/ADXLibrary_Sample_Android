package com.adxcorp.adxdev;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class CloseNativeAdActivity extends AppCompatActivity {

    private  CloseNativeAdDialog mCloseNativeAdDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCloseNativeAdDialog = new CloseNativeAdDialog(this);
        mCloseNativeAdDialog.setCancelable(true);
        mCloseNativeAdDialog.setPositiveButtonClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        mCloseNativeAdDialog.setNegativeButtonClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        mCloseNativeAdDialog.preloadAd();
    }

    @Override
    public void onBackPressed() {
        if (mCloseNativeAdDialog.isCanShow()) {
            mCloseNativeAdDialog.show();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (mCloseNativeAdDialog != null) {
            mCloseNativeAdDialog.destroy();
            mCloseNativeAdDialog = null;
        }
        super.onDestroy();
    }
}
