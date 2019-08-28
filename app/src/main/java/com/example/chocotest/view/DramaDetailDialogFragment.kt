package com.example.chocotest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class DramaDetailDialogFragment : DialogFragment() {
//
//    lateinit var binding: FragmentUserDetailBinding
//    private val userDetail by viewModel<UserDetailViewModel>()
//
//    companion object {
//        const val KEY_USER_INFO_LOGIN = "key_user_info_login"
//
//        fun createInstance(userInfo: UserInfo):  UserDetailDialogFragment {
//            val userDetailDialogFragment = UserDetailDialogFragment()
//            val bundle = Bundle()
//
//            bundle.putString(KEY_USER_INFO_LOGIN, userInfo.login)
//            userDetailDialogFragment.arguments = bundle
//            return userDetailDialogFragment
//        }
//    }
//
//    private var userLogin: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        arguments?.run {
//            userLogin = this.getString(KEY_USER_INFO_LOGIN, null)
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_user_detail, null, false)
//        binding.lifecycleOwner = this
//        binding.userDetail = userDetail
//        binding.imageClose.setOnClickListener { dialog.dismiss() }
//
//        return binding.root
//    }
//
//    override fun onStart() {
//        super.onStart()
//        val dialog = dialog
//        if (dialog != null) {
//            val width = ViewGroup.LayoutParams.MATCH_PARENT
//            val height = ViewGroup.LayoutParams.MATCH_PARENT
//            dialog.window!!.setLayout(width, height)
//        }
//        dialog.window!!.setBackgroundDrawableResource(R.color.colorUserDetailBackground)
//
//        Timber.e("userLogin = $userLogin")
//        userLogin?.run {
//            userDetail.getUserDetail(userLogin!!)
//        }
//
//        // show/hide progress bar
//        userDetail.isLoading.observe(this, Observer<Boolean> {
//            if (it) {
//                binding.progressBar.visibility = View.VISIBLE
//            } else {
//                binding.progressBar.visibility = View.GONE
//            }
//        })
//
//        // process userDetail related data
//        userDetail.userDetail.observe(this, Observer<UserDetailInfo> {
//            // avatar
//            Glide.with(requireContext())
//                .load(it.avatarUrl)
//                .placeholder(R.color.colorUserAvatarPlaceHolderColor)
//                .into(binding.imageUserPic)
//
//            binding.viewUserDetailInfo.setData(login = it.login, isSiteAdmin = it.isSiteAdmin)
//            binding.viewUserDetailLoc.setData(it.location)
//            binding.viewUserDetailBlog.setData(it.blogUrl)
//        })
//
//        userDetail.errorMessage.observe(this, Observer<String> {
//            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
//        })
//    }
}