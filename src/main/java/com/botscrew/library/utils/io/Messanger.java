package com.botscrew.library.utils.io;

import java.util.Scanner;

public class Messanger {

    public static Scanner scanner = new Scanner(System.in);

    public static String read(){
        return scanner.nextLine();
    }

    public static void write(String message){
        System.out.println(message);
    }

}
