package edu.udb.loginfirebase16032024

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)

        // Definir el clic del botón para abrir la actividad de registro
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email: String = etEmail.text.toString()
            val password: String = etPassword.text.toString()
            signIn(email, password)
        }
    }

    private fun showAlertDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }


    private fun signIn(email: String, password: String) {

        // Validar los campos
        when {
            email.isEmpty() || password.isEmpty() -> {
                showAlertDialog("Error", "Por favor, complete todos los campos")
                return
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showAlertDialog("Error", "Por favor, ingrese una dirección de correo electrónico válida")
                return
            }
            password.length < 6 -> {
                showAlertDialog("Error", "La contraseña debe tener al menos 6 caracteres")
                return
            }
        }

        // Validar los campos
        if (email.isEmpty() || password.isEmpty()) {
            // Mostrar un mensaje si algún campo está vacío
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            // Mostrar un mensaje si el correo electrónico no es válido
            Toast.makeText(this, "Por favor, ingrese una dirección de correo electrónico válida", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Log.d(TAG, "signInWithEmail:success, User: $user")
                    // Aquí puedes redirigir a la siguiente actividad o realizar otras operaciones
                    val intent = Intent(this, WelcomeActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    // Aquí puedes mostrar un mensaje de error al usuario, por ejemplo, Toast
                    showAlertDialog("Error", "signInWithEmail:failure" + task.exception)

                }
            }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
