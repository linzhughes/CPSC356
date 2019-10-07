package com.linz.miky.cpsc356

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
  private var babyCounter: Long = 0
  private fun getStore() = getPreferences(Context.MODE_PRIVATE)
  private fun getUsername() = intent.extras?.get("username").toString().toLowerCase(Locale.US)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState != null) {
      updateCounter(savedInstanceState.getLong(BABY_COUNTER_KEY, 0))
    } else if (getStore().contains(BABY_COUNTER_KEY)) {
      updateCounter(getStore().getLong(getUsername(), 0))
    }

    myButton.setOnClickListener {
      updateCounter(babyCounter++)
    }
  }

  private fun updateCounter(count: Long) {
    babyCounter = count
    myTextView.text = babyCounter.toString()

    myButton.text = when (babyCounter) {
      1L -> "stop"
      in 2 .. 9 -> myButton.text.toString().plus("!")
      else -> myButton.text
    }
  }

  override fun onPause() {
    super.onPause()
    getStore().edit().putLong(getUsername(), babyCounter).apply()
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
