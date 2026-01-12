package com.triply.barrierfreetrip

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.fragment.app.FragmentManager
import com.triply.barrierfreetrip.databinding.DialogAllProgressBinding
import com.triply.barrierfreetrip.feature.BaseDialogFragment

class ProgressDialogFragment : BaseDialogFragment<DialogAllProgressBinding>(
    R.layout.dialog_all_progress
) {
    fun show(manager: FragmentManager) {
        super.show(manager, "ProgressDialogFragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomAlertDialog)
    }
}