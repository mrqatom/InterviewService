package com.example.interviewservice.utils

import android.util.Log
import com.example.interviewservice.MainActivity
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * @author qiuyunfei
 * @date 2021/3/17 0017
 * @description
 */

/**
 * 除去空格换行符等
 */
fun String.replaceBlank():String{
    val p: Pattern = Pattern.compile("\\s*|\t|\r|\n")
    val m: Matcher = p.matcher(this)
    return m.replaceAll("")
}