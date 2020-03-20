package com.example.implement


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import java.util.jar.Attributes

class item_selected : Fragment() {
    private var head : String = ""
    private var body : String = ""
    private var img : String = ""
    private var attribute : String = ""
    private var type : String = ""
    private var level : String = ""
    private var atk : String = ""
    private var def : String = ""
    fun newInstance(head: String,body: String,img: String, attribute: String, type: String, level: String, atk: String, def: String): item_selected {
        val fragment = item_selected()
        val bundle = Bundle()
        bundle.putString("head", head)
        bundle.putString("body", body)
        bundle.putString("img", img)
        bundle.putString("attribute", attribute)
        bundle.putString("type", type)
        bundle.putString("level", level)
        bundle.putString("ATK", atk)
        bundle.putString("DEF", def)
        fragment.setArguments(bundle)
        return fragment
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            head = bundle.getString("head").toString()
            body = bundle.getString("body").toString()
            img = bundle.getString("img").toString()
            attribute = bundle.getString("attribute").toString()
            type = bundle.getString("type").toString()
            level = bundle.getString("level").toString()
            atk = bundle.getString("ATK").toString()
            def = bundle.getString("DEF").toString()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_item_selected, container, false)
        val imgVi : ImageView = view.findViewById(R.id.imgV)
        val headTxt : TextView = view.findViewById(R.id.cardName)
        val detailTxt : TextView = view.findViewById(R.id.cardDetail)
        val bodyTxt : TextView = view.findViewById(R.id.cardDescription)
        val typeTxt : TextView = view.findViewById(R.id.cardType)

        val btn : Button = view.findViewById(R.id.play_btn)

        val strDetails = "Attribute: $attribute Level: $level\nATK: $atk DEF: $def"
        headTxt.setText(head)
        bodyTxt.setText(body)
        detailTxt.setText(strDetails)
        typeTxt.setText("Type: " + type)
        Glide.with(activity!!.baseContext)
            .load(img)
            .into(imgVi);
        // Inflate the layout for this fragment

        btn.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            builder.setMessage("Are You Sure?")
            builder.setPositiveButton("Yes",
                DialogInterface.OnClickListener{ dialog, id ->
                    val fragment_game = Game().newInstance(img);
                    val fm = fragmentManager;
                    val transaction : FragmentTransaction = fm!!.beginTransaction()
                    transaction.replace(R.id.Layout, fragment_game, "fragment_game")
                    transaction.addToBackStack("fragment_game")

                    transaction.commit()
                })
            builder.setNegativeButton("No",
                DialogInterface.OnClickListener{ dialog, which ->
                    dialog.dismiss()
                })
            builder.show()
        }

        return view
    }




}
