package com.example.implement

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject

class CustomAdapter (act : FragmentActivity, val dataSource: JSONArray) : RecyclerView.Adapter<CustomAdapter.Holder>() {

    private val thisActivity : FragmentActivity = act
    private val thiscontext: Context = act.baseContext

    class Holder(view : View) : RecyclerView.ViewHolder(view) {
        private val View = view;

        lateinit var layout : LinearLayout
        lateinit var titleTextView: TextView
        lateinit var detailTextView: TextView
        lateinit var image: ImageView

        fun Holder(){
            layout = View.findViewById<View>(R.id.listview_layout) as LinearLayout
            titleTextView = View.findViewById<View>(R.id.tv_name) as TextView
            detailTextView = View.findViewById<View>(R.id.tv_description) as TextView
            image = View.findViewById<View>(R.id.imgV) as ImageView
        }

    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.layout_listview, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.Holder()

        holder.titleTextView.setText( dataSource.getJSONObject(position).getString("title").toString() )

        val strDetail = "Level: " + dataSource.getJSONObject(position).getString("level").toString()  + " " +
                "ATK: " + dataSource.getJSONObject(position).getString("ATK").toString()  + " " +
                "DEF: " + dataSource.getJSONObject(position).getString("DEF").toString()


        holder.detailTextView.setText( strDetail )

        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(holder.image)

        holder.layout.setOnClickListener{
            val head = dataSource.getJSONObject(position).getString("title").toString()
            val body = dataSource.getJSONObject(position).getString("description").toString()
            val img = dataSource.getJSONObject(position).getString("image").toString()
            val attribute = dataSource.getJSONObject(position).getString("attribute").toString()
            val type = dataSource.getJSONObject(position).getString("type").toString()
            val level = dataSource.getJSONObject(position).getString("level").toString()
            val atk = dataSource.getJSONObject(position).getString("ATK").toString()
            val def = dataSource.getJSONObject(position).getString("DEF").toString()
            val fragment_item_selected = item_selected().newInstance(head,body,img, attribute, type, level, atk, def)
            val fm = thisActivity.supportFragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.Layout, fragment_item_selected,"fragment_item_selected")
            transaction.addToBackStack("fragment_item_selected")
            transaction.commit()
        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}
