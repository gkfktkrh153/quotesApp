package org.example.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.Quotes;
import org.example.service.QuotesService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class QuotesController {
    private int id = 1;
    private final Scanner sc;
    private final ObjectMapper objectMapper;
    private QuotesService quotesService;

    public QuotesController() {
        objectMapper = new ObjectMapper();
        sc = new Scanner(System.in);
        if (quotesService == null)
            quotesService = new QuotesService();
    }

    public void register() {

        System.out.print("명언 : ");
        String name = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        quotesService.register(new Quotes(id++, name, author));

        System.out.println(id + "번 명언이 등록되었습니다.");

    }

    public void list() {
        String quotesList = quotesService.getList();
        System.out.println(quotesList);
    }

    public void delete(String command) {
        String[] split = command.split("=");
        int deleteId = Integer.parseInt(split[1]);

        quotesService.delete(deleteId);

    }

    public void update(String command) {
        String[] split = command.split("=");
        int updateId = Integer.parseInt(split[1]);

        quotesService.update(updateId);



    }
}
