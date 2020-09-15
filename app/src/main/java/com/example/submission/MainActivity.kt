package com.example.submission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.postDelayed

class MainActivity : AppCompatActivity() {

    private lateinit var topAnimation: Animation
    private lateinit var botAnimation: Animation
    private lateinit var image: ImageView
    private lateinit var title:TextView
    private lateinit var slogan:TextView

    companion object{
        const val SPLASH_SCREEN = 5000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * deklaration splash screen
         */
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        botAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)

        image = findViewById(R.id.iv_camera)
        title = findViewById(R.id.tv_title)
        slogan = findViewById(R.id.tv_slogan)

        image.animation = topAnimation
        title .animation = botAnimation
        slogan.animation = botAnimation

        /**
         * move to HomeActivity if splash complete
         */
        Handler().postDelayed({
            Intent(this,HomeActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }, SPLASH_SCREEN.toLong())

    }
}