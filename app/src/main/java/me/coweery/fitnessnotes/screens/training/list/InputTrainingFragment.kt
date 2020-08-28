package me.coweery.fitnessnotes.screens.training.list

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import me.R
import java.text.SimpleDateFormat
import java.util.*

class InputTrainingFragment(
    private val output : Output
) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var etName: EditText
    private lateinit var etDate: EditText
    private lateinit var btnSave: View

    private val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
    private var date = Date()
    private val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_training_input, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog!!.window!!
            .setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etName = view.findViewById(R.id.et_name)
        etDate = view.findViewById(R.id.et_date)

        btnSave = view.findViewById(R.id.btn_save)
        btnSave.setOnClickListener {
            output.onTrainingDataReceived(etName.text.toString(), date)
            dismissAllowingStateLoss()
        }

        etDate.setOnClickListener {

            calendar.time = date
            DatePickerDialog(
                context!!,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
        date = calendar.time
        etDate.setText(simpleDateFormat.format(date))
    }

    override fun onResume() {

        super.onResume()
        etName.setText("")
        etDate.setText("")
    }
}