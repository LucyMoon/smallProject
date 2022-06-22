package kr.hs.dgsw.historyproject

import java.io.Serializable

data class Rcv_Item(
    val Img: String,
    val Title: String,
    val Info: String
) : Serializable
