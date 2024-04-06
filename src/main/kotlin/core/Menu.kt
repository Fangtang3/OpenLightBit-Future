package am9.openLightBit.core

class Menu {
    companion object {
        fun getMenu(): String {
            return "Welcome to OpenLightBit!\n" +
                    "状态：运行中\n" +
                    "/olb menu：查看菜单" +
                    "/olb version：查询版本\n"
        }
    }
}