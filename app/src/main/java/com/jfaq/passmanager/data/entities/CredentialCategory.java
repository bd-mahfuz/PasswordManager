package com.jfaq.passmanager.data.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(tableName = "credential_category",
        indices = {@Index(value = "title", unique = true)})
public class CredentialCategory {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;

    public CredentialCategory() {
    }

    public CredentialCategory(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
