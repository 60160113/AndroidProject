package com.example.implement


import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import kotlin.random.Random

class Game : Fragment() {
    private var img : String = ""
    var score : Int = 0

    var imgArray = ArrayList<ImageView>()

    var handler : Handler = Handler()
    var runnable : Runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        score = 0;

        val bundle = arguments
        if (bundle != null) {
            img = bundle.getString("img").toString()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        val timer : TextView = view.findViewById(R.id.timer)
        val scoreTxt : TextView = view.findViewById(R.id.scoreText)

        imgArray = arrayListOf(view.findViewById(R.id.img01), view.findViewById(R.id.img02), view.findViewById(R.id.img03),
            view.findViewById(R.id.img04), view.findViewById(R.id.img05), view.findViewById(R.id.img06),
            view.findViewById(R.id.img07), view.findViewById(R.id.img08), view.findViewById(R.id.img09));
        setImages()
        hideImages()

        object : CountDownTimer(10000, 1000) {
            override fun onFinish() {
                timer.text = "Time: 0"
                handler.removeCallbacks(runnable)
                for (images in imgArray) {
                    images.visibility = View.INVISIBLE
                }
            }

            override fun onTick(millisUntilFinished: Long) {
                timer.text = "Time: " + (millisUntilFinished / 1000).toString()
            }
        }.start()

        val img01 : ImageView = view.findViewById(R.id.img01)
        val img02 : ImageView = view.findViewById(R.id.img02)
        val img03 : ImageView = view.findViewById(R.id.img03)
        val img04 : ImageView = view.findViewById(R.id.img04)
        val img05 : ImageView = view.findViewById(R.id.img05)
        val img06 : ImageView = view.findViewById(R.id.img06)
        val img07 : ImageView = view.findViewById(R.id.img07)
        val img08 : ImageView = view.findViewById(R.id.img08)
        val img09 : ImageView = view.findViewById(R.id.img09)

        fun increaseScore() {
            score++
            scoreTxt.text = "Score: $score"
        }

        img01.setOnClickListener {
            increaseScore()
        }

        img02.setOnClickListener {
            increaseScore()
        }

        img03.setOnClickListener {
            increaseScore()
        }

        img04.setOnClickListener {
            increaseScore()
        }

        img05.setOnClickListener {
            increaseScore()
        }

        img06.setOnClickListener {
            increaseScore()
        }

        img07.setOnClickListener {
            increaseScore()
        }

        img08.setOnClickListener {
            increaseScore()
        }

        img09.setOnClickListener {
            increaseScore()
        }

        return view;
    }

    fun newInstance(img : String) : Game{
        val fragment = Game();
        val bundle = Bundle();

        bundle.putString("img", img)
        fragment.setArguments(bundle)
        return fragment;
    }

    fun setImages() {
        for (images in imgArray) {
            Glide.with(activity!!.baseContext)
                .load(img)
                .into(images);
        }
    }

    fun hideImages() {
        runnable = object : Runnable {
            override fun run() {
                for (image in imgArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = Random
                val index = random.nextInt(8 - 0)

                imgArray[index].visibility = View.VISIBLE

                handler.postDelayed(runnable, 500)
            }
        }

        handler.post(runnable)
    }

}

