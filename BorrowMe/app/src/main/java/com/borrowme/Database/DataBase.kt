package com.borrowme.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.borrowme.DAO.EmprestimoDao
import com.borrowme.Model.Emprestimo

/**
 * Created by juliosantos on 11/6/18.
 */
@Database(version = 1, entities = arrayOf(Emprestimo::class))
abstract class AppDataBase : RoomDatabase() {
    abstract fun emprestimoDao(): EmprestimoDao
}