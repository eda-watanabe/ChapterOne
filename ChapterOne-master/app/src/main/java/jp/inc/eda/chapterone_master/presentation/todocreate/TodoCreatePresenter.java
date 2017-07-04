package jp.inc.eda.chapterone_master.presentation.todocreate;

import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import jp.inc.eda.chapterone_master.R;
import jp.inc.eda.chapterone_master.domain.model.OrmaDatabase;
import jp.inc.eda.chapterone_master.domain.model.Todo;
import jp.inc.eda.chapterone_master.helper.Provider;
import jp.inc.eda.chapterone_master.infra.dao.TodoDao;
import jp.inc.eda.chapterone_master.infra.repository.TodoRepository;

/**
 * Created by kobayashiryou on 2017/07/04.
 *
 * TODO作成画面Presenter
 */

class TodoCreatePresenter {

    interface Contract {
        /**
         * TODOの作成が完了した
         * @param todo 作成したTODO
         */
        void onTodoCreated(Todo todo);
    }

    private AppCompatActivity activity;
    private TodoRepository repository;
    private Contract contract;

    TodoCreatePresenter(AppCompatActivity activity, Contract _contract) {
        this.activity = activity;

        // DB取得
        Provider module = new Provider();
        OrmaDatabase orma = module.provideOrma(activity);
        // TODO用のDAO生成
        TodoDao dao = new TodoDao(orma);
        repository = new TodoRepository(dao);

        this.contract = _contract;

        FloatingActionButton fab = (FloatingActionButton) activity.findViewById(R.id.fab);
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
                // TODOを保存
                Todo todo = new Todo();
                todo.title = title;
                todo.description = description;
                todo.isChecked = false;
                repository.create(todo);
                contract.onTodoCreated(todo);
            }
        });
    }

    private String getViewText(@IdRes int id) {
        return ((TextView)activity.findViewById(id)).getText().toString();
    }
}
