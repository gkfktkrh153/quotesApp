package org.example.controller;

import org.example.domain.Quotes;
import org.example.repository.QuotesRepository;
import org.example.service.QuotesService;
import java.util.List;
import java.util.Scanner;

import static org.example.repository.QuotesRepository.quotesList;


public class QuotesController {
    private int id = 1;
    private Scanner sc;
    private QuotesService quotesService;
    private QuotesRepository quotesRepository;
    public QuotesController() {
        sc = new Scanner(System.in);
        quotesService = new QuotesService();

        if (quotesList.size() > 0){
            Quotes quotes = quotesList.get(quotesList.size() - 1);
            id = quotes.getId()+1;
        }
    }

    public void register() {

        System.out.print("명언 : ");
        String name = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        quotesService.register(new Quotes(id, name, author));

        System.out.println(id++ + "번 명언이 등록되었습니다.");

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
