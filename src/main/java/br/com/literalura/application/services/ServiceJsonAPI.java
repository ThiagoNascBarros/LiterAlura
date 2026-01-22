package br.com.literalura.application.services;

import br.com.literalura.application.communication.response.ApiResponse;
import br.com.literalura.application.ports.inbound.interfaces.IServiceJsonAPI;
import br.com.literalura.application.ports.inbound.repository.IBookRepository;
import br.com.literalura.domain.entities.Book;
import br.com.literalura.domain.exceptions.ErrorOnConsumerAPI;
import br.com.literalura.domain.exceptions.ErrorOnConvertJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Log4j2
@Service
public class ServiceJsonAPI implements IServiceJsonAPI {

    private final ObjectMapper mapper = new ObjectMapper();
    private final IBookRepository bookRepository;

    public ServiceJsonAPI(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public String getDataOfAPI(String address) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .build();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ErrorOnConsumerAPI(e.getMessage());
        }

        return response.body();
    }

    @Override
    public <T> T convertInObject(Class<T> classT, String json) {
        try {
            return mapper.readValue(json, classT);
        } catch (JsonProcessingException e) {
            throw new ErrorOnConvertJson(e.getMessage());
        }
    }

    @Override
    public Book convertObjInEntity(ApiResponse res) {
        return new Book(res);
    }

    @Override
    public List<Book> convertObjInListEntity(String json) {
        try {
            List<Book> books = Collections.singletonList(mapper.readValue(json, Book.class));
            return books;
        } catch (JsonProcessingException e) {
            throw new ErrorOnConvertJson(e.getMessage());
        }
    }

}
