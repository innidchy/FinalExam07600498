package com.example.finalexam07600498.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Ledger")
public class LedgerItem {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @SerializedName("username")
    public String username;

    @ColumnInfo(name = "Fullname")
    @SerializedName("fullname")
    public String fullname;

    @ColumnInfo(name = "password")
    @SerializedName("password")
    public String password;

    public LedgerItem(String username,String fullname, String password) {

        this.username = username;
        this.fullname=fullname;
        this.password = password;
    }
}
