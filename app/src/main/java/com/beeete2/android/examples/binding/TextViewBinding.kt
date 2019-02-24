package com.beeete2.android.examples.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.beeete2.android.examples.model.entity.Friend
import com.beeete2.android.examples.model.entity.FriendFormat
import com.beeete2.android.examples.model.entity.Gender

@BindingAdapter(value = ["android:text", "format"])
fun TextView.setFriendText(friend: Friend, format: FriendFormat) {
    text = when(format) {
        FriendFormat.FIRST_NAME -> friend.firstName
        FriendFormat.LAST_NAME -> friend.lastName
        FriendFormat.KANA_FIRST_NAME -> friend.kanaFirstName
        FriendFormat.KANA_LAST_NAME -> friend.kanaLastName
        FriendFormat.FULL_NAME -> "${friend.lastName} ${friend.firstName}"
        FriendFormat.FULL_KANA -> "${friend.kanaLastName} ${friend.kanaFirstName}"
    }
}

@BindingAdapter(value = ["android:text"])
fun TextView.setGenderText(gender: Gender) {
    text = when (gender) {
        Gender.MALE -> "男"
        Gender.FEMALE -> "女"
        Gender.UNKNOWN -> "不明"
    }
}
