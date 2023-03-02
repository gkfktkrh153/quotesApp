package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {


    public static void main(String[] args) throws JsonProcessingException {
        List<Quotes> quotesList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();


        try {
            FileReader reader = new FileReader("C:/codeLion/project/quotesApp/quotesApp/quotes.json");
            quotesList = objectMapper.readValue(reader, new TypeReference<List<Quotes>>() {
            });
            reader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


        Scanner sc = new Scanner(System.in);
        System.out.println("== 명언 앱 ==");
        String s;
        int num = 1;
        App app = new App();

        while(true){
            System.out.print("명령) ");
            s = sc.nextLine();

            if (s.equals("등록")) {
                Quotes quotes = app.register(num++);
                quotesList.add(quotes);
            }
            else if(s.equals("목록")) {
                System.out.println(quotesList.stream().
                        map(Quotes::toString).
                        collect(Collectors.joining("\n")));
            }
            else if(s.contains("삭제?id=")){
                String[] split = s.split("=");
                int deleteId = Integer.parseInt(split[1]);
                try{
                    Quotes remove = quotesList.get(deleteId - 1);
                    quotesList.remove(remove);
                }
                catch (IndexOutOfBoundsException e)
                {
                    System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
                }
            }
            else if(s.contains("수정?id=")){
                String[] split = s.split("=");
                int updateId = Integer.parseInt(split[1]);
                try{
                    Quotes updateObject = quotesList.get(updateId - 1);
                    app.update(updateObject);
                }
                catch (IndexOutOfBoundsException e)
                {
                    System.out.println(updateId + "번 명언은 존재하지 않습니다.");
                }

            } else if (s.equals("종료"))
                break;
            else
                System.out.println("잘못된 명령입니다.");
        }
        String s1 = objectMapper.writeValueAsString(quotesList);
        System.out.println(s1);

        try {
            FileWriter file = new FileWriter("C:/codeLion/project/quotesApp/quotesApp/quotes.json");
            file.write(s1);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



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
        return new Quotes(num, name, author);
    }
    public void list(){

    }

    public void update(Quotes updateObject) {
        System.out.println("명언(기존) : " + updateObject.getName());
        System.out.print("명언 : ");
        updateObject.setName(sc.nextLine());

        System.out.println("작가(기존) : " + updateObject.getAuthor());
        System.out.print("작가 : ");
        updateObject.setAuthor(sc.nextLine());
    }
}
class Quotes {
    private int id;
    private String name;
    private String author;
    Quotes(){

    }

    public Quotes(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return id + " / " + name + " / " + author;
    }
}