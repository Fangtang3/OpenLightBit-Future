package am9.openLightBit

import am9.olbcore.JavaTodo
import am9.olbcore.UniversalLogger
import org.jetbrains.annotations.NotNull
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class Timer {
    private val executor: ExecutorService = Executors.newCachedThreadPool()
    companion object {
        private val breadFactory: ExecutorService = Executors.newCachedThreadPool()
        private val woodenFish: ExecutorService = Executors.newCachedThreadPool()
        fun genBread() {
            TODO()
        }
        fun gongde() {
            TODO()
        }
    }
}