package org.example;

import org.example.controller.QuotesController;
import org.example.domain.Quotes;
import org.example.file.File;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        File file = new File();

        QuotesController quotesController = new QuotesController();

        file.readJson(); // 명언 리스트 불러오기

        System.out.println("== 명언 앱 ==");
        String command;


        while(true){
            System.out.print("명령) ");
            command = sc.nextLine();

            if (command.equals("등록"))
                quotesController.register();
            else if(command.equals("목록"))
                quotesController.list();
            else if(command.contains("삭제?id=")){
                quotesController.delete(command);
            }
            else if(command.contains("수정?id=")){
                quotesController.update(command);
            }
            else if(command.equals("빌드"))
                file.writeJson();
            else if (command.equals("종료"))
                break;
            else
                System.out.println("잘못된 명령입니다.");
        }

    }
}

