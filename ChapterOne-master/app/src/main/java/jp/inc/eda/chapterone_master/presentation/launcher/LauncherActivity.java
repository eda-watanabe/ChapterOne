package jp.inc.eda.chapterone_master.presentation.launcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import jp.inc.eda.chapterone_master.R;
import jp.inc.eda.chapterone_master.domain.usecase.GetVersionName;

public class LauncherActivity extends AppCompatActivity {

    private GetVersionName getVersionName;

    // ui
    private TextView textVersionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        getVersionName = new GetVersionName(this);

        textVersionName = (TextView) findViewById(R.id.versionName);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getVersionName.run()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        textVersionName.setText(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Toast.makeText(LauncherActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
