package am9.olbcore

import am9.olbcore.Runner.Companion
import groovy.lang.GroovyShell

class Script {
    companion object {
        lateinit var groovyShell: GroovyShell
        lateinit var messageListener: List<String>
        fun init() {
            groovyShell = GroovyShell()
        }
        fun runAnotherScript(script: String, args: Array<out String>) {
            groovyShell.evaluate(script)
        }
        fun make(dir: String){
            if (System.getProperty("os.name").contains("Windows")) {
                //run("nmake -C $dir")
                throw RuntimeException("目前不支持make！")
            } else {
                if (System.getProperty("os.name").contains("Linux")) {
                    try {
                        Runner.run("make -C $dir")
                    } catch (e: RuntimeException) {
                        Runner.run("gmake -C $dir")
                    }
                } else {
                    try {
                        Runner.run("gmake -C $dir")
                    } catch (e: RuntimeException) {
                        Runner.run("gnumake -C $dir")
                    }
                }
            }
        }
        fun makeWithConfigure(dir: String, configure: String) {
            if (!System.getProperty("os.name").contains("Windows")) {
                TODO()
            }
        }

    }
}