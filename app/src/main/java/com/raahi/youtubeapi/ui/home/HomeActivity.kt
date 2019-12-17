package com.raahi.youtubeapi.ui.home

import android.annotation.TargetApi
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.*
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.raahi.youtubeapi.R
import com.raahi.youtubeapi.data.network.model.VideoInfo
import com.raahi.youtubeapi.databinding.HomeActivityBinding
import com.raahi.youtubeapi.service.MyJobService
import com.raahi.youtubeapi.ui.base.BaseActivity
import com.raahi.youtubeapi.ui.details.DetailsActivity
import com.raahi.youtubeapi.ui.home.adapter.HomeAdapter
import com.raahi.youtubeapi.util.PaginationListener
import com.raahi.youtubeapi.util.PaginationListener.Companion.PAGE_START
import kotlinx.android.synthetic.main.home_activity.*
import javax.inject.Inject


class HomeActivity : BaseActivity(), HomeContract.View, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var mBinding: HomeActivityBinding

    lateinit var mPresenter: HomeContract.Presenter<HomeContract.View>
        @Inject set

    lateinit var mAdapter: HomeAdapter
        @Inject set

    lateinit var mLayoutManager: LinearLayoutManager

    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(
            context: Context,
            intent: Intent
        ) {
            mBinding.tvFetchNewData.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.home_activity)
        mBinding.view = this

        activityComponent.inject(this)

        mPresenter.onAttach(this)

    }

    override fun initToolBar() {
        setSupportActionBar(mBinding.toolbar)
    }

    override fun initView() {
        mLayoutManager = LinearLayoutManager(applicationContext)

        swipeRefresh.setOnRefreshListener(this)

        mBinding.recyclerView.addOnScrollListener(object : PaginationListener(mLayoutManager) {

            override fun loadMoreItems() {
                mPresenter.isLoading(true)
                mPresenter.increaseCurrentPage()
                mPresenter.getYouTubeVideos(true)
            }

            override val isLastPage: Boolean
                get() = mPresenter.isLastPage()
            override val isLoading: Boolean
                get() = mPresenter.isLoading()
        })
    }

    override fun notifyRecyclerView() {
        if (mPresenter.getCurrentPage() != PAGE_START) mAdapter.removeLoading()
        mAdapter.addAllItems()
        swipeRefresh.isRefreshing = false

        if (mPresenter.getCurrentPage() < mPresenter.getTotalPage()) {
            mAdapter.addLoading()
        } else {
            mPresenter.isLoading(true)
        }
        mPresenter.isLoading(false)
    }

    override fun launchDetailsActivity(videoInfo: VideoInfo, view: View) {
        val activityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, getString(R.string.tranasaction))

        startActivity(
            DetailsActivity.startActivity(this, videoInfo),
            activityOptionsCompat.toBundle()
        )
    }

    override fun refreshData(view: View) {
        mBinding.tvFetchNewData.visibility = View.GONE
        mPresenter.setItemCount(0)
        mPresenter.setCurrentPage(1)
        mPresenter.isLastPage(false)
        mPresenter.getYouTubeVideos(false)
    }

    override fun showRefreshDataView() {
        mBinding.tvFetchNewData.visibility = View.VISIBLE
    }

    override fun setUpRecyclerView() {
        mBinding.recyclerView.run {
            if (adapter == null) {
                layoutManager = mLayoutManager
                adapter = mAdapter
            } else {
                mAdapter.notifyDataSetChanged()
            }
            swipeRefresh.isRefreshing = false
            visibility = View.VISIBLE
            hideProgressView()
        }
    }

    override fun showProgressView() {
        mBinding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressView() {
        mBinding.progressBar.visibility = View.GONE
        mBinding.tvFetchNewData.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetach()
    }

    override fun onRefresh() {
        mPresenter.setItemCount(0)
        mPresenter.setCurrentPage(1)
        mPresenter.isLastPage(false)
        mPresenter.getYouTubeVideos(false)
    }


    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
             IntentFilter("com.raahi.youtubeapi")
        )
    }

    override fun onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver)
        super.onStop()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun scheduleJob() {
        val serviceComponent = ComponentName(this, MyJobService::class.java)
        val builder = JobInfo.Builder(0, serviceComponent)
        builder.setPeriodic(300 * 1000)
        val jobScheduler = applicationContext.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(builder.build())
    }

}