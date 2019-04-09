package com.epic.ssb.data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;



@Entity(tableName = "agent_model")
public class AgentModel implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "nameOfAgent")
    private String nameOfAgent;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "email")
    private  String email;
    @ColumnInfo(name = "gender")
    private String gender;
    @ColumnInfo(name = "nic")
    private String nic;
    @ColumnInfo(name = "telephone")
    private String telephone;
    @ColumnInfo(name = "district")
    private String district;
    @ColumnInfo(name = "dsOffice")
    private String dsOffice;
    @ColumnInfo(name = "photoOfAgent")
    private String photoOfAgent;

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setNameOfAgent(String nameOfAgent) {
        this.nameOfAgent = nameOfAgent;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setDsOffice(String dsOffice) {
        this.dsOffice = dsOffice;
    }

    public void setPhotoOfAgent(String photoOfAgent) {
        this.photoOfAgent = photoOfAgent;
    }
}
