package com.xxmm.lib_room.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lib_common.constant.TABLE_VIDEO_LIST
import com.xxmm.lib_room.entity.VideoInfo

interface VideoListCacheDao {
    @Insert(entity = VideoInfo::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(videoInfo: VideoInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(videoList: MutableList<VideoInfo>)

    @Query("DELETE FROM $TABLE_VIDEO_LIST WHERE id=:id")
    fun deleteById(id: Long)

    @Delete
    fun delete(videoInfo: VideoInfo): Int

    @Query("DELETE FROM $TABLE_VIDEO_LIST")
    suspend fun deleteAll()

    @Update
    fun update(videoInfo: VideoInfo): Int

    @Query("UPDATE $TABLE_VIDEO_LIST SET title=:title WHERE id=:id")
    fun updateById(id: Long, title: String)

    @Query("SELECT * FROM $TABLE_VIDEO_LIST")
    fun queryAll(): MutableList<VideoInfo>?

    @Query("SELECT * FROM $TABLE_VIDEO_LIST WHERE id=:id")
    fun query(id: Long): VideoInfo?

    @Query("SELECT * FROM $TABLE_VIDEO_LIST")
    fun queryAllLiveData(): LiveData<List<VideoInfo>>
}