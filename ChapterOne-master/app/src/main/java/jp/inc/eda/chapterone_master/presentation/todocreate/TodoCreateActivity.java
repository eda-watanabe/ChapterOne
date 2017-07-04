package jp.inc.eda.chapterone_master.presentation.todocreate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import jp.inc.eda.chapterone_master.R;
import jp.inc.eda.chapterone_master.domain.model.Todo;

public class TodoCreateActivity extends AppCompatActivity implements TodoCreatePresenter.Contract {

    private TodoCreatePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new TodoCreatePresenter(this, this);
    }

    @Override
    public void onTodoCreated(Todo todo) {
        finish();
    }
}
