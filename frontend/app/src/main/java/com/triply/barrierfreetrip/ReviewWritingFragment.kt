package com.triply.barrierfreetrip

import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.fragment.app.viewModels
import com.triply.barrierfreetrip.databinding.FragmentReviewWritingBinding
import com.triply.barrierfreetrip.feature.BaseFragment
import com.triply.barrierfreetrip.model.MainViewModel

class ReviewWritingFragment : BaseFragment<FragmentReviewWritingBinding>(R.layout.fragment_review_writing) {

    private val viewModel: MainViewModel by viewModels()
    private var contentId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentId = arguments?.getString(MainActivity.CONTENT_ID)
    }

    override fun initInViewCreated() {
        with(binding) {
            // 제목 설정

            // 돌아가기 버튼 설정
            ivReviewWritingBack.setOnClickListener {
                backToPrevFragment()
            }

            // 등록 버튼 설정
            btnReviewUpload.setOnClickListener {
                if (etReviewWritingContent.text.isNullOrBlank()) {
                    return@setOnClickListener
                }
                contentId?.let {
                    viewModel.postReview(
                        contentId = it,
                        rating = binding.rtbarReviewWritingRating.getRating().toDouble(),
                        content = binding.etReviewWritingContent.text?.toString() ?: ""
                    )
                }
            }

            etReviewWritingContent.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val isActivated = s.isNullOrBlank().not()
                    btnReviewUpload.setTextColor(
                        requireContext()
                            .getColor(
                                if (isActivated) R.color.black
                                else R.color.bright_gray
                            )
                    )
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            rtbarReviewWritingRating.isClickable = true

            val onLaunchKeyboardListener = object: OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val rect = Rect()
                    root.getWindowVisibleDisplayFrame(rect)

                    val screenHeight = root.height - etReviewWritingContent.top
                    val keyboardHeight = screenHeight - (rect.bottom - rect.top)

                    etReviewWritingContent.height = screenHeight - keyboardHeight
                    root.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            }
            root.viewTreeObserver.addOnGlobalLayoutListener(onLaunchKeyboardListener)
        }

        viewModel.isDataLoading.observe(viewLifecycleOwner) {
            if (it.getContentIfNotHandled() == true) {
                BFTReviewUploadCheckDialog(requireContext()) { backToPrevFragment() }.show()
            }
        }
    }

    private fun backToPrevFragment() {
        val frgManager = parentFragmentManager
        frgManager.beginTransaction()
            .remove(this@ReviewWritingFragment)
            .commit()
        frgManager.popBackStack()
    }
}