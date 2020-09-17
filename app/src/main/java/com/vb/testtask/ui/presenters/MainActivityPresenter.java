package com.vb.testtask.ui.presenters;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.navigation.NavController;

import com.vb.testtask.R;
import com.vb.testtask.data.network.TestClient;
import com.vb.testtask.data.network.models.ItemTest;
import com.vb.testtask.data.network.models.Test;
import com.vb.testtask.ui.views.MainActivityView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter {

    private MainActivityView mainActivityView;
    private int counter = 0;
    private List<Test> testList;

    public MainActivityPresenter(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
    }

    public void testCall(final ProgressBar progressBar, final Button button){
        TestClient.getInstance().getTestApi().getTest().enqueue(new Callback<List<Test>>() {
            @Override
            public void onResponse(Call<List<Test>> call, Response<List<Test>> response) {
                testList = response.body();
                hideProgressBar(progressBar);
                showButton(button);
            }

            @Override
            public void onFailure(Call<List<Test>> call, Throwable t) {
                Log.d(mainActivityView.provideActivityClassName(),
                        "Problem with test call  " + call.request()
                        + "  " + t.getMessage(), t);
            }
        });
    }

    public void onNextClickedCall(final NavController navController){
        if (counter > testList.size() - 1){
            counter = 0;
        }
        Test test = testList.get(counter);
        TestClient.getInstance().getTestApi().getItemTest(test.getId()).enqueue(new Callback<ItemTest>() {
            @Override
            public void onResponse(Call<ItemTest> call, Response<ItemTest> response) {
                counter++;
                if (response.body().getType().equals("text")){
                    mainActivityView.setMText(response.body().getContents());
                    navController.navigate(R.id.titleFragment);
                } else if (response.body().getType().equals("webview")){
                    mainActivityView.setUrl(response.body().getUrl());
                    navController.navigate(R.id.webViewFragment);
                }
            }

            @Override
            public void onFailure(Call<ItemTest> call, Throwable t) {
                Log.d(mainActivityView.provideActivityClassName(),
                        "Problem with item test call");
            }
        });
    }

    private void hideProgressBar(ProgressBar progressBar){
        progressBar.setVisibility(View.GONE);
    }

    private void showButton(Button button){
        button.setVisibility(View.VISIBLE);
    }
}
