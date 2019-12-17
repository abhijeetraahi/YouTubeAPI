package com.raahi.youtubeapi.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener
import com.raahi.youtubeapi.R
import com.raahi.youtubeapi.YouTubeAPIApplication
import com.raahi.youtubeapi.data.network.model.VideoInfo
import com.raahi.youtubeapi.databinding.DetailsActivityBinding
import com.raahi.youtubeapi.ui.details.adapter.CommentsAdapter
import com.raahi.youtubeapi.util.Const

class DetailsActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener,
    DetailsContract.View {

    private lateinit var mBinding: DetailsActivityBinding

    private lateinit var mPresenter: DetailsContract.Presenter

    private lateinit var mAdapter: CommentsAdapter

    private lateinit var mYoutubePlayer: YouTubePlayer

    companion object {

        private const val VIDEO_INFO = "video_info"

        fun startActivity(context: Context, videoInfo: VideoInfo): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(VIDEO_INFO, videoInfo)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.details_activity)

        mPresenter = DetailsPresenter(this, applicationContext as YouTubeAPIApplication)

        mAdapter = CommentsAdapter(mPresenter)

        mPresenter.setVideoInfo(intent.getSerializableExtra(VIDEO_INFO) as VideoInfo)
        mPresenter.onAttach()

    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        p1?.setPlayerStateChangeListener(playerStateChangeListener)
        p1?.setPlaybackEventListener(playbackEventListener)
        if (!p2) {
            p1?.loadVideo(mPresenter.getVideoInfo().videoId)
        }
        mYoutubePlayer = p1!!
    }

    private val playbackEventListener: PlaybackEventListener = object : PlaybackEventListener {
        override fun onPlaying() {}
        override fun onPaused() {}
        override fun onStopped() {}
        override fun onBuffering(b: Boolean) {}
        override fun onSeekTo(i: Int) {}
    }

    private val playerStateChangeListener: PlayerStateChangeListener =
        object : PlayerStateChangeListener {
            override fun onLoading() {}
            override fun onLoaded(s: String) {}
            override fun onAdStarted() {}
            override fun onVideoStarted() {}
            override fun onVideoEnded() {}
            override fun onError(errorReason: YouTubePlayer.ErrorReason) {}
        }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {

    }

    override fun initView() {
        mBinding.youtubePlayer.initialize(Const.KEY, this)

        mBinding.model = mPresenter.getVideoInfo()
    }

    override fun setUpRecyclerView() {
        mBinding.recyclerView.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(this@DetailsActivity)
                adapter = mAdapter
            } else {
                mAdapter.notifyDataSetChanged()
            }
            visibility = View.VISIBLE
        }
    }

    override fun showProgressView() {
        mBinding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressView() {
        mBinding.progressBar.visibility = View.GONE
    }

    override fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}