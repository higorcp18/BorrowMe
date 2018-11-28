package com.borrowme.Activity

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.borrowme.Model.Emprestimo
import com.borrowme.R
import kotlinx.android.synthetic.main.details_screen.*

class DetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_screen)

        var i : Emprestimo = intent.getSerializableExtra("emprestimo") as Emprestimo
        var cepDetails = consultaCep(i.cep!!)

        nomeView.text = "Nome: "+i.nome
        cepView.text = "Cep: "+i.cep

        telefoneView.text = "Telefone: "+i.telefone
        produtoView.text = "Produto Emprestado: "+i.nomeObjeto
        fotoObjetoView.setImageURI(Uri.parse(i.foto))
    }

    fun consultaCep(cep: String) {

        val url = "https://viacep.com.br/ws/${cep}/json/"
        var list = mutableListOf<String>()

        val que = Volley.newRequestQueue(this@DetailsActivity)
        val req = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->
//            var uf = response["uf"].toString()
            list.add(0, response["bairro"].toString())
            list.add(1, response["localidade"].toString())
            list.add(2, response["uf"].toString())

            preencheDadosCep(list)

        }, Response.ErrorListener {
            print("Fail")
        })

        que.add(req)

    }

    fun preencheDadosCep(list: List<String>) {
        bairroView.text = "Bairro: "+ list[0]
        localidadeView.text = "Localidade: "+list[1]
        ufView.text = "UF: "+list[2]
    }

}