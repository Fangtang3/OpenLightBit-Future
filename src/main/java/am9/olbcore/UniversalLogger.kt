package am9.olbcore

import am9.olbcore.Core

class UniversalLogger {
    companion object {
        private lateinit var colorReset: String
        fun info(msg: String){
            lateinit var colorBlue: String
            if (Core.platform.contains("Minecraft")) {
                colorBlue = "&b"
                colorReset = "&r"
            } else {
                colorBlue = ""
                colorReset = ""
            }
            println("$colorBlue[*] $colorReset$msg")
        }
        fun warn(msg: String){
            lateinit var colorYellow: String
            if (Core.platform.contains("Minecraft")) {
                colorYellow = "&e"
                colorReset = "&r"
            } else {
                colorYellow = ""
                colorReset = ""
            }
            println("$colorYellow[!] $colorReset$msg")
        }
        fun error(msg: String){
            lateinit var colorRed: String
            if (Core.platform.contains("Minecraft")) {
                colorRed = "&c"
                colorReset = "&r"
            } else {
                colorRed = ""
                colorReset = ""
            }
            println("$colorRed[x] $colorReset$msg")
        }
    }
}