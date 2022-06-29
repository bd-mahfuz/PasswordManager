package com.jfaq.passmanager.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jfaq.passmanager.data.entities.Vault;

import java.util.List;

@Dao
public interface VaultDao {
    @Insert
    void insert(Vault vault);

    @Update
    void update(Vault vault);

    @Delete
    void delete(Vault vault);

    @Query("select * from vault")
    public List<Vault> getAll();
}
