package com.vb.testtask.ui.views;

import com.vb.testtask.data.network.models.Test;

import java.util.List;

public interface MainActivityView {
    String provideActivityClassName();
    void setMText(String text);
    void setUrl(String url);
}
