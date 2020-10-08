package com.esofthore.mytasks

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.Toolbar as Toolbar1


class MainActivity : AppCompatActivity() {
    private var coordinatorLayout: CoordinatorLayout? = null //this variable receives the coordinatorLayout that is in the activity.main
    private var recyclerView: RecyclerView? = null //this variable receives the RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setTitleTextColor(Color.WHITE)//changing text color
        setSupportActionBar(toolbar)//toolbar startup

        //function to control activties
        control()
    }

    private fun control() {
        coordinatorLayout = findViewById(R.id.layoutMain)
        recyclerView = findViewById(R.id.recyclerMain)
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton

        fab.setOnClickListener{
            showDialog(false, null,-1)
        }
    }

    private fun showDialog(isUpdate: Boolean, nothing: Nothing?, position: Int) {
        val layoutInflaterAndroid = LayoutInflater.from(applicationContext)
        val view = layoutInflaterAndroid.inflate(R.layout.dialog_list, null)

        val userInput = AlertDialog.Builder(this@MainActivity)
        userInput.setView(view)

        val input = view.findViewById<EditText>(R.id.dialogText)
        val titleOfDialogList = view.findViewById<TextView>(R.id.dialogTitle)
        titleOfDialogList.text = if(!isUpdate) getString(R.string.newTask) else getString(R.string.editTask)

        userInput
            .setCancelable(false)
            .setPositiveButton(if(isUpdate) getString(R.string.updateTask) else getString(R.string.saveTask)) {dialogBox, id ->}
            .setNegativeButton(getString(R.string.cancelTask)) {dialogBox, id->dialogBox.cancel()}

        val alertDialog = userInput.create()
        alertDialog.show()

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(View.OnClickListener {
            if(TextUtils.isEmpty(input.text.toString())) {
                Toast.makeText(this@MainActivity, getString(R.string.toastTask), Toast.LENGTH_SHORT).show()
                return@OnClickListener
            } else {
                alertDialog.dismiss()
            }
        })
    }
}