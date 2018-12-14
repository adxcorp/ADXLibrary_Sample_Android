package com.adxcorp.adxdev;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mopub.nativeads.NativeAd;
import com.mopub.nativeads.NativeAdFactory;

import java.lang.annotation.Native;

public class CloseNativeAdDialog extends Dialog {

    private Context mContext;
    private TextView mNegativeButton;
    private TextView mPositiveButton;
    private View.OnClickListener mPositiveButtonListener;
    private View.OnClickListener mNegativeButtonListener;
    private FrameLayout mContentLayout;

    private TextView mDscTextView;
    private LinearLayout mButtonlayout;

    private NativeAd mNativeAd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.close_native_ad_dialog_layout);

        mNegativeButton = findViewById(R.id.negativeButton);
        mNegativeButton.setOnClickListener(mNegativeButtonListener);

        mPositiveButton = findViewById(R.id.positiveButton);
        mPositiveButton.setOnClickListener(mPositiveButtonListener);

        resetVisibility();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mNativeAd != null) {
            mNativeAd.destroy();
            mNativeAd = null;
        }
        resetVisibility();
    }

    @Override
    public void show() {
        super.show();
        mContentLayout = findViewById(R.id.native_ad_conatiner);
        NativeAdFactory.addListener(mNativeAdListener);
        NativeAdFactory.loadAd(DefineAdUnitId.NATIVE_AD_UNIT_ID);
    }

    public void setPositiveButtonClickListener(final OnClickListener listener) {
        mPositiveButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(CloseNativeAdDialog.this, Dialog.BUTTON_POSITIVE);
                }
                dismiss();
            }
        };
    }

    public void setNegativeButtonClickListener(final OnClickListener listener) {
        mNegativeButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(CloseNativeAdDialog.this, Dialog.BUTTON_NEGATIVE);
                }
                dismiss();
            }
        };
    }

    public Boolean isCanShow() {
        return NativeAdFactory.getNativeAd(DefineAdUnitId.NATIVE_AD_UNIT_ID) != null;
    }

    public void preloadAd() {
        NativeAdFactory.preloadAd(DefineAdUnitId.NATIVE_AD_UNIT_ID);
    }

    private void resetVisibility() {
        mContentLayout = findViewById(R.id.native_ad_conatiner);
        mContentLayout.setVisibility(View.GONE);

        mDscTextView = findViewById(R.id.messageTextView);
        mDscTextView.setVisibility(View.INVISIBLE);

        mButtonlayout = findViewById(R.id.button_layout);
        mButtonlayout.setVisibility(View.INVISIBLE);

        mContentLayout.removeAllViews();
    }

    private void loadButtonLayout() {
        // Native Ad의 경우 최소 노출 시간을 확보하기 위해 버튼 노출 딜레이를 줍니다.
        mButtonlayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mButtonlayout.setVisibility(View.VISIBLE);
                mButtonlayout.setAlpha(0f);
                mButtonlayout.animate().alpha(1f).setDuration(500).setListener(null);
            }
        }, 850);
    }

    private NativeAdFactory.NativeAdListener mNativeAdListener = new NativeAdFactory.NativeAdListener() {
        @Override
        public void onSuccess(String adUnitId, NativeAd nativeAd) {
            try {
                mNativeAd = NativeAdFactory.getNativeAd(DefineAdUnitId.NATIVE_AD_UNIT_ID);
                View view = NativeAdFactory.getNativeAdView(mContext, DefineAdUnitId.NATIVE_AD_UNIT_ID, null, null);
                if (mContentLayout.getChildCount() == 0) {
                    mContentLayout.addView(view);
                }
                mContentLayout.setVisibility(View.VISIBLE);
                mDscTextView.setVisibility(View.VISIBLE);
                loadButtonLayout();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(String s) {
            mDscTextView.setVisibility(View.VISIBLE);
            mButtonlayout.setVisibility(View.VISIBLE);
        }
    };

    public CloseNativeAdDialog(Activity context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        mContext = context;
    }

    public void destroy() {
        NativeAdFactory.removeListener(mNativeAdListener);
        if (mNativeAd != null) {
            mNativeAd.destroy();
            mNativeAd = null;
        }
    }
}
