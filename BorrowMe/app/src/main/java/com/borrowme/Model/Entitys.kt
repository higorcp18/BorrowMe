package com.borrowme.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import java.io.Serializable

@Entity(tableName = "emprestimo")
data class Emprestimo(
        var nome: String = "",
        var cep: String? = "",
        var telefone: String? = "",
        var nomeObjeto: String? = "",
        var foto: String? = "",
        var dataEmprestimo: String? = "",
        var dataDevolucao: String? = ""
): Serializable{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
