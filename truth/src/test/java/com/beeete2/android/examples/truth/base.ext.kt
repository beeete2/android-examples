package com.beeete2.android.examples.truth

import kotlin.reflect.KProperty1

/**
 * Taken from https://taro.hatenablog.jp/entry/2015/02/24/215413
 */
fun <A, B> KProperty1<A, B>.toFunction(): (A?) -> B? = { if (it == null) null else get(it) }
