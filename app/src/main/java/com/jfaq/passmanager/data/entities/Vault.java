package com.jfaq.passmanager.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity(tableName = "vault")
public class Vault {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
}
