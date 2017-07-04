package jp.inc.eda.chapterone_master.presentation.todolist;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import jp.inc.eda.chapterone_master.domain.model.Todo;
import jp.inc.eda.chapterone_master.infra.repository.TodoRepository;

/**
 * Created by watanabe on 2017/07/04.
 */
class TodoListPresenter {

    private final TodoRepository repository;
    private final Contract contract;

    TodoListPresenter(Contract contract, TodoRepository todoRepository) {
        this.contract = contract;
        this.repository = todoRepository;
    }

    void fetchTodos(boolean isCheck) {
        repository.getTodos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Todo>>() {
                    @Override
                    public void accept(@NonNull List<Todo> todos) throws Exception {
                        if (contract != null) {
                            contract.showTodos(todos);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (contract != null) {
                            contract.error();
                        }
                    }
                })
                .dispose();
    }



    interface Contract {
        void showTodos(List<Todo> todos);
        void error();
    }
}
