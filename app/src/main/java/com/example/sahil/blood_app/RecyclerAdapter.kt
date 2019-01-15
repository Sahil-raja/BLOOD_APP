package com.example.sahil.blood_app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

class RecyclerAdapter(var list:List<Doner>/*,var clickListener: (Doner) -> Unit*/):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    lateinit var context: Context
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: RecyclerAdapter.ViewHolder, p1: Int) {
        var doner = list[p1]

        //  (p0 as ViewHolder).bind(list[p1], clickListener)

        //      p0.tx.text = list.get(p1)
        p0.tx.text = doner.Name
        p0.tx1.text = doner.City
        p0.tx2.text = doner.Area
        p0.tx3.text = doner.State
        p0.tx4.text = doner.Blood_Group
        p0.tx5.text = doner.MobileNo

        p0.tx1.visibility = TextView.INVISIBLE
        p0.tx2.visibility = TextView.INVISIBLE
        p0.tx3.visibility = TextView.INVISIBLE

        var a: String = p0.tx.text.toString()
        var b = p0.tx1.text.toString()
        var c = p0.tx2.text.toString()
        var d = p0.tx3.text.toString()
        var e = p0.tx4.text.toString()
        var f = p0.tx5.text.toString()

        p0.call.setOnClickListener({
            var i = Intent()
            i.setAction(Intent.ACTION_DIAL)
            i.setData(Uri.parse("tel:" + f))
            context.startActivity(i)
        })
        p0.parentlayout.setOnClickListener({
            var i = Intent(context, PartDetailActivity::class.java)
            i.putExtra("iName", a)
            i.putExtra("iCity", b)
            i.putExtra("iArea", c)
            i.putExtra("iState", d)
            i.putExtra("iBlood_Group", e)
            i.putExtra("iMob", f)
            context.startActivity(i)
        })

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerAdapter.ViewHolder {
        var itemView = LayoutInflater.from(p0.context).inflate(R.layout.lisdata, p0, false)
        context = p0.context
        return ViewHolder(itemView)


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tx: TextView = itemView.findViewById(R.id.name)
        var call: ImageView = itemView.findViewById(R.id.call)
        var tx1: TextView = itemView.findViewById(R.id.dist)
        var tx2: TextView = itemView.findViewById(R.id.area)
        var tx3: TextView = itemView.findViewById(R.id.state1)
        var tx4: TextView = itemView.findViewById(R.id.bld)
        var tx5: TextView = itemView.findViewById(R.id.mobile)
        var parentlayout: RelativeLayout = itemView.findViewById(R.id.parent_layout)
    }
}






//inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//    internal var tx:TextView
//    internal var tx1:TextView
//    internal var tx2:TextView
//    internal var tx3:TextView
//    internal var tx4:TextView
//    internal var tx5:TextView
//    internal var call: ImageView
//    internal var parentlayout: RelativeLayout
//
//    init {
//
//        call = itemView.findViewById(R.id.call)
//           tx = itemView.findViewById(R.id.name)
//           tx1 = itemView.findViewById(R.id.dist)
//           tx2 = itemView.findViewById(R.id.area)
//           tx3 = itemView.findViewById(R.id.state1)
//           tx4 = itemView.findViewById(R.id.bld)
//           tx5 = itemView.findViewById(R.id.mobile)
//           parentlayout = itemView.findViewById(R.id.parent_layout)
//
//    }
//}
