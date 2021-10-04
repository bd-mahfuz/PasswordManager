package com.jfaq.passmanager.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jfaq.passmanager.data.entities.CredentialInfo;

import java.util.List;

@Dao
public interface CredentialInfoDao {

    @Query("select * from credential_info")
    List<CredentialInfo> getAllCredentialInfo();

    @Query("select * from credential_info where id = :id")
    CredentialInfo findById(int id);

    @Query("select * from credential_info where title = :title")
    CredentialInfo getByTitle(String title);

    @Insert
    void save(CredentialInfo credentialInfo);

    @Update
    void update(CredentialInfo credentialInfo);

    @Delete
    void delete(CredentialInfo credentialInfo);
}
