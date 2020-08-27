package me.coweery.fitnessnotes.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import me.R
import me.coweery.fitnessnotes.presenters.login.LoginScreenContract
import me.coweery.fitnessnotes.provideLoginScreenPresenter

class LoginScreen : AppCompatActivity(), LoginScreenContract.View {

    private val presenter = provideLoginScreenPresenter()

    private val etLogin by lazy { findViewById<EditText>(R.id.et_login) }
    private val etPassword by lazy { findViewById<EditText>(R.id.et_password) }
    private val btnSignIn by lazy { findViewById<Button>(R.id.btn_login) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.attachView(this)
        btnSignIn.setOnClickListener {
            presenter.onLoginClicked(
                etLogin.text.toString(),
                etPassword.text.toString()
            )
        }
    }

    override fun openMainScreen() {
        etLogin.setText("Login success")
    }
}