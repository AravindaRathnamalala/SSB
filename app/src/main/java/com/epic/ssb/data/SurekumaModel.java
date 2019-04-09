package com.epic.ssb.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import io.reactivex.annotations.NonNull;


@Entity(tableName = "surekuma_table")
public class SurekumaModel implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "dob")
    private String dob;

    @ColumnInfo(name = "nic")
    private String nic;

    @ColumnInfo(name = "type_of_premium_payment")
    private String typeOfPensionPayment;

    @ColumnInfo(name = "monthly_pension")
    private String monthlyPayment;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setTypeOfPensionPayment(String typeOfPensionPayment) {
        this.typeOfPensionPayment = typeOfPensionPayment;
    }

    public void setMonthlyPayment(String monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
