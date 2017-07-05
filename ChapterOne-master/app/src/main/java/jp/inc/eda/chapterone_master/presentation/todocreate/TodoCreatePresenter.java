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
    }

    private AppCompatActivity activity;
    private TodoRepository repository;
    private Contract contract;

    TodoCreatePresenter(AppCompatActivity activity, TodoRepository repository, Contract _contract) {
        this.activity = activity;

        // DB取得
        Provider module = new Provider();
        OrmaDatabase orma = module.provideOrma(activity);
        // TODO用のDAO生成
        TodoDao dao = new TodoDao(orma);
        this.repository = new TodoRepository(dao);

        this.contract = _contract;
    }

    void createTodo(String title, String description) {
        Todo todo = new Todo();
        todo.title = title;
        todo.description = description;
        todo.isChecked = false;
        repository.create(todo);
    }
}
