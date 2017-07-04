package jp.inc.eda.chapterone_master.presentation.launcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import jp.inc.eda.chapterone_master.R;

public class LauncherActivity extends AppCompatActivity implements LauncherPresenter.Contract{

    private LauncherPresenter presenter;

    // ui
    private TextView textVersionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        presenter = new LauncherPresenter(this, this);
        textVersionName = (TextView) findViewById(R.id.versionName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onGetVersionName(String val) {
        textVersionName.setText(val);
    }
}
