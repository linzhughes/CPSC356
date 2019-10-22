package com.linz.miky.cpsc356.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.linz.miky.cpsc356.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    loginUsernameField.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // unused
      }

      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        p0?.apply { loginButton.isEnabled = length > 0 }
      }

      override fun afterTextChanged(p0: Editable?) {
        // unused
      }
    })

    loginButton.setOnClickListener {
      startActivity(Intent(this, MainActivity::class.java).apply { putExtra("username", loginUsernameField.text) })
    }
  }
}