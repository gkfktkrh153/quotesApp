package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.domain.Quotes;
import org.example.repository.QuotesRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class QuotesService {

    private final QuotesRepository quotesRepository;
    private final Scanner sc;
    public QuotesService(){
        quotesRepository = new QuotesRepository();
        sc = new Scanner(System.in);
    }

    public void register(Quotes quotes) {
        quotesRepository.save(quotes);

    }
    public void delete(int deleteId) {
        quotesRepository.delete(deleteId);
    }

    public void update(int updateId) {
        Quotes updateObject = quotesRepository.findById(updateId);

        if(!(updateObject == null))
        {
            System.out.println("명언(기존) : " + updateObject.getName());
            System.out.print("명언 : ");
            updateObject.setName(sc.nextLine());

            System.out.println("작가(기존) : " + updateObject.getAuthor());
            System.out.print("작가 : ");
            updateObject.setAuthor(sc.nextLine());
        }
    }

    public String getList() {
        List<Quotes> quotesList = quotesRepository.findAll();
        return quotesList.stream().
                map(Quotes::toString).
                collect(Collectors.joining("\n"));
    }




}
