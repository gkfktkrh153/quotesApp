package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 명언 앱 ==");
        String s;
        int num = 0;
        Quotes quotes = new Quotes();
        do {
            System.out.print("명언) ");
            s = sc.nextLine();

            if (s.equals("등록")) {
                quotes.register();
            }

        }
        while(!s.equals("종료"));


    }
}
class Quotes{
    private String name;
    private String author;
    private int num;
    private final Scanner sc;
    Quotes(){
        sc = new Scanner(System.in);
    }
    public void register(){

        System.out.print("명언 : ");
        name = sc.nextLine();
        System.out.print("작가 : ");
        author = sc.nextLine();

        System.out.println(num + "번 명언이 등록되었습니다.");
    }
}