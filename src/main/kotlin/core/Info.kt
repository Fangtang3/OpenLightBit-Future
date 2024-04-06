package am9.openLightBit.core

import am9.openLightBit.Main

class Info {
    companion object {
        fun getInfo(): String {
            return "OpenLightBit 9 " + Main.version +
                        "\n运行系统：" + System.getProperty("os.name") + " " + System.getProperty("os.version") +
                        "\n系统架构：" + System.getProperty("os.arch") +
                        "\nJRE版本" + System.getProperty("java.version") +
                        "\n运行平台：" + Main.platform
        }
    }
}