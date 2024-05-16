package am9.olbcore.test;

import am9.olbcore.IMessage;

public class MessageManager implements IMessage {
    //示例
    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
    //Kotlin
    //override fun sendMessage(message: String) {
    //    println(message)
    //}
}
