package com.example.implement

import ListView
import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        debugHashKey()
        mediaPlayer =  MediaPlayer.create(this, R.raw.duel)

        mediaPlayer?.setOnPreparedListener{

        }
        mediaPlayer?.start()
      //  val fragment_list_view = ListView()
        val fragment_login = login()
        val manager = supportFragmentManager;
        val transaction = manager.beginTransaction();
        transaction.replace(R.id.Layout,fragment_login,"fragment_login")
        //transaction.replace(R.id.Layout, fragment_list_view,"fragment_list_view");
        //transaction.addToBackStack("fragment_list_view");
        transaction.addToBackStack("fragment_login");
        transaction.commit();

    }

    private fun debugHashKey() {
        try {
            val info = packageManager.getPackageInfo(
                "com.example.implement",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.getEncoder().encodeToString(md.digest()))
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
    }

}
