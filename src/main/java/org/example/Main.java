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


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        List<Quotes> quotesList = new ArrayList<>();
        App app = new App();


        app.readJson(quotesList); // 명언 리스트 불러오기

        System.out.println("== 명언 앱 ==");
        String s;
        int num = 1;


        while(true){
            System.out.print("명령) ");
            s = sc.nextLine();

            if (s.equals("등록"))
                app.register(quotesList,num++);
            else if(s.equals("목록"))
                app.list(quotesList);
            else if(s.contains("삭제?id=")){
                String[] split = s.split("=");
                int deleteId = Integer.parseInt(split[1]);

                app.delete(quotesList, deleteId);
            }
            else if(s.contains("수정?id=")){
                String[] split = s.split("=");
                int updateId = Integer.parseInt(split[1]);

                app.update(quotesList, updateId);
            }
            else if(s.equals("빌드"))
                app.writeJson(quotesList);
            else if (s.equals("종료"))
                break;
            else
                System.out.println("잘못된 명령입니다.");
        }

    }
}

