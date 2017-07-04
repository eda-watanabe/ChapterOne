package jp.inc.eda.chapterone_master.di;

import android.content.Context;

import com.github.gfx.android.orma.AccessThreadConstraint;

import jp.inc.eda.chapterone_master.domain.model.OrmaDatabase;

/**
 * Created by watanabe on 2017/07/04.
 */

public class Module {

    public OrmaDatabase provideOrma(Context context) {
        return new OrmaDatabase.Builder(context)
                .writeOnMainThread(AccessThreadConstraint.FATAL)
                .readOnMainThread(AccessThreadConstraint.FATAL)
                .trace(true)
                .build();
    }
}
