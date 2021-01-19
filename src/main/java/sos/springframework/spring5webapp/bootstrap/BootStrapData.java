package sos.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sos.springframework.spring5webapp.domain.Author;
import sos.springframework.spring5webapp.domain.Book;
import sos.springframework.spring5webapp.domain.Publisher;
import sos.springframework.spring5webapp.repositories.AuthorRepository;
import sos.springframework.spring5webapp.repositories.BookRepository;
import sos.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository    authorRepository;
    private final BookRepository      bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in BootStrap");

        Publisher publisher = new Publisher();
        publisher.setCity("St Petersburg");
        publisher.setName("SFG Publisher");
        publisher.setState("Fl");

        publisherRepository.save(publisher);

        Author eric    = new Author("Eric", "Evans");
        Book   newBok1 = new Book("Domain Drive Design", "123123");
        eric.getBooks().add(newBok1);
        newBok1.getAuthors().add(eric);
        newBok1.setPublisher(publisher);

        authorRepository.save(eric);
        bookRepository.save(newBok1);

        Author rod     = new Author("Rod", "Johnson");
        Book   newBok2 = new Book("J2EE Development without EJB", "2341341234");
        rod.getBooks().add(newBok2);
        newBok2.getAuthors().add(rod);
        newBok2.setPublisher(publisher);

        authorRepository.save(rod);
        bookRepository.save(newBok2);

        publisher.getBooks().add(newBok1);
        publisher.getBooks().add(newBok2);

        publisherRepository.save(publisher);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher number of books: " + publisher.getBooks().size());


    }
}
