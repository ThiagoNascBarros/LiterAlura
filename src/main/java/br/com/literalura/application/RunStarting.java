package br.com.literalura.application;

import br.com.literalura.application.communication.response.ApiResponse;
import br.com.literalura.application.ports.inbound.repository.IAuthorRepository;
import br.com.literalura.application.ports.inbound.repository.IBookRepository;
import br.com.literalura.application.services.ServiceJsonAPI;
import lombok.extern.log4j.Log4j2;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Log4j2
public class RunStarting {

    private final Scanner input = new Scanner(System.in);
    private final IBookRepository bookRepository;
    private final IAuthorRepository authorRepository;
    private final ServiceJsonAPI serviceJsonAPI;

    public RunStarting(IBookRepository bookRepository, IAuthorRepository authorRepository, ServiceJsonAPI serviceJsonAPI) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.serviceJsonAPI = serviceJsonAPI;
    }

    public void StartingRun() {
        String option = "1";

        while (!option.equalsIgnoreCase("0")) {
            log.info("""
                    
                    
                    ============================= Bem-vindo á LiterAlura! =============================
                    1. Buscar livro por titulo
                    2. Listar livros registrados
                    3. Listar todos os livros
                    Digite sua opção:
                    """);
            option = this.input.nextLine();
            switch (option) {
                case "1":
                    getBookByTitle();
                    break;
                case "2":
                    getListBooks();
                    break;
                case "3":
                    getAllBooks();
                    break;
                default:
                    break;
            }
        }

    }

    private void getAllBooks() {
        var req = serviceJsonAPI.getDataOfAPI("https://gutendex.com/books/");
        List<ApiResponse> res = Collections.singletonList(serviceJsonAPI.convertInObject(ApiResponse.class, req));
        res.forEach(log::info);
    }

    private void getListBooks() {
        var titlesBook = bookRepository.findAll();
        titlesBook.forEach(System.out::println);
    }

    private void getBookByTitle() {
        System.out.println("Digite o titulo do livro: ");
        String title = input.nextLine();

        var req = serviceJsonAPI.getDataOfAPI("https://gutendex.com/books?search=" + title.replace(" ", "%20"));
        List<ApiResponse> res = Collections.singletonList(serviceJsonAPI.convertInObject(ApiResponse.class, req));
        res.forEach(System.out::println);
    }

}
