package com.example.ejercicio

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.example.ejercicio.databinding.ActivityMain5Binding

class MainActivity5 : AppCompatActivity() {

    private lateinit var binding: ActivityMain5Binding
    private val nombrePref = "mis_preferencias"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener una referencia a SharedPreferences
        val sharedPreferences = getSharedPreferences(nombrePref, Context.MODE_PRIVATE)


        // Recuperar los datos del Intent
        val acertadas = intent.getIntExtra("acertadas", 0)
        val falladas = intent.getIntExtra("falladas", 0)

        val porcentajeAciertos = calcularPorcentajeAciertos(acertadas, falladas)
        binding.tvAcertadas.text = "Acertadas: ${acertadas.toString()}"
        binding.tvFalladas.text = "Falladas: ${falladas.toString()}"
        binding.tvPorcentaje.text = "Porcentaje de aciertos: $porcentajeAciertos%"

        //Actualizar los valores de la shared preferences
        var valorAntiguoAciertos = sharedPreferences.getInt("acertadas_predeterminado", 0)
        var valorAntiguoFalladas = sharedPreferences.getInt("falladas_predeterminado", 0)
        //Nuevos valores actualizados
        var nuevoValorAcertadas = acertadas + valorAntiguoAciertos
        var nuevoValorFalladas = falladas + valorAntiguoFalladas
        //Actualizo valores pantalla
        val porcentajeAciertosTotal = calcularPorcentajeAciertos(nuevoValorAcertadas, nuevoValorFalladas)
        binding.tvAcertadasTotal.text = "Acertadas totales: ${nuevoValorAcertadas.toString()}"
        binding.tvFalladasTotal.text = "Falladas totales: ${nuevoValorFalladas.toString()}"
        binding.tvPorcentajeTotal.text = "Porcentaje de aciertos totales: $porcentajeAciertosTotal%"
        //Actualizo la shared
        sharedPreferences.edit {
            putInt("acertadas_predeterminado", nuevoValorAcertadas)
            putInt("falladas_predeterminado", nuevoValorFalladas)
            apply()
        }

        binding.btnVolverMenu.setOnClickListener {
            val intent = Intent(this@MainActivity5, MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnVolverAJugar.setOnClickListener {
            val intent = Intent(this@MainActivity5, MainActivity3::class.java)
            startActivity(intent)
        }

    }

    fun calcularPorcentajeAciertos(acertadas: Int, falladas: Int): Double {
        if (acertadas + falladas == 0) {
            // Evitar división por cero, retorna 0.0 si no hay intentos
            return 0.00
        }

        val porcentaje = (acertadas.toDouble() / (acertadas + falladas).toDouble()) * 100.0
        return "%.2f".format(porcentaje).toDouble()
    }

}