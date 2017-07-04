package jp.inc.eda.chapterone_master.domain.usecase;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by watanabe on 2017/07/04.
 */

public class GetVersionName {

    private Context context;

    public GetVersionName(Context context) {
        this.context = context;
    }

    public Observable<String> run() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                PackageManager pm = context.getPackageManager();
                String versionName = "";
                try{
                    PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
                    versionName = packageInfo.versionName;
                    e.onNext(versionName);
                    e.onComplete();
                }catch(PackageManager.NameNotFoundException e1){
                    e1.printStackTrace();
                    e.onError(e1);
                }
            }
        });
    }
}
