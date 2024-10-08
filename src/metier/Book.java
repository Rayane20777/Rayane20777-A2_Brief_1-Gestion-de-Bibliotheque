package metier;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Book extends Document {

    private String isbn;

    public Book(String title, String author, LocalDate publicationDate, int numberOfPages, boolean status, String isbn) {
        super(title, author, publicationDate, numberOfPages, status);
        this.isbn = isbn;
    }

    public Book() {
        super("", "", LocalDate.now(), 0, true);
        this.isbn = "";
    }

    public static void search(List<Book> books, String title) {
        final String  searchTitle  = title.toLowerCase();

        List<Book> foundBooks = books.stream()
                .filter(book -> book.getTitle().toLowerCase().equals(searchTitle))
                .collect(Collectors.toList());
        if (foundBooks.isEmpty()) {
            System.out.println("No books found with the title \"" + title + "\".");
        } else {
            System.out.println("Book(s) found:");
            foundBooks.forEach(book -> {
                book.displayDetails();
                System.out.println();
            });
        }

    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void add(Scanner scanner) {
        System.out.print("Enter title: ");
        this.setTitle(scanner.nextLine());
        System.out.print("Enter author: ");
        this.setAuthor(scanner.nextLine());
        System.out.print("Enter publication date (dd/MM/yyyy): ");
        this.setPublicationDate(scanner.nextLine());
        System.out.print("Enter the number of pages: ");
        while (!scanner.hasNextInt()) {
            System.out.println(" Invalid input Please enter a valid number: ");
            scanner.nextLine();
        }

        this.setNumberOfPages(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter isbn: ");
        this.setIsbn(scanner.nextLine());

    }

    public void borrow() {
        if (getStatus()) {
            setStatus(false);
            System.out.println("You have Borrowed" + getTitle());
        } else {
            System.out.println(getTitle() + "is Borrowed!");
        }
    }

    public void returnDocument() {
        if (!getStatus()) {
            setStatus(true);
            System.out.println("You have Returned" + getTitle());
        } else {
            System.out.println(getTitle() + "is already Available!");
        }

    }

    public void displayDetails() {
        System.out.println("Title: " + getTitle() + "\nAuthor: " + getAuthor() + "\nPublication Date: " + getPublicationDate() + "\nISBN: " + getIsbn() + "\nStatus: " + getStatus());
    }
}
