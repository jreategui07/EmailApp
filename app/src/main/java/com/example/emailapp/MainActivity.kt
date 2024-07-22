package com.example.emailapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emailapp.databinding.ActivityMainBinding
import com.example.pokemonapp.utils.SnackbarHelper

class MainActivity : AppCompatActivity(), ClickDetectorInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: EmailAdapter
    private lateinit var snackbarHelper: SnackbarHelper
    private var emailList:MutableList<Email> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // init custom snackbar helper
        this.snackbarHelper = SnackbarHelper(this.binding.root)

        // getting mock data
        this.emailList = Mock.EMAIL_LIST.toMutableList()

        // create an instance of the custom adapter
        this.adapter = EmailAdapter(this, this.emailList, this)
        this.binding.rvContainer.adapter = this.adapter

        // configuring the RecyclerView with a LinearLayoutManager
        this.binding.rvContainer.layoutManager = LinearLayoutManager(this)

        // adding a line between each item in the list of the
        this.binding.rvContainer.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun onRowClicked(position: Int) {
        this.emailList.removeAt(position)
        this.adapter.notifyDataSetChanged()
        this.snackbarHelper.showSnackbar("Email deleted.")
    }

    override fun onImportanceClicked(position: Int) {
        this.emailList[position].isImportant = !this.emailList[position].isImportant
        this.adapter.notifyDataSetChanged()
        this.snackbarHelper.showSnackbar("Status updated.")
    }
}