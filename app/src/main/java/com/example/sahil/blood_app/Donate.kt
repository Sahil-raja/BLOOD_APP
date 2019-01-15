package com.example.sahil.blood_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_donate.*

class Donate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)


        var actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        save.setOnClickListener{
            var database = FirebaseDatabase.getInstance()
            var myRef = database.getReference("users")
            var fireUser = FirebaseAuth.getInstance().currentUser;

            if(fireUser!=null) {
                var child_ref = myRef.child(fireUser.uid)
                child_ref.child("Name").setValue(u_name.text.toString())
                child_ref.child("MobileNo").setValue(Mob_no.text.toString())
                child_ref.child("Blood_Group").setValue(Blood_grp.text.toString())
                child_ref.child("City").setValue(city.text.toString())
                child_ref.child("State").setValue(state2.text.toString())
                child_ref.child("Area").setValue(area.text.toString())
            }else{
                Toast.makeText(this@Donate, "Error getting user info", Toast.LENGTH_LONG).show()
            }

        }

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
