package jp.inc.eda.chapterone_master.domain.model;

import android.support.annotation.Nullable;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

/**
 * Created by watanabe on 2017/07/04.
 */
@Table
public class Todo {

    @PrimaryKey(autoincrement = true)
    public long id;

    @Column(indexed = true)
    public String title;

    @Column
    @Nullable
    public String description;

    @Column
    public long createAt;

    @Column
    public long updateAt;

    @Column
    public boolean isChecked;
}
