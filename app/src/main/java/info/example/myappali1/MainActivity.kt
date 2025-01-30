package info.example.myappali1

import android.content.Context
import android.content.Intent
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import info.example.myappali1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private fun isKeyboardEnable(): Boolean {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val enabledInputMethodIDs = inputMethodManager.enabledInputMethodList.map { it.id }
        return enabledInputMethodIDs.contains("info.example.myappali1/.MyKeyBoard")
    }

    private fun openKeyboardChooserSittings() {
        val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        im.showInputMethodPicker()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        // check keyboard status
        binding.apply {

            if (isKeyboardEnable())
                btnEnableKeyboard.isEnabled = false

            btnEnableKeyboard.setOnClickListener {
                openKeyboardSittings()
            }
            btnChooseKeyboard.setOnClickListener {
                if (isKeyboardEnable()) {

                    openKeyboardChooserSittings()


                } else Toast.makeText(this@MainActivity, "Choose the keyboard action button.", Toast.LENGTH_SHORT).show()
            }


        }


    }

    private fun openKeyboardSittings(){
        val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
        startActivity(intent)
    }
}