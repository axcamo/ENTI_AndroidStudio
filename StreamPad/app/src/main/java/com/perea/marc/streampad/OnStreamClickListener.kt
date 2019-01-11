package com.perea.marc.streampad

interface OnStreamClickListener<T> {
    fun onItemClick(item: T, position: Int)
}