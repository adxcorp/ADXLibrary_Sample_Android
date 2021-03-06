package com.adxcorp.adxdev;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adxcorp.nativead.NativeAdFactory;
import com.mopub.nativeads.MoPubRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by god on 2017. 6. 27..
 */

public class NativeAdRecyclerViewActivity extends AppCompatActivity {
    private MoPubRecyclerAdapter mRecyclerAdapter;
    private RecyclerView mRecyclerView;

    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad_recyclerview);

        for (int i = 0; i < 150; i++) {
            mList.add(String.format(Locale.US, "Content Item #%d", i));
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.native_recycler_view);

        final RecyclerView.Adapter originalAdapter = new DemoRecyclerAdapter();

        mRecyclerAdapter = NativeAdFactory.getMoPubRecyclerAdapter(this, originalAdapter, DefineAdUnitId.NATIVE_AD_UNIT_ID);
        mRecyclerAdapter.setContentChangeStrategy(MoPubRecyclerAdapter.ContentChangeStrategy.MOVE_ALL_ADS_WITH_CONTENT);

        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerAdapter.loadAds(DefineAdUnitId.NATIVE_AD_UNIT_ID);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecyclerAdapter.destroy();
    }

    private class DemoRecyclerAdapter extends RecyclerView.Adapter<DemoViewHolder> {
        private static final int ITEM_COUNT = 150;

        @Override
        public DemoViewHolder onCreateViewHolder(final ViewGroup parent,
                                                 final int viewType) {
            final View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new DemoViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final DemoViewHolder holder, final int position) {
            holder.textView.setText(mList.get(position));
        }

        @Override
        public long getItemId(final int position) {
            return (long) position;
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    /**
     * A view holder for R.layout.simple_list_item_1
     */
    private static class DemoViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        public DemoViewHolder(final View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
