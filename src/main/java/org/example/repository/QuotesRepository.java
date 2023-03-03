package org.example.repository;

import org.example.domain.Quotes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuotesRepository {
    public static final List<Quotes> quotesList = new ArrayList<>();
    public QuotesRepository(){
    }

    public static List<Quotes> getQuotesList(){
        return quotesList;
    }
    public void load(List<Quotes> list){
        List<Quotes> collect = list.stream().collect(Collectors.toList());
        for(Quotes quotes : collect){
            quotesList.add(quotes);
        }
    }

    public void save(Quotes quotes) {
        quotesList.add(quotes);
    }
    public List<Quotes> findAll(){
        return quotesList;
    }

    public void delete(int deleteId) {
        try{
            Quotes remove = quotesList.get(deleteId - 1);
            quotesList.remove(remove);
            System.out.println("성공적으로 삭제되었습니다.");
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
        }


    }

    public Quotes findById(int updateId) {
        Quotes quotes = null;
        try {
            quotes = quotesList.get(updateId - 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        return quotes;
    }
}
