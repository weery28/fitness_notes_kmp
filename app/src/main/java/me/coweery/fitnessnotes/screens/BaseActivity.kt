package me.coweery.fitnessnotes.screens

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import me.R
import me.coweery.fitnessnotes.mvp.BaseMvp

abstract class BaseActivity<S : BaseMvp.View, T : BaseMvp.Presenter<S>> :
    BaseMvp.View, AppCompatActivity() {

    protected abstract val presenter: T
    protected  val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar)}

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this as S)
    }

    protected fun setupToolbar(title: String? = null) {

        setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent))
        toolbar.apply {
            setBackgroundColor(Color.TRANSPARENT)
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            title?.let {
                supportActionBar?.title = it
            }
        }

        setBackArrowColor(ContextCompat.getColor(this, android.R.color.white))
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun setStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = color
        }
    }

    private fun setBackArrowColor(color: Int) {
        val upArrow: Drawable? = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material)
        upArrow?.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
    }
}