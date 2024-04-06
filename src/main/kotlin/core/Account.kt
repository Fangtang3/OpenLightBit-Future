package am9.openLightBit.core

import User
import java.io.File
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class Account {
    //为非 QQ 平台用户对应 “QQ 号”
    companion object {
        fun init(jsonLocation: String) {
            val accountJson: File = File("$jsonLocation/account.json")
            if (!accountJson.exists()) {
                accountJson.createNewFile()
            }
        }
        fun register(username: String) {
            TODO()
        }
    }
}