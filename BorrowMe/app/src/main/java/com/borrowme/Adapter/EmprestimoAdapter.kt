package com.borrowme.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.borrowme.Model.Emprestimo
import com.borrowme.R

class EmprestimoAdapter constructor(var context: Context, private var emprestimos: List<Emprestimo>): BaseAdapter() {

    private val mInflator: LayoutInflater
    internal var emp: List<Emprestimo>

    init {
        this.mInflator = LayoutInflater.from(context)
        this.emp = emprestimos
    }

    override fun getCount(): Int {
        return emp.size
    }

    override fun getItem(position: Int): Any {
        return emp[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val emprestimo = emp[position]


        val view: View
        val vh: ListRowHolder

        if (convertView == null) {
            view = mInflator.inflate(R.layout.emprestimo_list, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        vh.nomePessoa.setText(emprestimo.nome)
        vh.parentesco.setText(emprestimo.nome)
        vh.dataDevolucao.setText(emprestimo.dataDevolucao)
        vh.nomeObjeto.setText(emprestimo.nome)
        //vh.btnStatus.setBackgroundColor(60179113)

        return view
    }

    private class ListRowHolder(row: View?) {
         val nomeObjeto: TextView
         val dataDevolucao: TextView
         val parentesco: TextView
         val nomePessoa: TextView
         val btnStatus: View

        init {
            this.nomeObjeto = row?.findViewById<TextView>(R.id.textViewProductName) as TextView
            this.dataDevolucao = row?.findViewById<TextView>(R.id.dataObjetoDevolucao) as TextView
            this.parentesco = row?.findViewById<TextView>(R.id.tvParentesco) as TextView
            this.nomePessoa = row?.findViewById<TextView>(R.id.nomePessoa) as TextView
            this.btnStatus = row?.findViewById<View>(R.id.btnStatus) as View
        }
    }
}

