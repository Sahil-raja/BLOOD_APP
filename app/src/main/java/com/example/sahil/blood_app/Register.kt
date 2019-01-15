package com.example.sahil.blood_app

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)


        date_btn.setOnClickListener {
            var cal = Calendar.getInstance()
/*   Context context, DatePickerDialog.OnDateSetListener listener, int year,
int month, int dayOfMonth          */
            var dpd = DatePickerDialog(this@Register,
                    object : DatePickerDialog.OnDateSetListener{
                        override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                            et2.editText!!.setText("$p3-${p2+1}-$p1")
                        }
                    },
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH))
            dpd.show()

        } // DPD

        sp1.setOnItemSelectedListener(
                object: AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        if(p2>0) {
                            /*  Toast.makeText(this@Register,
                                      sp1.selectedItem.toString(), Toast.LENGTH_LONG).show()*/
                            var i = sp1.selectedItem.toString()
                            bld_grp.editText!!.setText(i)
                        }
                    }
                })


        var mAuth = FirebaseAuth.getInstance()

        save_btn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                //   validateEmail()
                //     validatePassword()
                val reg_email = email2!!.editText!!.text.toString().trim()
                val reg_password = password3!!.editText!!.text.toString().trim()

                if (TextUtils.isEmpty(reg_email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return
                }

                if (TextUtils.isEmpty(reg_password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return
                }

                if (reg_password.length < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return
                }


                mAuth!!.createUserWithEmailAndPassword(reg_email,reg_password).
                        addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(this@Register, "register successful", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@Register,MainActivity::class.java))
                            } else {
                                Toast.makeText(this@Register, "register failed", Toast.LENGTH_SHORT).show()
                            }
                        }
            }



        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
