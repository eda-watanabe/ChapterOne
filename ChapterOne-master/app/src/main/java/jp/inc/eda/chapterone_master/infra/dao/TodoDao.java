package jp.inc.eda.chapterone_master.infra.dao;

import android.support.annotation.Nullable;

import com.github.gfx.android.orma.annotation.OnConflict;

import java.util.List;

import jp.inc.eda.chapterone_master.domain.model.OrmaDatabase;
import jp.inc.eda.chapterone_master.domain.model.Todo;

/**
 * Created by watanabe on 2017/07/04.
 * Todoデータの永続化処理を司る
 */
public class TodoDao {

    private OrmaDatabase orma;

    public TodoDao(OrmaDatabase orma) {
        this.orma = orma;
    }

    public void insert(final Todo todo) {
        orma.transactionSync(new Runnable() {
            @Override
            public void run() {
                orma.prepareInsertIntoTodo(OnConflict.REPLACE).execute(todo);
            }
        });
    }

    @Nullable
    public Todo read(long id) {
        return orma.selectFromTodo().idEq(id).getOrNull(0);
    }

    public List<Todo> findEqNonChecked() {
        return null;
    }

    public List<Todo> findAll() {
        return orma.selectFromTodo().toList();
    }

}
