package am9.olbcore

class BreadException(val msg: String) : Throwable() {
    val errorMessage = msg
    fun getType(): String {
        if (errorMessage == "仓库满了！") {
            return "full"
        } else if (errorMessage == "没有那么多面包！") {
            return "empty"
        } else {
            return "unknown"
        }
    }
}