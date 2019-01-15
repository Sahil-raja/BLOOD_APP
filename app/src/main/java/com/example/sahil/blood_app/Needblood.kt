package com.example.sahil.blood_app

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_needblood.*
import java.io.File

class Needblood : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_needblood)

      var database = FirebaseDatabase.getInstance()
//        var shp = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        var myRef = database.getReference("users")
        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot?) {

                var list = mutableListOf<Doner>()

                var it:Iterable<DataSnapshot> = p0!!.children
                it.forEach {
                    var area = it.child("Area").getValue().toString()
                    var bloodg = it.child("Blood_Group").getValue().toString()
                    var city = it.child("City").getValue().toString()
                    var mob = it.child("MobileNo").getValue().toString()
                    var name = it.child("Name").getValue().toString()
                    var state = it.child("State").getValue().toString()

                    var doner = Doner(area, bloodg, city, mob, name, state)
                    list.add(doner)



                }
                lview.layoutManager = LinearLayoutManager(this@Needblood)
                lview.adapter = RecyclerAdapter(list)
           }

            override fun onCancelled(p0: DatabaseError?) {

            }

        })
    }

}
