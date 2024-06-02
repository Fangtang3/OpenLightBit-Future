package am9.olbcore

import am9.openLightBit.Main
import am9.openLightBit.Core

class Info {
    companion object {
        fun getInfo(): String {
            return "OpenLightBit 9 " + Main.version +
                        "\n运行系统：" + System.getProperty("os.name") + " " + System.getProperty("os.version") +
                        "\n系统架构：" + System.getProperty("os.arch") +
                        "\nJRE版本" + System.getProperty("java.version") +
                        "\n运行平台：" + Core.platform
        }
    }
}