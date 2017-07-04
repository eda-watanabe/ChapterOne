package jp.inc.eda.chapterone_master.presentation.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;

import jp.inc.eda.chapterone_master.R;
import jp.inc.eda.chapterone_master.presentation.todolist.TodoListActivity;

public class LauncherActivity extends AppCompatActivity implements LauncherPresenter.Contract {

    private final static int SPLASH_TIME = 1500;

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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LauncherActivity.this, TodoListActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onGetVersionName(String val) {
        textVersionName.setText(val);
    }
}
