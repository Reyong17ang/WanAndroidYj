package com.example.mod_video

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_common.constant.KEY_VIDEO_PLAY_LIST
import com.example.lib_common.constant.VIDEO_ACTIVITY_PLAYER
import com.example.lib_framework.base.BaseDataBindActivity
import com.example.mod_video.adapter.VideoAdapter
import com.example.mod_video.databinding.ActivityVideoPlayBinding
import com.example.mod_video.view.RotateNoteView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.cache.Cache
import com.xxmm.lib_room.entity.VideoInfo

@Route(path = VIDEO_ACTIVITY_PLAYER)
class VideoPlayActivity : BaseDataBindActivity<ActivityVideoPlayBinding>() {
    //播放位置
    private var mPlayingPosition = 0

    //当前播放URL
    private var mPlayUrl: String? = null

    //自动播放
    private val mStartAutoPlay = true

    //缓存对象
    private lateinit var mCache: Cache

    //ExoPlayer
    private var mExoPlayer: ExoPlayer? = null

    //播放器
    private var mPlayView: StyledPlayerView? = null

    //媒体资源加载工厂类
    private lateinit var mMediaSource: MediaSource.Factory

    //动画view
    private var mRotateNoteView: RotateNoteView? = null

    private lateinit var mAdapter: VideoAdapter

    //需要添加JvmField注解
    @Autowired(name = KEY_VIDEO_PLAY_LIST)
    @JvmField
    var mData: ArrayList<VideoInfo>? = null
    override fun initView(savedInstanceState: Bundle?) {

    }
}