package com.borrowme.DAO

import android.arch.persistence.room.*
import com.borrowme.Model.Emprestimo

@Dao
interface EmprestimoDao {

    @Query("SELECT * FROM emprestimo")
    fun getAllEmprestimos(): List<Emprestimo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmprestimo(vararg  emprestimo: Emprestimo)

    @Update
    fun updateEmprestimo(emprestimo: Emprestimo)

    @Delete
    fun deleteEmprestimo(emprestimo: Emprestimo)

}