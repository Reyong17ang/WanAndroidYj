package com.xxmm.lib_room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.lib_common.constant.TABLE_VIDEO_LIST
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_VIDEO_LIST)
data class VideoInfo(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title", defaultValue = "")
    val title: String?,
    @ColumnInfo(name = "desc")
    val desc: String?,
    @ColumnInfo(name = "authorName")
    val authorName: String?,
    @ColumnInfo(name = "playUrl")
    val playUrl: String?,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
    //部分字段不想映射到数据库表中，添加Ignore注解忽略，就不会成为数据库表中的类名
    @Ignore
    var collectionCount: String?
) : Parcelable {
    constructor() : this(0, "", "", "", "", "", "")
}