package com.sun.sql;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sun on 2018/3/5.
 */

public class Car implements Parcelable {
    String name;
    int price;
    boolean quick;

    public Car() {

    }

    protected Car(Parcel in) {
        name = in.readString();
        price = in.readInt();
        quick = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeByte((byte) (quick ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isQuick() {
        return quick;
    }

    public void setQuick(boolean quick) {
        this.quick = quick;
    }
}
