package info.example.myappali1

import android.inputmethodservice.InputMethodService
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import info.example.myappali1.databinding.MykeyboardLayoutBinding

class mykeyboard_layout : InputMethodService() {

    private lateinit var popupWindow: PopupWindow

    override fun onCreateInputView(): View {

        val keyboardView = LayoutInflater.from(this).inflate(R.layout.mykeyboard_layout, null)
        val mykeyboardLayoutBinding = MykeyboardLayoutBinding.inflate(layoutInflater)


        //List if button IDs in your layout
        val buttonIDs = arrayOf(
            R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9,
            R.id.btn0,R.id.btnQ,R.id.btnW,R.id.btnE,R.id.btnR,R.id.btnT,R.id.btnY,R.id.btnU,R.id.btnI,R.id.btnO,
            R.id.btnP,R.id.btnA,R.id.btnS,R.id.btnD,R.id.btnF,R.id.btnG,R.id.btnH,R.id.btnJ,R.id.btnK,R.id.btnL,
            R.id.btnX,R.id.btnC,R.id.btnV,R.id.btnB,R.id.btnN,R.id.btnM,R.id.btnDot,R.id.btnComma,
        )
        for (buttonId in buttonIDs){
            val button = mykeyboardLayoutBinding.root.findViewById<Button>(buttonId)
            button.setOnClickListener{
                val inputConnection = currentInputConnection
                inputConnection?.commitText(button.text.toString(),1)

            }
        }
        mykeyboardLayoutBinding.btnBackSpace.setOnClickListener{
            val inputConnection = currentInputConnection
            inputConnection?.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
            return@setOnClickListener
        }
        mykeyboardLayoutBinding.btnEnter.setOnClickListener{
            val inputConnection = currentInputConnection
            inputConnection?.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER))
            return@setOnClickListener
        }
        mykeyboardLayoutBinding.btnSpace.setOnClickListener{
            val inputConnection = currentInputConnection
            inputConnection?.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SPACE))
            return@setOnClickListener
        }

        mykeyboardLayoutBinding.btnEmoji.setOnClickListener{
            showClipboardPopup(keyboardView)

        }

        return keyboardView
    }

    private fun showClipboardPopup(anchorView: View) {


        val popupView = LayoutInflater.from(this).inflate(R.layout.emoji_layout_keyboard, null)
        popupWindow = PopupWindow(popupView, anchorView.width, 600, true)

        val closeButton = popupView.findViewById<ImageButton>(R.id.btnBack)
        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.showAsDropDown(anchorView, 0, -anchorView.height)


    }


}