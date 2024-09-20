package edu.udb.loginfirebase16032024

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class WelcomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val selectedProducts = mutableListOf<String>() // Lista de productos seleccionados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        auth = FirebaseAuth.getInstance()


        // Añadir productos al carrito
        configureProductButtons()


    }

    private fun configureProductButtons() {
        // Simulación de productos con botones "Añadir"
        val productButtons = listOf(
            Pair(findViewById<Button>(R.id.btnProducto4), "Manzanas"),
            Pair(findViewById<Button>(R.id.btnProducto2), "Peras"),
            Pair(findViewById<Button>(R.id.btnProducto3), "Agua"),
            Pair(findViewById<Button>(R.id.btnProducto4), "Coca-Cola"),
            Pair(findViewById<Button>(R.id.btnProducto5), "Jugo de Naranja"),
            Pair(findViewById<Button>(R.id.btnProducto6), "Salsa de Tomate"),
            Pair(findViewById<Button>(R.id.btnProducto7), "Productos de Limpieza"),
            Pair(findViewById<Button>(R.id.btnProducto8), "Queso Cheddar"),
            Pair(findViewById<Button>(R.id.btnProducto9), "Papel Scotth"),
            Pair(findViewById<Button>(R.id.btnProducto10), "Leche Salud")
        )

        for ((button, productName) in productButtons) {
            button.setOnClickListener {
                selectedProducts.add(productName)
                Toast.makeText(this, "$productName añadido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

