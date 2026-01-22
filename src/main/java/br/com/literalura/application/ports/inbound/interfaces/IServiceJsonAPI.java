package br.com.literalura.application.ports.inbound.interfaces;

import br.com.literalura.application.communication.response.ApiResponse;
import br.com.literalura.domain.entities.Author;
import br.com.literalura.domain.entities.Book;

import java.util.List;

public interface IServiceJsonAPI {
    String getDataOfAPI(String address);
    <T> T convertInObject(Class<T> classT, String json);
    Book convertObjInEntity(ApiResponse res);
    List<Author> convertObjInAuthors(ApiResponse res);
    List<Book> convertObjInListEntity(String json);

}
