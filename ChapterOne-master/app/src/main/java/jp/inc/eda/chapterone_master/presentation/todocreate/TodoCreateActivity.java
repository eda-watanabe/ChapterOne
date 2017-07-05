package jp.inc.eda.chapterone_master.presentation.todocreate;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import jp.inc.eda.chapterone_master.R;
import jp.inc.eda.chapterone_master.domain.model.OrmaDatabase;
import jp.inc.eda.chapterone_master.domain.model.Todo;
import jp.inc.eda.chapterone_master.helper.Provider;
import jp.inc.eda.chapterone_master.infra.dao.TodoDao;
import jp.inc.eda.chapterone_master.infra.repository.TodoRepository;

public class TodoCreateActivity extends AppCompatActivity implements TodoCreatePresenter.Contract {

    private TodoCreatePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODOリポジトリ作成
        Provider module = new Provider();
        OrmaDatabase orma = module.provideOrma(this);
        // TODO用のDAO生成
        TodoDao dao = new TodoDao(orma);
        TodoRepository repo = new TodoRepository(dao);

        // Presenter作成
        presenter = new TodoCreatePresenter(this, repo, this);

        // TODO作成ボタンコールバック設定
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getViewText(R.id.title);
                if(title.isEmpty()) {
                    // タイトルが入力されていない
                    Snackbar.make(v, "Please enter Todo title.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                    return;
                }
                String description = getViewText(R.id.description);
                if(description.isEmpty()) {
                    // 内容が入力されていない
                    Snackbar.make(v, "Please enter Todo description.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                    return;
                }
                presenter.createTodo(title, description);
                finish();
            }
        });
    }

    private String getViewText(@IdRes int id) {
        return ((TextView)findViewById(id)).getText().toString();
    }
}
