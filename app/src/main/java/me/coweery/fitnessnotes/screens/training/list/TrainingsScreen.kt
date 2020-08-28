package me.coweery.fitnessnotes.screens.training.list

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import me.R
import me.coweery.fitnessnotes.presenters.trainings.list.TrainingsListContract
import me.coweery.fitnessnotes.provideTrainingsListPresenter
import me.coweery.fitnessnotes.screens.BaseActivity
import me.coweery.fitnessnotes.sqldelight.data.model.Training
import java.util.Date

class TrainingsScreen :
    BaseActivity<TrainingsListContract.View, TrainingsListContract.Presenter>(),
    TrainingsListContract.View, Output {

    override val presenter = provideTrainingsListPresenter()
    private val trainingsList by lazy { findViewById<ListView>(R.id.lv_trainings_list) }
    private val addTrainingButton by lazy { findViewById<FloatingActionButton>(R.id.fab_add) }
    private lateinit var adapter: TrainingsListAdapter
    private lateinit var inputTraining : InputTrainingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainings_list)
        presenter.attachView(this)
        adapter =
            TrainingsListAdapter(
                this
            ) {
                presenter.onTrainingClicked(it.id)
            }
        trainingsList.adapter = adapter
        setupToolbar()
        addTrainingButton.setOnClickListener {
            presenter.onAddTrainingClicked()
        }

        inputTraining =
            InputTrainingFragment(
                this
            )

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,  R.string.app_name, R.string.app_name
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onResume() {
        super.onResume()
        presenter.onScreenLoaded()
    }

    override fun onTrainingDataReceived(name: String, date: Date) {
        presenter.onTrainingDataReceived(name, date.time)
    }

    override fun showTrainings(list: List<Training>) {
        adapter.trainings.clear()
        adapter.trainings.addAll(list)
        adapter.notifyDataSetChanged()
    }

    override fun showCreateTrainingScreen() {
        inputTraining.show(supportFragmentManager, "exerciseInput")
    }

    override fun showTrainingScreen(id: Long) {
    }
}