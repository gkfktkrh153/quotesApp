package org.example.file;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.example.domain.Quotes;
import org.example.repository.QuotesRepository;
import org.example.service.QuotesService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class File {
    private final ObjectMapper objectMapper;
    private QuotesService quotesService;
    private QuotesRepository quotesRepository;

    public File(){
        quotesService = new QuotesService();
        objectMapper = new ObjectMapper();
        quotesRepository = new QuotesRepository();

    }
    public void readJson() throws IOException {

        try {
            FileReader reader = new FileReader("C:/codeLion/project/quotesApp/quotesApp/data.json");
            List<Quotes> quotesList = objectMapper.readValue(reader, new TypeReference<List<Quotes>>() {});
            reader.close();
            quotesRepository.load(quotesList);
        }
        catch (FileNotFoundException e){
            FileWriter file = new FileWriter("C:/codeLion/project/quotesApp/quotesApp/data.json");
        }
        catch (MismatchedInputException e)
        {
            FileWriter file = new FileWriter("C:/codeLion/project/quotesApp/quotesApp/data.json");
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void writeJson() {
        try {
            FileWriter file = new FileWriter("C:/codeLion/project/quotesApp/quotesApp/data.json");
            file.write(objectMapper.writeValueAsString(quotesService.getList()));
            System.out.println("data.json파일의 내용이 갱신되었습니다.");
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
