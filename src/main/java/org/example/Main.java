package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 명언 앱 ==");
        String s;
        do {
            System.out.print("명언) ");
            s = sc.nextLine();


        }
        while(!s.equals("종료"));


    }
}