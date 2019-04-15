package com.beeete2.android.examples.model.repository

import com.beeete2.android.examples.model.entity.Friend
import com.beeete2.android.examples.model.entity.Gender

class FriendLocalRepository : FriendRepository {

    override val friends: List<Friend>
        get() = listOf(
            Friend(
                1,
                "晋三",
                "安倍",
                "しんぞう",
                "あべ",
                Gender.MALE,
                "1行目"
            ),
            Friend(
                2,
                "佳彦",
                "野田",
                "よしひこ",
                "のだ",
                Gender.MALE,
                "1行目\n2行目"
            ),
            Friend(
                3,
                "直人",
                "菅",
                "なおと",
                "かん",
                Gender.MALE,
                "1行目\n2行目\n3行目"
            ),
            Friend(
                4,
                "由紀夫",
                "鳩山",
                "ゆきお",
                "はとやま",
                Gender.MALE
            ),
            Friend(
                5,
                "太郎",
                "麻生",
                "たろう",
                "あそう",
                Gender.MALE
            ),
            Friend(
                6,
                "康夫",
                "福田",
                "やすお",
                "ふくだ",
                Gender.MALE
            ),
            Friend(
                7,
                "純一郎",
                "小泉",
                "じゅんいちろう",
                "こいずみ",
                Gender.MALE
            ),
            Friend(
                8,
                "喜朗",
                "森",
                "よしろう",
                "もり",
                Gender.MALE
            ),
            Friend(
                9,
                "恵三",
                "小渕",
                "けいぞう",
                "おぶち",
                Gender.MALE
            ),
            Friend(
                10,
                "龍太郎",
                "橋本",
                "りゅうたろう",
                "はしもと",
                Gender.MALE
            )
        )



}