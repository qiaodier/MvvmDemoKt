package com.mvvm.kt.entity

/**
 *@author qiaohao
 *@date 21-4-7 下午1:58
 */
data class New constructor(
    private var sid:String,
    private var text:String,
    private var thumbnail:String,
    private var video:String,
    private var images:String?,
    private var up:String,
    private var down:String,
    private var forward:String,
    private var comment:String,
    private var uid:String,
    private var name:String,
    private var header:String,
    private var top_comments_content:String,
    private var top_comments_voiceuri:String,
    private var top_comments_uid:String,
    private var top_comments_name:String,
    private var top_comments_header:String,
    private var passtime:String
) {

}