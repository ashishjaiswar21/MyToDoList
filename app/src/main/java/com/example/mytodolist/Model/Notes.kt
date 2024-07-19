package com.example.mytodolist.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// doing parcellize to get data transfer throughout fregments
@Parcelize
// I have write textfields that will be used for data transaction
@Entity(tableName = "Notes")
class Notes (
    @PrimaryKey(autoGenerate = true)
    var id:Int? =null,
    var title:String,
    var subTitle:String,
    var notes:String,
    var date:String,
    var priority:String

): Parcelable