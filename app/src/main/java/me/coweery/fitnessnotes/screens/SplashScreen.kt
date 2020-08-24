package me.coweery.fitnessnotes.screens

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.R
import me.coweery.fitnessnotes.DI
import me.coweery.fitnessnotes.presenters.splash.SplashScreenContract
import org.kodein.di.instance

class SplashScreen : AppCompatActivity(), SplashScreenContract.View {

    private val presenter by DI.kodein.instance<SplashScreenContract.Presenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewLoaded()
    }

    override fun openLoginScreen() {
    }

    override fun openMainScreen() {
        println("MainScreen")
    }
}