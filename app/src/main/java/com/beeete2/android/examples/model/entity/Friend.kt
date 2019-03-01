package com.beeete2.android.examples.model.entity

data class Friend(
    val firstName: String = "",
    val lastName: String = "",
    val kanaFirstName: String = "",
    val kanaLastName: String = "",
    val gender: Gender = Gender.UNKNOWN,
    val memo: String = ""
)


enum class FriendFormat {
    LAST_NAME,
    FIRST_NAME,
    KANA_LAST_NAME,
    KANA_FIRST_NAME,
    FULL_NAME,
    FULL_KANA
}
