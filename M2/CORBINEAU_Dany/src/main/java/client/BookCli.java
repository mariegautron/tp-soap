package client;

import server.model.AuthorModel;
import server.model.BookModel;
import server.ws.IAuthor;
import server.ws.IBook;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BookCli {
    Scanner scanner;
    IBook bookService;
    IAuthor authorService;
    public AuthorCli authorCli;

    public BookCli(Scanner scanner) throws MalformedURLException {
        this.scanner = scanner;
        bookService = Cli.getBookService();
        authorService = Cli.getAuthorService();
    }

    public void run() {
        boolean mainLoop = true;
        while (mainLoop) {
            System.out.println("--- Actions on Book ----------------------------------");
            System.out.println(Cli.ANSI_GREEN + "1" + Cli.ANSI_RESET + " : Add a book");
            System.out.println(Cli.ANSI_GREEN + "2" + Cli.ANSI_RESET + " : Show all books");
            System.out.println(Cli.ANSI_GREEN + "3" + Cli.ANSI_RESET + " : Update a book");
            System.out.println(Cli.ANSI_GREEN + "4" + Cli.ANSI_RESET + " : Delete a book");
            System.out.println(Cli.ANSI_GREEN + "5" + Cli.ANSI_RESET + " : Show books by author name");
            System.out.println(Cli.ANSI_GREEN + "6" + Cli.ANSI_RESET + " : Show books without author");
            System.out.println(Cli.ANSI_GREEN + "7" + Cli.ANSI_RESET + " : Show recent books");
            System.out.println(Cli.ANSI_GREEN + "0" + Cli.ANSI_RESET + " : Quit");

            String value = scanner.nextLine();
            int intValue;
            try {
                intValue = Cli.getIntValue(value);
                if (intValue >= 0) {
                    switch (intValue) {
                        case 1:
                            addBookk();
                            break;
                        case 2:
                            showAllBooks();
                            break;
                        case 3:
                            updateBook();
                            break;
                        case 4:
                            deleteBook();
                            break;
                        case 5:
                            showBookByAuthorName();
                            break;
                        case 6:
                            showBookWithoutAuthor();
                            break;
                        case 7:
                            showBookByDate();
                            break;
                        case 0:
                            System.out.println("Return to main menu");
                            mainLoop = false;
                            break;
                        default:
                            System.out.println(Cli.ANSI_RED + "Not accepted value" + Cli.ANSI_RESET);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showBookWithoutAuthor()
    {
        BookModel[] books = bookService.getBooksWithNoAuthor();
        if(books.length > 0) {
            printBookArray(books);
        } else {
            System.out.println(Cli.ANSI_RED+"Book not find"+Cli.ANSI_RESET);
        }
    }

    private void printBookArray(BookModel[] books)
    {
        for (BookModel book : books) {
            StringBuilder authors = new StringBuilder();
            for(AuthorModel a :book.getAuthors()) {
                if(a!=null) {
                    authors.append(a.getFirstName()).append("-").append(a.getLastName()).append(", ");
                }
            }
            System.out.println(
                    "ID : "
                            + Cli.ANSI_GREEN + book.getId() + Cli.ANSI_RESET
                            + ". Title : "
                            + Cli.ANSI_GREEN + book.getTitle() + Cli.ANSI_RESET
                            + ". ISBN : "
                            + Cli.ANSI_GREEN + book.getISBN() + Cli.ANSI_RESET
                            + ". Publication date : "
                            + Cli.ANSI_GREEN + Cli.dateFormat.format(book.getPublicationDate()) + Cli.ANSI_RESET
                            + ". Writen by : "
                            + Cli.ANSI_GREEN+authors.toString()+Cli.ANSI_RESET
            );
        }
    }

    private void showBookByAuthorName()
    {
        System.out.println(Cli.ANSI_GREEN+"Author Name"+Cli.ANSI_RESET);
        String value = scanner.nextLine();
        if(value.compareTo("") != 0) {
            BookModel[] books = bookService.getBooksByAuthorName(value, value);
            if(books.length > 0) {
                printBookArray(books);
            } else {
                System.out.println(Cli.ANSI_RED+"Book not find"+Cli.ANSI_RESET);
            }
        } else {
            System.out.println(Cli.ANSI_RED+"Author name cannot be empty"+Cli.ANSI_RESET);
        }
    }

    private void showBookByDate()
    {
        boolean loop = true;
        Date date = new Date();
        while(loop) {
            System.out.println(Cli.ANSI_GREEN+"Start date (yyyy-mm-d format) ?"+Cli.ANSI_RESET);
            String value = scanner.nextLine();
            if(value.compareTo("") != 0) {
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(value);
                    loop = false;
                } catch (ParseException e) {
                    System.out.println(Cli.ANSI_RED+"Not valid format"+Cli.ANSI_RESET);
                }
            } else {
                loop = false;
            }
        }
        BookModel[] books = bookService.getBooksRecently(date);
        if(books.length > 0) {
            printBookArray(books);
        } else {
            System.out.println(Cli.ANSI_RED+"Book not find"+Cli.ANSI_RESET);
        }
    }

    private void updateBook()
    {
        boolean mainLoop = true;

        List<Integer> validId = new ArrayList<Integer>();
        BookModel[] books = bookService.getAllBooks();
        for (BookModel book : books) {
            validId.add(book.getId());
        }
        while (mainLoop) {
            System.out.println("Type the book ID");
            String value = scanner.nextLine();
            int intValue;
            try {
                intValue = Cli.getIntValue(value);
                if (intValue > 0 && validId.contains(intValue)) {
                    BookModel currentBook = bookService.getBook(intValue);
                    if(currentBook!=null) {
                        System.out.println("Change value of title : "+Cli.ANSI_GREEN+currentBook.getTitle()+Cli.ANSI_RESET+" [nothing to keep current value]");
                        value = scanner.nextLine();
                        if (value.compareTo("") != 0) {
                            currentBook.setTitle(value);
                        }
                        System.out.println("Change value of ISBN : "+Cli.ANSI_GREEN+currentBook.getISBN()+Cli.ANSI_RESET+" [nothing to keep current value]");
                        value = scanner.nextLine();
                        if (value.compareTo("") != 0) {
                            currentBook.setISBN(value);
                        }
                        System.out.println("Change value of publication date (YYYY-MM-DD) : "+Cli.ANSI_GREEN+Cli.dateFormat.format(currentBook.getPublicationDate())+Cli.ANSI_RESET+" [nothing to kkeep current value]");
                        String dateString = scanner.nextLine();
                        if(dateString.compareTo("") != 0) {
                            Date date;
                            try {
                                date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                                currentBook.setPublicationDate(date);
                            } catch (Exception e) {
                                System.out.println(Cli.ANSI_RED+"Not valid format"+Cli.ANSI_RESET);
                            }
                        }
                        /* TODO add author add & remove system */

                        bookService.updateBook(currentBook);
                        System.out.println(Cli.ANSI_GREEN+"Book "+currentBook.getId()+" have been updated"+Cli.ANSI_RESET);
                        mainLoop = false;
                    }
                } else if(intValue == 0) {
                    System.out.println(Cli.ANSI_GREEN+"Abort update"+Cli.ANSI_RESET);
                    mainLoop = false;
                }
                else {
                    System.out.println(Cli.ANSI_RED+intValue+" is not a valid ID"+Cli.ANSI_RESET);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showAllBooks() {
        BookModel[] books = bookService.getAllBooks();
        printBookArray(books);
    }

    private void addBookk() {
        boolean mainLoop = true;
        System.out.println(Cli.ANSI_GREEN+"Add book"+Cli.ANSI_RESET);

        System.out.println("Book title ?");
        String title = scanner.nextLine();

        System.out.println("Book ISBN ?");
        String isbn = scanner.nextLine();

        while(mainLoop) {
            System.out.println("Publication date ? (YYYY-MM-DD)");
            String dateString = scanner.nextLine();
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            } catch (Exception e) {
                System.out.println(Cli.ANSI_RED+"Not valid format"+Cli.ANSI_RESET);
                continue;
            }
            /* TODO add book add system */

            BookModel bm = new BookModel(title, isbn, date);
            bm.setAuthors(new AuthorModel[0]);
            bookService.addBook(bm);
            System.out.println(Cli.ANSI_GREEN+"Book added"+Cli.ANSI_RESET);
            mainLoop = false;
        }

    }

    private void deleteBook() {
        boolean mainLoop = true;

        List<Integer> validId = new ArrayList<Integer>();
        BookModel[] books = bookService.getAllBooks();
        for (BookModel book : books) {
            validId.add(book.getId());
        }
        while (mainLoop) {
            System.out.println("Type the book ID");
            String value = scanner.nextLine();
            int intValue;
            try {
                intValue = Cli.getIntValue(value);
                if (intValue >= 0 && validId.contains(intValue)) {
                    bookService.deleteBook(intValue);
                    mainLoop = false;
                    System.out.println(Cli.ANSI_GREEN+"Book "+intValue+" removed."+Cli.ANSI_RESET);
                } else if(intValue == 0) {
                    System.out.println(Cli.ANSI_GREEN+"Abort delete"+Cli.ANSI_RESET);
                    mainLoop = false;
                }
                else {
                    System.out.println(Cli.ANSI_RED+intValue+" is not a valid ID"+Cli.ANSI_RESET);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
