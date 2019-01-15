package com.example.sahil.blood_app

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.lisdata.*
import java.util.jar.Attributes
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_part_detail.*


class PartDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_part_detail)

        var call:ImageView = findViewById(R.id.call)

        var i = getIntent()
//
        //    if(getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name"))
        if (i.hasExtra("iMob") && i.hasExtra("iName")) {

            var kName = i.getStringExtra("iName")
            var kBlood = i.getStringExtra("iBlood_Group")
            var kCity = i.getStringExtra("iCity")
            var kState = i.getStringExtra("iState")
            var kArea = i.getStringExtra("iArea")
            var kMobile = i.getStringExtra("iMob")

            var name: TextView = findViewById(R.id.tx11)
            name.setText(kName)
            var blood: TextView = findViewById(R.id.pBld)
            blood.setText(kBlood)
            var city: TextView = findViewById(R.id.pCity)
            city.setText(kCity)
            var state: TextView = findViewById(R.id.pState)
            state.setText(kState)
            var area: TextView = findViewById(R.id.pArea)
            area.setText(kArea)
            var mobile: TextView = findViewById(R.id.pContact)
            mobile.setText(kMobile)



            call.setOnClickListener({
                var i = Intent()
                i.setAction(Intent.ACTION_DIAL)
                i.setData(Uri.parse("tel:"+kMobile))
                startActivity(i)
            })

        }

    }
}
