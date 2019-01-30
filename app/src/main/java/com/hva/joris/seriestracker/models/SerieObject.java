package com.hva.joris.seriestracker.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "serie")
public class SerieObject implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "notes")
    private String notes;

    @ColumnInfo(name = "lastModified")
    private String lastModified;

    public SerieObject(String title, String status, String notes, String lastModified) {
        this.title = title;
        this.status = status;
        this.notes = notes;
        this.lastModified = lastModified;
    }

    protected SerieObject(Parcel in) {
        id = in.readLong();
        title = in.readString();
        status = in.readString();
        notes = in.readString();
        lastModified = in.readString();
    }

    public static final Creator<SerieObject> CREATOR = new Creator<SerieObject>() {
        @Override
        public SerieObject createFromParcel(Parcel in) {
            return new SerieObject(in);
        }

        @Override
        public SerieObject[] newArray(int size) {
            return new SerieObject[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long mId) {
        this.id = mId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(status);
        dest.writeString(notes);
        dest.writeString(lastModified);
    }
}
