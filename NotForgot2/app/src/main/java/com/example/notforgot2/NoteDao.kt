package com.example.notforgot2

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {

    // Добавление Object в бд
    @Insert
    fun insertAll(myObject: Note)

    // Удаление Object из бд
    @Delete
    fun delete(myObject: Note)

    // Получение всех Object из бд
    @Query("SELECT * FROM myNotes")
    fun getAllNotes(): List<Note>

    // Получение всех Object из бд с условием
    @Query("SELECT * FROM myNotes WHERE id LIKE :condition")
    fun getAllPeopleWithFavoriteColor(condition: String): List<Note>

}