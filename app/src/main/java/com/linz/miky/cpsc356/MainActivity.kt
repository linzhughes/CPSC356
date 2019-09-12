package com.linz.miky.cpsc356

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.linz.miky.cpsc356.util.rotate90
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  private var babyCounter: Int = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState != null) {
      babyCounter = savedInstanceState.getInt(BABY_COUNTER_KEY, 0)
    }

    myButton.setOnClickListener {
      babyCounter++
      myImage.rotate90()
    }
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    outState?.run {
      putInt(BABY_COUNTER_KEY, babyCounter)
    }

    super.onSaveInstanceState(outState)
  }

  companion object {
    private const val BABY_COUNTER_KEY = "babyCounterKey"
  }
}
