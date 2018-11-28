package com.borrowme.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import com.borrowme.Adapter.EmprestimoAdapter
import com.borrowme.Model.Emprestimo
import com.borrowme.Aplication.MyApplication
import com.borrowme.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var emprestimos: List<Emprestimo>? = MyApplication.database?.emprestimoDao()?.getAllEmprestimos()

        var emprestimosAdapter = EmprestimoAdapter(this, emprestimos!!)
        var listViewEmprestimos = findViewById<ListView>(R.id.listViewEmprestimos)
        listViewEmprestimos.adapter = emprestimosAdapter

        addButton.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
            finish()
        }

        listViewEmprestimos.setOnItemClickListener { parent, view, position, id ->

            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("emprestimo", emprestimos[position])
            startActivity(intent)
        }

    }
}
