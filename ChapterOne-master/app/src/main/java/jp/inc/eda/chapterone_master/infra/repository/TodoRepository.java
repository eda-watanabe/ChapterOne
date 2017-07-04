package jp.inc.eda.chapterone_master.infra.repository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import jp.inc.eda.chapterone_master.domain.model.Todo;
import jp.inc.eda.chapterone_master.infra.dao.TodoDao;

/**
 * Created by watanabe on 2017/07/04.
 * TODOのリポジトリ
 */
public class TodoRepository {

    private TodoDao dao;

    public TodoRepository(TodoDao dao) {
        this.dao = dao;
    }

    public Observable<List<Todo>> getTodos() {
        return Observable.create(new ObservableOnSubscribe<List<Todo>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Todo>> e) throws Exception {
                e.onNext(dao.findAll());
                e.onComplete();
            }
        });
    }

    public void create(Todo todo) {
        todo.createAt = System.currentTimeMillis();
        dao.insert(todo);
    }

    public void update(Todo todo) {
        todo.updateAt = System.currentTimeMillis();
        dao.insert(todo);
    }
}
