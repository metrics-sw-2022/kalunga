package com.jhonnatan.kalunga.data.user.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.w3c.dom.DocumentType
import java.util.*

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.source.local.entities
 * Created by Jhonnatan E. Zamudio P. on 7/07/2021 at 8:47 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

@Parcelize
@Entity
data class UserLocal (
    @PrimaryKey(autoGenerate = true) var id: Int,
    var account: String,
    var sessionState: Boolean,
    var typeUser: Int,
    var email: String,
    var fullName: String,
    var documentType: Int,
    var documentNumber: String,
    var phoneNumber: String,
    var country: String,
    var city: String
): Parcelable