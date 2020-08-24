package me.coweery.fitnessnotes.screens

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import me.R
import me.coweery.fitnessnotes.DI
import me.coweery.fitnessnotes.presenters.login.LoginScreenContract
import org.kodein.di.instance

class LoginScreen : AppCompatActivity(), LoginScreenContract.View {

    private val presenter by DI.kodein.instance<LoginScreenContract.Presenter>()

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
        println("login success")
    }
}