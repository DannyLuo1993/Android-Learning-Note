package com.danny.englishdictionary;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Dictionary")
public class Word {

    //Primary Key是主键， 当id相同时，对象会被替换掉
    @PrimaryKey(autoGenerate = true)
    private int id;

    public Word(String englishword, String chineseword) {
        this.englishword = englishword;
        this.chineseword = chineseword;
    }

    @ColumnInfo(name = "English Word")
    private String englishword;

    @ColumnInfo(name = "Chinese Word")
    private String chineseword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglishword() {
        return englishword;
    }

    public void setEnglishword(String englishword) {
        this.englishword = englishword;
    }

    public String getChineseword() {
        return chineseword;
    }

    public void setChineseword(String chineseword) {
        this.chineseword = chineseword;
    }
}
