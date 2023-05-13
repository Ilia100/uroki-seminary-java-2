//Реализуйте алгоритм сортировки пузырьком числового массива,
// результат после каждой итерации запишите в лог-файл.

package hw2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class task2 {
public static void main(String[] args) {
    String str;
    int [] numbers = myScanner();
    numbers=Sorter(numbers);
    System.out.println(str=strRead());
}
    
private static int[] Sorter(int[] numbers) {
    int temp;
    String str="";
    writeToFile(Arrays.toString(numbers));
    for (int j = 0; j < numbers.length; j++) {
        for (int i=1; i<numbers.length; i++)  {
            if (numbers[i]<numbers[i-1]){
                temp=numbers[i];
                numbers[i]=numbers[i-1];
                numbers[i-1]=temp;
                str=str+"Числа " + numbers[i-1]+" и " +numbers[i]+" переставлены местами, ";
            }
        }
        writeToFile(str);
        writeToFile(Arrays.toString(numbers));
        str="";
        
    }

    return numbers;
}


private static int[] myScanner() {
    Scanner scanner = new Scanner (System.in, "CP866");
    int []numbers=new int[5];
    System.out.println("Введите "+ numbers.length + " чисел в массив: ");
    for (int i=0; i<numbers.length; i++)  numbers[i] = scanner.nextInt ();
    scanner.close ();
    return numbers;
}

private static void writeToFile(String text) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("hw2/log.txt", true))){
        writer.write(text);
        writer.newLine();
    }
    catch(IOException ex){
        System.out.println("Ошибка записи в файл."+ex.getMessage());
    }
}

private static String strRead() {
    StringBuilder str = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader("hw2/log.txt"))) {
        String line;
        while ((line = reader.readLine()) != null)
            str.append(line);
    } catch (IOException ex) {
        System.out.println("Ошибка чтения с файла." + ex.getMessage());
    }
    return str.toString();
}
}