package com.triply.barrierfreetrip

import android.graphics.Rect
import android.os.Bundle
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
        contentId = arguments?.getString("contentId")
    }

    override fun initInViewCreated() {
        with(binding) {
//            // 제목 설정
//
//
//            // 등록 버튼 설정
            btnReviewUpload.setOnClickListener {
                contentId?.let {
                    viewModel.postReview(
                        contentId = it,
                        rating = binding.rtbarReviewWritingRating.getRating().toDouble(),
                        content = binding.etReviewWritingContent.text?.toString() ?: ""
                    )
                }
            }

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

        viewModel.isUploadingReviewSucceed.observe(viewLifecycleOwner) {
            if (it.getContentIfNotHandled() == true) {
                BFTDialog(requireContext()).show()
            }
        }
    }


}