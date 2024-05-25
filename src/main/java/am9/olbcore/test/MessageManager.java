package am9.olbcore.test;

import am9.olbcore.IMessage;
import org.jetbrains.annotations.NotNull;

public class MessageManager implements IMessage {
    //示例
    @Override
    public void sendMessage(@NotNull String message) {
        System.out.println(message);
    }
    //Kotlin
    //override fun sendMessage(message: String) {
    //    println(message)
    //}
}
