package br.com.literalura.application;

import br.com.literalura.application.communication.response.ApiResponse;
import br.com.literalura.application.ports.inbound.repository.IAuthorRepository;
import br.com.literalura.application.ports.inbound.repository.IBookRepository;
import br.com.literalura.application.services.ServiceJsonAPI;
import br.com.literalura.domain.entities.Book;
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
                    4. Listar livros por idioma
                    5. Listar autores vivos em determinado ano
                    Digite sua opção:""");
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
                case "4":
                    getListBookByLanguage();
                    break;
                case "5":
                    getListAuthorsByYears();
                    break;
                default:
                    break;
            }
        }

    }

    private void getListAuthorsByYears() {
        System.out.println("Digite o determinado ano: ");
        String year = input.nextLine();
        var req = serviceJsonAPI.getDataOfAPI("https://gutendex.com/books?author_year_start=" + year + "&author_year_end=" + year);
        var objs = serviceJsonAPI.convertInObject(ApiResponse.class, req);
        var authors = serviceJsonAPI.convertObjInAuthors(objs);

        authors.forEach(System.out::println);
    }

    private void getListBookByLanguage() {
        var languagens = bookRepository.findAll()
                .stream()
                .map(Book::getLanguage)
                .distinct()
                .toList();

        System.out.println("=========== Selecione o idioma ===========");
        languagens.forEach(b -> System.out.println(b));
        System.out.println("==========================================");
        System.out.print("-> ");

        var languageSelect = input.nextLine().trim();

        var books = bookRepository.findByLanguageContainingIgnoreCase(languageSelect);
        System.out.println("Aqui está os livros da linguagem: " + languageSelect + "\n");
        books.forEach(System.out::println);
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
        var res = serviceJsonAPI.convertInObject(ApiResponse.class, req);
        var book = serviceJsonAPI.convertObjInEntity(res);

        var search = authorRepository.findByName(book.getAuthor().getName());
        if (search == null) {
            authorRepository.save(book.getAuthor());
        } else {
            book.setAuthor(search);
        }

        bookRepository.save(book);

        System.out.println(res.toString());
    }

}
