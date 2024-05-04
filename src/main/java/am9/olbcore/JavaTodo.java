package am9.olbcore;

import kotlin.NotImplementedError;

public class JavaTodo {
    public static void TODO() throws NotImplementedError {
        throw new NotImplementedError();
    }
    public static void TODO(String reason) throws NotImplementedError {
        throw new NotImplementedError(reason);
    }
}
