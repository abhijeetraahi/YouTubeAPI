package com.raahi.youtubeapi.ui.home

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.raahi.youtubeapi.R
import com.raahi.youtubeapi.databinding.HomeActivityBinding
import com.raahi.youtubeapi.ui.base.BaseActivity
import com.raahi.youtubeapi.ui.home.adapter.HomeAdapter
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    private lateinit var mBinding: HomeActivityBinding

    lateinit var mPresenter: HomeContract.Presenter<HomeContract.View>
        @Inject set

    lateinit var mAdapter: HomeAdapter
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.home_activity)

        activityComponent.inject(this)

        mPresenter.onAttach(this)
    }

    override fun setUpRecyclerView() {
        mBinding.recyclerView.run {
            if (adapter == null)
                layoutManager = LinearLayoutManager(applicationContext)

            visibility = View.VISIBLE
            adapter = mAdapter

            hideProgressView()
        }
    }

    override fun showProgressView() {
        mBinding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressView() {
        mBinding.progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetach()
    }
}