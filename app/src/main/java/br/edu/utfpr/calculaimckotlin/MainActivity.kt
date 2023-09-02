package br.edu.utfpr.calculaimckotlin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() { //fim da MainActivity

    private lateinit var etPeso : EditText
    private lateinit var etAltura : EditText
    private lateinit var tvResultado : TextView
    private lateinit var btCalcular : Button
    private lateinit var btLimpar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPeso = findViewById( R.id.etPeso )
        etAltura = findViewById( R.id.etAltura )
        tvResultado = findViewById( R.id.tvResultado )
        btCalcular = findViewById( R.id.btCalcular )
        btLimpar = findViewById( R.id.btLimpar )

        btCalcular.setOnClickListener {
            btCalcularOnClick()
        }

        btLimpar.setOnClickListener {
            btLimparOnClick()
        }

        btLimpar.setOnLongClickListener {
            Toast.makeText( this, getString(R.string.long_limpar), Toast.LENGTH_SHORT ).show()
            false
        }
        Log.d( "ciclo_de_vida", "onCreate() executado" )

    } //fim do onCreate()

    private fun btLimparOnClick() {
        etPeso.setText( "" )
        etAltura.setText( "" )
        tvResultado.text = getString(R.string.zeros)
        etPeso.requestFocus()
    }

    private fun btCalcularOnClick() {

        if ( etPeso.text.toString().isEmpty() ) {
            etPeso.error = getString(R.string.error_peso)
            return
        }

        if ( etAltura.text.toString().isEmpty() ) {
            etAltura.error = getString(R.string.error_altura)
            return
        }

        //entrada
        val peso = etPeso.text.toString().toDouble()
        val altura = etAltura.text.toString().toDouble()

        //processamento
        val calculo = Calculo()
        val imc = calculo.calculaIMC( peso, altura )

        //saída
        tvResultado.setText( imc )

    }

    override fun onStart() {
        super.onStart()
        Log.d( "ciclo_de_vida", "onStart() executado" )
    }

    override fun onResume() {
        super.onResume()
        Log.d( "ciclo_de_vida", "onResume() executado" )
    }

    override fun onPause() {
        super.onPause()
        Log.d( "ciclo_de_vida", "onPause() executado" )
    }

    override fun onStop() {
        super.onStop()
        Log.d( "ciclo_de_vida", "onStop() executado" )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d( "ciclo_de_vida", "onDestroy() executado" )
    }

    override fun onRestart() {
        super.onRestart()
        Log.d( "ciclo_de_vida", "onRestart() executado" )
    }

}


class Calculo {
    fun calculaIMC( peso : Double, altura : Double ) : String {
        val imc = peso / altura.pow( 2.0 )
        val df = DecimalFormat( "0.0")
        return df.format( imc )
    }
}
