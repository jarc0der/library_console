package com.botscrew.library.utils.io;

import java.util.Scanner;

public class Messanger {

    private static Scanner scanner = new Scanner(System.in);

    public static String read(){
        return scanner.nextLine();
    }

    public static void write(String message){
        System.out.println(message);
    }

    public static Integer readInt(){
        return Integer.parseInt(scanner.nextLine());
    }

    public static String askWriteCommandOrData(String message){
        write(message);
        System.out.print(">>");
        return read();
    }

    public static int askChooseNumber(String message) {
        try{
            write(message);
            System.out.print(">>");
            return readInt();
        }catch (RuntimeException e){
            throw new IllegalArgumentException("Incorrect number");
        }


    }
}
