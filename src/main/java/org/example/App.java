package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class App {
    private final Scanner sc;
    private final ObjectMapper objectMapper;
    App() {
        objectMapper = new ObjectMapper();
        sc = new Scanner(System.in);
    }

    public Quotes register(List<Quotes> quotesList, int num) {

        System.out.print("명언 : ");
        String name = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        quotesList.add(new Quotes(num, name, author));

        System.out.println(num + "번 명언이 등록되었습니다.");
        return new Quotes(num, name, author);
    }

    public void list(List<Quotes> quotesList) {
        System.out.println(quotesList.stream().
                map(Quotes::toString).
                collect(Collectors.joining("\n")));
    }

    public void update(Quotes updateObject) {

    }

    public void readJson(List<Quotes> quotesList) throws IOException {
        try {
            FileReader reader = new FileReader("C:/codeLion/project/quotesApp/quotesApp/quotes.json");
            quotesList = objectMapper.readValue(reader, new TypeReference<List<Quotes>>() {
            });
            reader.close();


        }
        catch (FileNotFoundException e){
            FileWriter file = new FileWriter("C:/codeLion/project/quotesApp/quotesApp/data.json");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeJson(List<Quotes> quotesList) {
        try {
            FileWriter file = new FileWriter("C:/codeLion/project/quotesApp/quotesApp/data.json");
            file.write(objectMapper.writeValueAsString(quotesList));
            System.out.println("data.json파일의 내용이 갱신되었습니다.");
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void delete(List<Quotes> quotesList, int deleteId) {
        try{
            Quotes remove = quotesList.get(deleteId - 1);
            quotesList.remove(remove);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
        }
    }

    public void update(List<Quotes> quotesList, int updateId) {
        Quotes updateObject;

        try {
            updateObject = quotesList.get(updateId - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(updateId + "번 명언은 존재하지 않습니다.");
            return;
        }

        System.out.println("명언(기존) : " + updateObject.getName());
        System.out.print("명언 : ");
        updateObject.setName(sc.nextLine());

        System.out.println("작가(기존) : " + updateObject.getAuthor());
        System.out.print("작가 : ");
        updateObject.setAuthor(sc.nextLine());
    }
}
