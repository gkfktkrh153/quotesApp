package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        HashMap<Integer, Quotes> quotesList = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("== 명언 앱 ==");
        String s;
        int num = 1;
        App app = new App();

        do {
            System.out.print("명언) ");
            s = sc.nextLine();

            if (s.equals("등록")) {
                Quotes quotes = app.register(num);
                quotesList.put(num++, quotes);
            }
            else if(s.equals("목록")) {
                quotesList.forEach((key, q) -> {
                    System.out.println(key + " / " + q.getAuthor() + " / " + q.getName());
                });
            }
            else if(s.contains("삭제?id=")){
                String[] split = s.split("=");
                int deleteId = Integer.parseInt(split[1]);
                quotesList.remove(deleteId);
            }




        }
        while(!s.equals("종료"));


    }
}
class App {
    private final Scanner sc;

    App() {
        sc = new Scanner(System.in);
    }
    public Quotes register(int num){

        System.out.print("명언 : ");
        String name = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        System.out.println(num + "번 명언이 등록되었습니다.");
        return new Quotes(name, author);
    }
    public void list(){

    }

}
class Quotes{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private String name;
    private String author;

    public Quotes(String name, String author) {
        this.name = name;
        this.author = author;
    }
}