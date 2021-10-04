package com.jfaq.passmanager.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jfaq.passmanager.data.entities.CredentialCategory;

import java.util.List;

@Dao
public interface CredentialCategoryDao {

    @Query("select * from credential_category")
    List<CredentialCategory> getAllCredentialCategory();

    @Query("select * from credential_category where id = :id")
    CredentialCategory getById(int id);

    @Query("select * from credential_category where title = :title")
    CredentialCategory getByTitle(String title);

    @Insert
    void save(CredentialCategory credentialCategory);

    @Update
    void update(CredentialCategory credentialCategory);

    @Delete
    void delete(CredentialCategory credentialCategory);

}
