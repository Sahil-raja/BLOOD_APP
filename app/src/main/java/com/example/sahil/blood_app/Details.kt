package com.example.sahil.blood_app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_needblood.*

class Details : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


            var database = FirebaseDatabase.getInstance()
            var myRef = database.getReference("users")
            myRef.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(p0: DataSnapshot?) {

                    var list = mutableListOf<String>()
                    var it:Iterable<DataSnapshot> = p0!!.children
                    it.forEach {
                        var itr = it.children
                        var msg = ""
                        itr.forEach {
                            msg = msg+"\n"+it.value
                        }
                        list.add(msg)
                    }


                    var adapter = ArrayAdapter(this@Details,android.R.layout.simple_list_item_1,list)

              }

                override fun onCancelled(p0: DatabaseError?) {

                }

            })
    }
}
