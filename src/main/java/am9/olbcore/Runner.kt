package am9.olbcore

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.concurrent.locks.ReentrantLock
import groovy.lang.GroovyShell

class Runner {
    companion object {
        private val lock = ReentrantLock()
        lateinit var process: Process
            private set

        fun run(command: String) {
            if (process.isAlive) {
                throw IllegalStateException("进程未结束！")
            } else {
                lock.lock()
                try {
                    process = Runtime.getRuntime().exec(command)
                } catch (e: Exception) {
                    throw RuntimeException("运行命令失败！", e)
                } finally {
                    lock.unlock()
                }
            }
        }

        fun runJavaClass(cls: String) {
            run("java \"$cls\"")
        }

        fun runJarWithMemory(jar: String, xms: Int, xmx: Int, xmn: Int) {
            run("java -Xmx${xmx}m -Xms${xms}m -Xmn${xmn}m -jar \"$jar\"")
        }

        fun getLog(): String {
            lock.lock()
            try {
                return process.inputStream.use { input ->
                    BufferedReader(InputStreamReader(input)).use { reader ->
                        StringBuilder().apply {
                            var line: String?
                            while (reader.readLine().also { line = it } != null) {
                                append(line).append("\n")
                            }
                        }.toString()
                    }
                }
            } catch (e: Exception) {
                UniversalLogger.error("获取日志失败！")
                return "获取日志失败！"
            } finally {
                lock.unlock()
            }
        }
    }
}
