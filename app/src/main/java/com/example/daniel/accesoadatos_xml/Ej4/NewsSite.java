package com.example.daniel.accesoadatos_xml.Ej4;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by daniel on 8/12/16.
 */

public class NewsSite implements Parcelable{
    private String name;
    private int icon;
    private String url;

    public NewsSite(String name, int icon, String url) {
        this.name = name;
        this.icon = icon;
        this.url = url;
    }

    protected NewsSite(Parcel in) {
        name = in.readString();
        icon = in.readInt();
        url = in.readString();
    }

    public static final Creator<NewsSite> CREATOR = new Creator<NewsSite>() {
        @Override
        public NewsSite createFromParcel(Parcel in) {
            return new NewsSite(in);
        }

        @Override
        public NewsSite[] newArray(int size) {
            return new NewsSite[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(icon);
        parcel.writeString(url);
    }
}
