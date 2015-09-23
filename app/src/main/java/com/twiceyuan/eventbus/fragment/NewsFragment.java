package com.twiceyuan.eventbus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.twiceyuan.eventbus.Counter;
import com.twiceyuan.eventbus.R;
import com.twiceyuan.eventbus.EventBus;
import com.twiceyuan.eventbus.event.NewsReadEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsFragment extends Fragment {

    @Bind(R.id.tv_display)
    TextView tv_display;
    @Bind(R.id.btn_count)
    Button btn_count;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_core, container, false);
        ButterKnife.bind(this, view);
        tv_display.setText("新闻");
        updateUnread();
        return view;
    }

    private void updateUnread() {
        btn_count.setText("未读数：" + Counter.getCounter().newsUnread);
    }

    @Subscribe
    public void onEvent(NewsReadEvent event) {
        updateUnread();
    }

    @OnClick(R.id.btn_count)
    public void count() {
        EventBus.getBus().post(new NewsReadEvent());
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getBus().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
