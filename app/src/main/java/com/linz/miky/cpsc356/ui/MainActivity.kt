package com.linz.miky.cpsc356.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.linz.miky.cpsc356.R
import com.linz.miky.cpsc356.viewmodel.CountViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
  private lateinit var countViewModel: CountViewModel

  private var babyCounter: Long = 0
  private fun getUsername() = intent.extras?.get("username").toString().toLowerCase(Locale.US)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)
    countViewModel.getUserCount(getUsername()).observe(this,
      androidx.lifecycle.Observer { updateCounter(it) })

    myButton.setOnClickListener {
      countViewModel.setUserCount(getUsername(), babyCounter + 1)
    }
  }

  private fun updateCounter(count: Long) {
    babyCounter = count
    myTextView.text = babyCounter.toString()
  }
}
