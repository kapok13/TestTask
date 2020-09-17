package com.vb.testtask.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.vb.testtask.R;
import com.vb.testtask.ui.presenters.MainActivityPresenter;
import com.vb.testtask.ui.views.MainActivityView;


public class MainActivity extends AppCompatActivity implements MainActivityView {

    private MainActivityPresenter mainActivityPresenter;
    private NavController controller;
    private String mText;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = Navigation.findNavController(this, R.id.host_fragment);
        Button nextButton = findViewById(R.id.next_btn);
        ProgressBar loadingPrgrs = findViewById(R.id.loading_prgrs);
        mainActivityPresenter = new MainActivityPresenter(this);
        mainActivityPresenter.testCall(loadingPrgrs, nextButton);
    }



    public void nextBtnClick(View view) {
       mainActivityPresenter.onNextClickedCall(controller);
    }

    public String getmText() {
        return mText;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String provideActivityClassName() {
        return getLocalClassName();
    }

    @Override
    public void setMText(String text) {
        this.mText = text;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

}
