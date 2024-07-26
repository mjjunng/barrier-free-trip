package com.triply.barrierfreetrip

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import com.triply.barrierfreetrip.databinding.DialogLoadingProgressBinding
import com.triply.barrierfreetrip.databinding.DialogReviewUploadBinding

class BFTLoadingProgressBar(
    context: Context
): Dialog(context) {

    private val binding by lazy {
        DialogLoadingProgressBinding.inflate(LayoutInflater.from(context), null, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCancelable(false)
        setContentView(binding.root)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }
}