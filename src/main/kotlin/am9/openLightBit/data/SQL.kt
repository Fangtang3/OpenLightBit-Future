package am9.openLightBit.data

import java.sql.Connection

class SQL {
    companion object {
        lateinit var c: Connection
        fun main() {
            am9.olbcore.Database.c = c
            am9.olbcore.Database.init()
        }
    }
}

