package com.triply.barrierfreetrip

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.triply.barrierfreetrip.adapter.ReviewAdapter
import com.triply.barrierfreetrip.adapter.decoration.ReviewViewHolderDecoration
import com.triply.barrierfreetrip.databinding.FragmentReviewBinding
import com.triply.barrierfreetrip.feature.BaseFragment
import com.triply.barrierfreetrip.model.MainViewModel

class ReviewFragment : BaseFragment<FragmentReviewBinding>(R.layout.fragment_review) {
    private var contentId : String? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentId = arguments?.getString(MainActivity.CONTENT_ID)
    }

    override fun initInViewCreated() {
        with(binding) {
            // 제목 설정
            
            // 리뷰 리사이클러뷰 설정
            rvReview.adapter = ReviewAdapter()
            rvReview.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            if (rvReview.itemDecorationCount == 0) {
                rvReview.addItemDecoration(ReviewViewHolderDecoration())
            }

            // 리뷰 작성 버튼
            btnWritingReview.setOnClickListener {
                val bundle = Bundle()
                val reviewWritingFragment = ReviewWritingFragment()

                bundle.putString(MainActivity.CONTENT_ID, contentId)
                reviewWritingFragment.arguments = bundle

                parentFragmentManager
                    .beginTransaction()
                    .add(R.id.main_nav_host_fragment, reviewWritingFragment)
                    .commit()
            }
        }

        contentId?.let {
            viewModel.getReviews(it)
        }
        // 테스트용
//        viewModel.getReviews("1882945")

        viewModel.reviews.observe(viewLifecycleOwner) {
            val totalReviewCnt = it.totalCnt
            val reviews = it.reviews
            (binding.rvReview.adapter as ReviewAdapter).setDataList(reviews)
            (binding.tvReviewCount).text = totalReviewCnt.toString()
        }
    }
}