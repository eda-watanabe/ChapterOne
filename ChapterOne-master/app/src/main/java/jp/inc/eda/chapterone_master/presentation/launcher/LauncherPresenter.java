package jp.inc.eda.chapterone_master.presentation.launcher;

import android.content.Context;
import android.widget.Toast;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import jp.inc.eda.chapterone_master.domain.usecase.GetVersionName;

/**
 * Created by watanabe on 2017/07/04.
 */
class LauncherPresenter {

    final private Context context;
    final private GetVersionName getVersionName;

    private Contract contract;

    LauncherPresenter(Context context, Contract contract) {
        this.context = context;
        getVersionName = new GetVersionName(context);
        this.contract = contract;
    }

    void resume() {
        getVersionName.run()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        if (contract == null) return;
                        contract.onGetVersionName(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).dispose();
    }

    interface Contract {
        void onGetVersionName(String val);
    }
}
