package com.sun.sql;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sun on 2018/1/17.
 */

@Entity

public class FirstGreen {
    @Id(autoincrement = true)
    private long id;

    @Generated(hash = 1355601508)
    public FirstGreen(long id) {
        this.id = id;
    }

    @Generated(hash = 417343951)
    public FirstGreen() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    @Property()
}
