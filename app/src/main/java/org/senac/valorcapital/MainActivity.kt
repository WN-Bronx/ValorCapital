package org.senac.valorcapital

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var capitalAtualComponent: EditText
    private lateinit var taxaJurosComponent: EditText
    private lateinit var numeroMesesComponent: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        capitalAtualComponent = findViewById<EditText>(R.id.eTextCapitalAtual)
        taxaJurosComponent = findViewById<EditText>(R.id.eTextTaxaJuros)
        numeroMesesComponent = findViewById<SeekBar>(R.id.seekBar)
    }

    fun calcCapitalFuturo(view: View) {
        if (validador()){

            var numeroMeses = numeroMesesComponent.progress.toDouble()
            var capitalAtual = capitalAtualComponent.text.toString().toDouble()
            var taxaJuros = taxaJurosComponent.text.toString().toDouble()
            var capitalFuturo = capitalAtual * Math.pow(1 + taxaJuros / 100 , numeroMeses.toDouble())


            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle(" Capital Futuro")
            alertDialogBuilder.setMessage("O Seu Valor Calculado do Capital Futuro é :   R$ ${"%.2f".format(capitalFuturo)}")

            alertDialogBuilder.setNeutralButton(" Confirmar") {_, _->}
            alertDialogBuilder.create().show()
        }
    }

    private fun validador(): Boolean{
        var validador = true

        if (capitalAtualComponent.text.isNullOrEmpty()){
            capitalAtualComponent.error = "Campo Obrigatório - Deve informar o Capital Atual!"
            validador = false
        }
        if (taxaJurosComponent.text.isNullOrEmpty()){
            taxaJurosComponent.error = "Campo Obrigatório - Você deve Informar uma Taxa!"
            validador = false
        }

        if (numeroMesesComponent.progress <= 0){
            Toast.makeText(this, "Informe o Número  de Meses!", Toast.LENGTH_SHORT).show()
            validador = false
        }

        return validador
    }

}
