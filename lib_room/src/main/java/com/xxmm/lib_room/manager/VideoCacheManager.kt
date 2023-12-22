package com.xxmm.lib_room.manager

import androidx.lifecycle.LiveData
import com.xxmm.lib_room.database.SumDataBase
import com.xxmm.lib_room.entity.VideoInfo
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

object VideoCacheManager {
    private val videoDao by lazy { SumDataBase.getInstance().videoListDao() }
    suspend fun saveVideoList(list: MutableList<VideoInfo>) {
        videoDao.insertAll(list)
    }

    fun insertVideoInfo(videoInfo: VideoInfo) {
        MainScope().launch {
            videoDao.insert(videoInfo)
        }
    }

    fun deleteVideoItem(id: Long) {
        MainScope().launch {
            videoDao.deleteById(id)
        }
    }

    fun deleteVideoItem(videoInfo: VideoInfo) {
        MainScope().launch {
            videoDao.delete(videoInfo)
        }
    }

    fun updateVideoItem(videoInfo: VideoInfo) {
        MainScope().launch {
            videoDao.update(videoInfo)
        }
    }

    fun updateVideoItem(id: Long, title: String) {
        MainScope().launch {
            videoDao.updateById(id, title)
        }
    }

    fun getVideoItem(id: Long): VideoInfo? {
        return videoDao.query(id)
    }


    fun getVideoList(): MutableList<VideoInfo>? {
        return videoDao.queryAll()
    }


    fun getVideoListLiveData(): LiveData<List<VideoInfo>> {
        return videoDao.queryAllLiveData()
    }


    fun clearVideoList(callBack: (String) -> Unit) {
        MainScope().launch {
            videoDao.deleteAll()
            callBack("删除成功")
        }
    }
}