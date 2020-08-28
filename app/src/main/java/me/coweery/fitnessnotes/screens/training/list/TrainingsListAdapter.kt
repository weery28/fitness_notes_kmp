package me.coweery.fitnessnotes.screens.training.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import me.R
import me.coweery.fitnessnotes.sqldelight.data.model.Training

class TrainingsListAdapter(
    context: Context,
    private val onItemClicked: (Training) ->Unit
) : BaseAdapter() {

    val trainings = mutableListOf<Training>()
    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val viewHolder = if (convertView == null) {

            view = inflater.inflate(R.layout.trainings_list_item, parent, false)
            ViewHolder(
                view.findViewById(R.id.tv_name)
            ).apply {
                view.tag = this
            }

        } else {
            view = convertView
            convertView.tag as ViewHolder
        }

        viewHolder.tvName.text = trainings[position].name

        view.setOnClickListener {
            onItemClicked(trainings[position])
        }

        return view
    }

    override fun getCount(): Int = trainings.size

    override fun getItem(position: Int): Any = trainings[position]

    override fun getItemId(position: Int): Long = trainings[position].id

    class ViewHolder(
        val tvName: TextView
    )
}