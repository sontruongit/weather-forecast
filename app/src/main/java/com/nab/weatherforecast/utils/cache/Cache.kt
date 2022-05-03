package com.nab.weatherforecast.utils.cache

interface Cache<K, V> {
    fun set(key: K, value: V)

    fun get(key: K): V?
}