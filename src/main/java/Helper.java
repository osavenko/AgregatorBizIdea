import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Helper {
    public static void writeToConsole(String message){
        System.out.println(message);
    }
    public static String readString() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedReader.readLine();
    }
    public static int readInt() throws IOException {
        return Integer.parseInt(readString());
    }
}
