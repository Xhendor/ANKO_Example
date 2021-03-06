package com.uabc.edu.anko

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import android.text.InputType.TYPE_CLASS_TEXT
import android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    //Cuando queremos utilizar el XML layout
   // setContentView(R.layout.activity_main)

   //Cuando cargamos un layout ANKO
   MainActivityUi().setContentView(this)


  }

fun tryLogin(ui: AnkoContext<MainActivity>, name: CharSequence?, password: CharSequence?) {
  ui.doAsync {
    Thread.sleep(500)

    activityUiThreadWithContext {
      if (checkCredentials(name.toString(), password.toString())) {
        toast("Logged in! :)")
        startActivity<CountriesActivity>()
       // email("rosendorsc@gmail.com","Correo de prueba","Prueba desde anko")
        //sendSMS("6862032977","Soy un SMS!!!")
        //share("Texto desde ANKO APP","Test Anko")
      } else {
        toast("Wrong password :( Enter user:password")

      }
    }
  }
}

private fun checkCredentials(name: String, password: String) = name == "user" && password == "password"
}

class MainActivityUi : AnkoComponent<MainActivity> {
  private val customStyle = { v: Any ->
    when (v) {
      is Button -> v.textSize = 26f
      is EditText -> v.textSize = 24f
    }
  }

  override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
    verticalLayout {
      padding = dip(32)

      imageView(android.R.drawable.ic_menu_manage).lparams {
        margin = dip(16)
        gravity = Gravity.CENTER
      }
      val name = editText {
        hintResource = R.string.name
      }
      val password = editText {
        hintResource = R.string.password
        inputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD
      }

      button("Log in") {
        onClick {
          ui.owner.tryLogin(ui, name.text, password.text)
        }
      }

      myRichView()
    }.applyRecursively(customStyle)
  }


}
