package vn.eazy.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import vn.eazy.example.data.News;
import vn.eazy.example.presenter.main.MainPresenter;
import vn.eazy.example.presenter.main.MainView;

/**
 * Created by Harry on 2/8/17.
 */

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter mainPresenter;

    private TextView tvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContent = (TextView) findViewById(R.id.tvContent);

        mainPresenter = new MainPresenter(getApplicationContext());
        mainPresenter.bind(this);

        mainPresenter.getNews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.unbind();
    }

    @Override
    public void onGetAllNewsSuccess(List<News> news) {
        StringBuilder sb = new StringBuilder();
        for (News n : news) {
            sb.append(n.getTitle());
            sb.append("\n");
        }
        tvContent.setText(sb.toString());
    }

    @Override
    public void showError() {
        tvContent.setText("Error");
    }
}
