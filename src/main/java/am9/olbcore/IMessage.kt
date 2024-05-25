package am9.olbcore

interface IMessage {
    fun sendPrivateMessage(msg: String)
    fun sendPublicMessage(msg: String)
}