package com.linz.miky.cpsc356

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.linz.miky.cpsc356.util.rotate90
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  private var babyCounter: Long = 0
  fun getStore() = getPreferences(Context.MODE_PRIVATE)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState != null) {
      babyCounter = savedInstanceState.getLong(BABY_COUNTER_KEY, 0)
      myTextView.text = babyCounter.toString()
    }

    myButton.setOnClickListener {
      babyCounter++
      myTextView.text = babyCounter.toString()
      myImage.rotate90()

      myButton.text = when (babyCounter) {
        1L -> "stop"
        in 2 .. 9 -> myButton.text.toString().plus("!")
        else -> myButton.text
      }
    }
  }

  override fun onPause() {
    super.onPause()
    getStore().edit().putLong(BABY_COUNTER_KEY, babyCounter).apply()
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    outState?.run {
      putLong(BABY_COUNTER_KEY, babyCounter)
    }

    super.onSaveInstanceState(outState)
  }

  companion object {
    private const val BABY_COUNTER_KEY = "babyCounterKey"
  }
}
