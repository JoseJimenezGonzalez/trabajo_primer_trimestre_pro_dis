package com.example.ejercicio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.os.Handler
import com.example.ejercicio.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    var vidas = 10

    var aciertos = 0

    val posicionescartas = listOf(
        Pair(0, 0),
        Pair(0, 1),
        Pair(0, 2),
        Pair(1, 0),
        Pair(1, 1),
        Pair(1, 2),
        Pair(2, 0),
        Pair(2, 1),
        Pair(2, 2),
        Pair(3, 0),
        Pair(3, 1),
        Pair(3, 2)
    )

    val tablero: Array<Array<String>> = arrayOf(
        arrayOf("A", "B", "C"),
        arrayOf("D", "E", "F"),
        arrayOf("A", "B", "C"),
        arrayOf("D", "E", "F")
    )

    val mapaPosicionImagen = mutableMapOf<Pair<Int, Int>, ImageView>()

    val mapaTableroImagen = mapOf(
        "A" to R.drawable.black_lotus,
        "B" to R.drawable.force_of_will,
        "C" to R.drawable.intuition,
        "D" to R.drawable.mox_diamond,
        "E" to R.drawable.mox_sapphire,
        "F" to R.drawable.vampiric_tutor
    )

    var primeraCartaSeleccionada: Pair<Int, Int>? = null
    var valorPrimeraCartaGirada: String = ""

    var segundaCartaSeleccionada: Pair<Int, Int>? = null
    var valorSegundaCartaGirada: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar el mapaPosicionImagen después de inflar el diseño
        inicializarMapaPosicionImagen()

        //Barajamos para que sea aleatorio
        barajarCartas()

        //Iniciar vidas
        iniciarVidas()

        //Configuramos los botones
        configurarBotones()

        //Configurar boton reiniciar
        configurarBotonReiniciar()
    }

    private fun configurarBotonReiniciar() {
        binding.btnVolverAJugar.setOnClickListener {
            recreate()
        }
        binding.btnVolverMenu.setOnClickListener {
            val intent = Intent(this@MainActivity2, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun iniciarVidas() {
        binding.tvLife.text = "Vidas: $vidas"
    }

    private fun barajarCartas() {
        tablero[0].shuffle()
        tablero[1].shuffle()
        tablero[2].shuffle()
        tablero[3].shuffle()
    }

    private fun configurarBotones() {
        binding.iv1.setOnClickListener {
            if(valorPrimeraCartaGirada.equals("") || valorSegundaCartaGirada.equals("")){
                procesarClickEnCarta(posicionescartas[0], binding.iv1)
            }
        }
        binding.iv2.setOnClickListener {
            if(valorPrimeraCartaGirada.equals("") || valorSegundaCartaGirada.equals("")){
                procesarClickEnCarta(posicionescartas[1], binding.iv2)
            }
        }
        binding.iv3.setOnClickListener {
            if(valorPrimeraCartaGirada.equals("") || valorSegundaCartaGirada.equals("")){
                procesarClickEnCarta(posicionescartas[2], binding.iv3)
            }
        }
        binding.iv4.setOnClickListener {
            if(valorPrimeraCartaGirada.equals("") || valorSegundaCartaGirada.equals("")){
                procesarClickEnCarta(posicionescartas[3], binding.iv4)
            }
        }
        binding.iv5.setOnClickListener {
            if(valorPrimeraCartaGirada.equals("") || valorSegundaCartaGirada.equals("")){
                procesarClickEnCarta(posicionescartas[4], binding.iv5)
            }
        }
        binding.iv6.setOnClickListener {
            if(valorPrimeraCartaGirada.equals("") || valorSegundaCartaGirada.equals("")){
                procesarClickEnCarta(posicionescartas[5], binding.iv6)
            }
        }
        binding.iv7.setOnClickListener {
            if(valorPrimeraCartaGirada.equals("") || valorSegundaCartaGirada.equals("")){
                procesarClickEnCarta(posicionescartas[6], binding.iv7)
            }
        }
        binding.iv8.setOnClickListener {
            if(valorPrimeraCartaGirada.equals("") || valorSegundaCartaGirada.equals("")){
                procesarClickEnCarta(posicionescartas[7], binding.iv8)
            }
        }
        binding.iv9.setOnClickListener {
            if(valorPrimeraCartaGirada.equals("") || valorSegundaCartaGirada.equals("")){
                procesarClickEnCarta(posicionescartas[8], binding.iv9)
            }
        }
        binding.iv10.setOnClickListener {
            if(valorPrimeraCartaGirada.equals("") || valorSegundaCartaGirada.equals("")){
                procesarClickEnCarta(posicionescartas[9], binding.iv10)
            }
        }
        binding.iv11.setOnClickListener {
            if(valorPrimeraCartaGirada.equals("") || valorSegundaCartaGirada.equals("")){
                procesarClickEnCarta(posicionescartas[10], binding.iv11)
            }
        }
        binding.iv12.setOnClickListener {
            if(valorPrimeraCartaGirada.equals("") || valorSegundaCartaGirada.equals("")){
                procesarClickEnCarta(posicionescartas[11], binding.iv12)
            }
        }
    }

    private fun procesarClickEnCarta(par: Pair<Int, Int>, iv: ImageView) {
        // Si es la primera carta seleccionada
        if (primeraCartaSeleccionada == null) {
            primeraCartaSeleccionada = par
            valorCartaPrimeraSeleccionada(primeraCartaSeleccionada!!)
            //Le damos la vuelta a la primera carta
            val recursoId = mapaTableroImagen[valorPrimeraCartaGirada] ?: 0
            iv.setImageResource(recursoId)
            //Impido que pueda darle de nuevo a esta carta
            iv.isClickable = false
        } else {
            // Si es la segunda carta seleccionada
            segundaCartaSeleccionada = par
            valorCartaSegundaSeleccionada(segundaCartaSeleccionada!!)
            //Le damos la vuelta a la segunda carta
            val recursoId = mapaTableroImagen[valorSegundaCartaGirada] ?: 0
            iv.setImageResource(recursoId)
            //Aqui hay que esperar un segundo
            Handler().postDelayed({
                if (valorPrimeraCartaGirada == valorSegundaCartaGirada) {
                    // Son iguales
                    primeraCartaSeleccionada = null
                    valorPrimeraCartaGirada = ""

                    segundaCartaSeleccionada = null
                    valorSegundaCartaGirada = ""
                    aciertos++
                    //Le quitamos el que sea clickable
                    //primera carta
                    val idIvPrimeraCarta = mapaPosicionImagen[primeraCartaSeleccionada]
                    if (idIvPrimeraCarta != null) {
                        idIvPrimeraCarta.isClickable = false
                    }
                    //Segunda carta
                    iv.isClickable = false
                    //Comprobamos si hemos ganado
                    if (aciertos == 6){
                        binding.clInvisible.visibility = View.VISIBLE
                        binding.tvMensajeFinalJuego.text = "¡Has ganado!"
                    }
                } else {
                    // Son distintas
                    // Las ponemos boca abajo
                    // Con la posición de la primera carta podemos ponerla de nuevo boca abajo
                    val idIvPrimeraCarta = mapaPosicionImagen[primeraCartaSeleccionada]
                    idIvPrimeraCarta?.setImageResource(R.drawable.back)
                    //Hacemos que la primera vuelva a ser clicable
                    if (idIvPrimeraCarta != null) {
                        idIvPrimeraCarta.isClickable = true
                    }
                    // La segunda carta también boca abajo
                    iv.setImageResource(R.drawable.back)
                    // Reset
                    primeraCartaSeleccionada = null
                    valorPrimeraCartaGirada = ""

                    segundaCartaSeleccionada = null
                    valorSegundaCartaGirada = ""
                    vidas -= 1
                    binding.tvLife.text = "Vidas: $vidas"
                    if(vidas == 0){
                        binding.clInvisible.visibility = View.VISIBLE
                        binding.tvMensajeFinalJuego.text = "Has perdido"
                    }
                }
            }, 1000) // Retrasa la ejecución del código dentro del bloque durante 1000 milisegundos (1 segundo)
        }
    }
    private fun valorCartaPrimeraSeleccionada(cartaSeleccionada: Pair<Int, Int>) {
        //Valor
        valorPrimeraCartaGirada = tablero[cartaSeleccionada.first][cartaSeleccionada.second]
    }

    private fun valorCartaSegundaSeleccionada(cartaSeleccionada: Pair<Int, Int>) {
        //Valor
        valorSegundaCartaGirada = tablero[cartaSeleccionada.first][cartaSeleccionada.second]
    }

    private fun inicializarMapaPosicionImagen() {
        mapaPosicionImagen.apply {
            put(Pair(0, 0), binding.iv1)
            put(Pair(0, 1), binding.iv2)
            put(Pair(0, 2), binding.iv3)
            put(Pair(1, 0), binding.iv4)
            put(Pair(1, 1), binding.iv5)
            put(Pair(1, 2), binding.iv6)
            put(Pair(2, 0), binding.iv7)
            put(Pair(2, 1), binding.iv8)
            put(Pair(2, 2), binding.iv9)
            put(Pair(3, 0), binding.iv10)
            put(Pair(3, 1), binding.iv11)
            put(Pair(3, 2), binding.iv12)
        }
    }
}