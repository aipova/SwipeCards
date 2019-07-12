package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders
            .of(this)
            .get(SwipeViewModel::class.java)

        viewModel
            .modelStream
            .observe(this, Observer {
                bindCard(it)
            })


        motionLayout.setTransitionListener(object : TransitionAdapter() {

            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                when (currentId) {
                    R.id.offScreenRight,
                    R.id.offScreenLeft,
                    R.id.offScreenTop,
                    R.id.offScreenBottom -> {
                        motionLayout.progress = 0f
                        motionLayout.setTransition(R.id.rest, R.id.right)
                        topCard.rotation = 0f
                        viewModel.swipe()
                    }

                }
            }

        })
    }

    private fun bindCard(model: SwipeModel) {
        topCard.setCardBackgroundColor(model.topCard.backgroundColor)
        topText.text = model.topCard.number.toString()
        bottomCard.setCardBackgroundColor(model.bottomCard.backgroundColor)
        bottomText.text = model.bottomCard.number.toString()
    }
}
