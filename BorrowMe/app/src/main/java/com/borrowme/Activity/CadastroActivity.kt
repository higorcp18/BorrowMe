package com.borrowme.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.borrowme.Model.Emprestimo
import com.borrowme.Aplication.MyApplication
import com.borrowme.Notification.NotificationUtils
import com.borrowme.R
import kotlinx.android.synthetic.main.cadastro_screen.*
import java.text.SimpleDateFormat
import java.util.*

class CadastroActivity: AppCompatActivity() {

    val CAMERA_REQUEST_CODE = 0
    var currentUri: Uri? = null
    var emprestimo: Emprestimo = Emprestimo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro_screen)

        addFoto.setOnClickListener {

            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (callCameraIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
            }

        }


        confirmaCadastro.setOnClickListener {
            var dataEntregaConcatenada = "${dataEntregaPicker.dayOfMonth}/${dataEntregaPicker.month}/${dataEntregaPicker.year}"
            emprestimo = Emprestimo(
                nomeStamp.editableText.toString(),
                cepStamp.editableText.toString(),
                telefoneStamp.editableText.toString(),
                produtoStamp.editableText.toString(),
                currentUri.toString(),
                getDataAtual(),
                dataEntregaConcatenada
            )
            MyApplication.database?.emprestimoDao()?.insertEmprestimo(emprestimo)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

            val formataData = "dd/MM/yyyy"
            val batata = SimpleDateFormat(formataData)
            var datadevolucao = batata.parse(emprestimo.dataDevolucao)

            var time = datadevolucao.time - Date().time

            val mNotificationTime = Calendar.getInstance().timeInMillis + time
            NotificationUtils().setNotification(mNotificationTime, this@CadastroActivity)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data!= null) {

                    currentUri = saveImage(data.extras.get("data") as Bitmap, "")

                    fotoObjeto.setImageBitmap(data.extras.get("data") as Bitmap)
                }
            }
            else -> {
                Toast.makeText(this, "Unrecognized request code", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun saveImage(bitmap: Bitmap, title: String): Uri {

        val savedImageURL = MediaStore.Images.Media.insertImage(
                contentResolver,
                bitmap,
                title,
                "Image of $title"
        )

        return Uri.parse(savedImageURL)
    }

    fun getDataAtual(): String {
        var formataData: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        var data = Date()
        var dataFormatada = formataData.format(data)
        return dataFormatada
    }

}