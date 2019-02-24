package com.beeete2.android.examples.model.repository

import com.beeete2.android.examples.model.entity.Friend
import com.beeete2.android.examples.model.entity.Gender

class FriendLocalRepository : FriendRepository {

    override val friends: List<Friend>
        get() = listOf(
            Friend(
                "晋三",
                "安倍",
                "しんぞう",
                "あべ",
                Gender.MALE
            ),
            Friend(
                "佳彦",
                "野田",
                "よしひこ",
                "のだ",
                Gender.MALE
            ),
            Friend(
                "直人",
                "菅",
                "なおと",
                "かん",
                Gender.MALE
            ),
            Friend(
                "由紀夫",
                "鳩山",
                "ゆきお",
                "はとやま",
                Gender.MALE
            ),
            Friend(
                "太郎",
                "麻生",
                "たろう",
                "あそう",
                Gender.MALE
            ),
            Friend(
                "康夫",
                "福田",
                "やすお",
                "ふくだ",
                Gender.MALE
            ),
            Friend(
                "純一郎",
                "小泉",
                "じゅんいちろう",
                "こいずみ",
                Gender.MALE
            ),
            Friend(
                "喜朗",
                "森",
                "よしろう",
                "もり",
                Gender.MALE
            ),
            Friend(
                "恵三",
                "小渕",
                "けいぞう",
                "おぶち",
                Gender.MALE
            ),
            Friend(
                "龍太郎",
                "橋本",
                "りゅうたろう",
                "はしもと",
                Gender.MALE
            )
        )

}