// В файле содержится строка с исходными данными в такой форме:
// {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
// Требуется на её основе построить и вывести на экран новую строку, в форме SQL запроса:
// SELECT * FROM students WHERE name = "Ivanov" AND country = "Russia" AND city = "Moscow";
// Для разбора строки используйте String.split. 
//Сформируйте новую строку, используя StringBuilder. Значения null не включаются в запрос.

package hw2;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class task1 {
    public static void main(String[] args) {
        String str = strRead();
        str = str.substring(1, str.length() - 1);
        String[] string = str.split(":");
        str = Arrays.toString(string);
        str = str.replace("[", "");
        str = str.replace("]", "");
        string = str.split(",");
        String[] SecString = delNull(string);
        String Newstr = Formater(SecString);
        System.out.print("SELECT * FROM students WHERE  ");
        System.out.println(Newstr);
    }

    private static String strRead() {
        StringBuilder str = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("hw2/students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null)
                str.append(line);
        } catch (IOException ex) {
            System.out.println("Ошибка чтения с файла." + ex.getMessage());
        }
        return str.toString();
    }

    private static String[] delNull(String[] string) {
        String[] firstString = new String[string.length];
        int count = 0;
        for (int i = 0; i < string.length; i++) {
            if (string[i].contains("null")) {
                firstString[i] = "*";
                firstString[i - 1] = "*";
                count = count + 2;
            } else
                firstString[i] = string[i];
        }
        // System.out.println(Arrays.toString(NewString));
        String[] SecString = new String[firstString.length - count];
        for (int i = 0, j = 0; i < firstString.length; i++) {
            if (firstString[i].contains("*"))
                i++;
            else {
                SecString[j] = firstString[i];
                j++;
            }
        }
        return SecString;
    }

    private static String Formater(String[] SecString) {
        StringBuilder Newstr = new StringBuilder();
        String str;
        Newstr.append(SecString[0]);
        for (int i = 1; i < SecString.length; i++) {
            if (i % 2 == 0) {
                Newstr.append(SecString[i]);
            } else {
                str = SecString[i];
                if (i == SecString.length - 1)
                    str = " = " + str.substring(2, str.length() - 1);
                else
                    str = " = " + str.substring(2, str.length() - 1) + "  AND ";
                Newstr.append(str);
            }
        }
        return Newstr.toString();
    }

}
