package com.vb.testtask.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vb.testtask.R;
import com.vb.testtask.ui.activities.MainActivity;
import com.vb.testtask.ui.adapters.TestWebViewClient;

public class WebViewFragment extends Fragment {

    private WebView webView;
    private MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        webView = view.findViewById(R.id.fragment_webview);
        webView.setWebViewClient(new TestWebViewClient());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        try {
            webView.loadUrl(activity.getUrl());
        } catch (NullPointerException e){
            Toast.makeText(getActivity().getBaseContext(), getResources().getString(R.string.view_problem)
                    , Toast.LENGTH_SHORT)
                    .show();
        }
        super.onResume();
    }
}
