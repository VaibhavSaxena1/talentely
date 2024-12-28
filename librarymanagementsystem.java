import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public LibraryManagementSystem() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Book added: " + title);
    }

    public void registerUser(String name) {
        users.add(new User(name));
        System.out.println("User registered: " + name);
    }

    public void borrowBook(String userName, String bookTitle) {
        User user = findUser(userName);
        Book book = findBook(bookTitle);

        if (user != null && book != null && !book.isBorrowed()) {
            book.setBorrowed(true);
            user.borrowBook(book);
            System.out.println(userName + " borrowed " + bookTitle);
        } else {
            System.out.println("Cannot borrow the book.");
        }
    }

    public void returnBook(String userName, String bookTitle) {
        User user = findUser(userName);
        Book book = findBook(bookTitle);

        if (user != null && book != null && book.isBorrowed()) {
            book.setBorrowed(false);
            user.returnBook(book);
            System.out.println(userName + " returned " + bookTitle);
        } else {
            System.out.println("Cannot return the book.");
        }
    }

    private User findUser(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    private Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void displayBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Register User");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;
                case 2:
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    library.registerUser(name);
                    break;
                case 3:
                    System.out.print("Enter user name: ");
                    String borrowerName = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String borrowTitle = scanner.nextLine();
                    library.borrowBook(borrowerName, borrowTitle);
                    break;
                case 4:
                    System.out.print("Enter user name: ");
                    String returnerName = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnerName, returnTitle);
                    break;
                case 5:
                    library.displayBooks();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    @Override
    public String toString() {
        return title + " by " + author + (isBorrowed ? " (Borrowed)" : "");
    }
}

class User {
    private String name;
    private ArrayList<Book> borrowedBooks;

    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}
