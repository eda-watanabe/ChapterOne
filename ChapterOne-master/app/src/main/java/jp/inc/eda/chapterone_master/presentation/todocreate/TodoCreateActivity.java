package jp.inc.eda.chapterone_master.presentation.todocreate;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import jp.inc.eda.chapterone_master.R;
import jp.inc.eda.chapterone_master.di.Module;
import jp.inc.eda.chapterone_master.domain.model.OrmaDatabase;
import jp.inc.eda.chapterone_master.infra.dao.TodoDao;
import jp.inc.eda.chapterone_master.infra.repository.TodoRepository;

public class TodoCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Module module = new Module();
        OrmaDatabase orma = module.provideOrma(this);
        TodoDao dao = new TodoDao(orma);
        TodoRepository repository = new TodoRepository(dao);
    }

}
