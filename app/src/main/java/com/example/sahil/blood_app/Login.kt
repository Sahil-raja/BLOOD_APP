package com.example.sahil.blood_app

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class Login : AppCompatActivity() {

    var Email: TextInputLayout? = null
    var password: TextInputLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Email = findViewById(R.id.input_email)
        password = findViewById(R.id.input_password)

        var mAuth = FirebaseAuth.getInstance()

        log_btn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                //    validatePassword()

                val email = Email!!.editText!!.text.toString().trim()
                val password = password!!.editText!!.text.toString().trim()


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return
                }

                if (password.length < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return
                }
                mAuth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    if (it.isSuccessful){
                        var uid = it.result.user.uid
                        var shp = getSharedPreferences(packageName, Context.MODE_PRIVATE)
                        var editor = shp.edit()
                        editor.putString("uid", uid)
                        editor.apply()

                        Toast.makeText(this@Login,"LOGIN Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@Login,Navigater::class.java))
                    }else{
                        Toast.makeText(this@Login,"LOGIN failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })

        reg_btn.setOnClickListener({
            var i = Intent(this@Login,Register::class.java)
            startActivity(i)
        })


    }
}