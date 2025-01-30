package info.example.myappali1

import android.inputmethodservice.InputMethodService
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.PopupWindow
import info.example.myappali1.databinding.MykeyboardLayoutBinding

class mykeyboard_layout : InputMethodService() {

    private lateinit var popupWindow: PopupWindow

    override fun onCreateInputView(): View {

        val keyboardView = LayoutInflater.from(this).inflate(R.layout.mykeyboard_layout, null)
        val mykeyboardLayoutBinding = MykeyboardLayoutBinding.inflate(layoutInflater)


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