package br.com.literalura;

import br.com.literalura.application.RunStarting;
import br.com.literalura.application.ports.inbound.interfaces.IServiceJsonAPI;
import br.com.literalura.application.ports.inbound.repository.IAuthorRepository;
import br.com.literalura.application.ports.inbound.repository.IBookRepository;
import br.com.literalura.application.services.ServiceJsonAPI;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    private ServiceJsonAPI serviceJsonAPI;
    private IBookRepository bookRepository;
    private final IAuthorRepository authorRepository;

    public LiteraluraApplication(ServiceJsonAPI serviceJsonAPI, IBookRepository bookRepository, IAuthorRepository authorRepository) {
        this.serviceJsonAPI = serviceJsonAPI;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var starting = new RunStarting(bookRepository, authorRepository, serviceJsonAPI);
        starting.StartingRun();
    }
}
