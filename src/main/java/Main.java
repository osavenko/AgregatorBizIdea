import command.ReciveFromGoogle;
import ex.GoogleParseException;
import model.GoogleSearch;
import model.SearchResults;
import model.SearchSite;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int command = 0;
        while (command<10){
            Helper.writeToConsole("1. Поиск в Google.");
            Helper.writeToConsole("10. Выход.");
            Helper.writeToConsole("Веедите команду ->");
            try {
                command = Helper.readInt();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (command==1){
                int depth = 0;
                String query = "";
                Helper.writeToConsole("Введите строку запроса: ");
                try {
                    query = Helper.readString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Helper.writeToConsole("Введите глубину просмотра: ");
                    depth = Helper.readInt();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ReciveFromGoogle reciveFromGoogle = new ReciveFromGoogle(query, depth);
                reciveFromGoogle.execute();
            }
        }
    }
}
