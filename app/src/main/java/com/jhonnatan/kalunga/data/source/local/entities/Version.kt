package com.jhonnatan.kalunga.data.source.local.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.source.local.entities
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 11:01 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

@Parcelize
@Entity
data class Version (
    @PrimaryKey(autoGenerate = true) var id: Int,
    var versionCode: Int,
    var versionName: String,
    var versionDate: Date
): Parcelable