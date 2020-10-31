package client;

import server.entity.Book;
import server.model.AuthorModel;
import server.model.BookModel;
import server.ws.IAuthor;
import server.ws.IBook;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthorCli {
    Scanner scanner;
    IAuthor service;
    IBook bookService;
    public BookCli bookCli;

    public AuthorCli(Scanner scanner) throws MalformedURLException {
        this.scanner = scanner;
        service = Cli.getAuthorService();
        bookService = Cli.getBookService();
    }

    public void run() {
        boolean mainLoop = true;
        while (mainLoop) {
            System.out.println("--- Actions on Author ----------------------------------");
            System.out.println(Cli.ANSI_GREEN + "1" + Cli.ANSI_RESET + " : Add a author");
            System.out.println(Cli.ANSI_GREEN + "2" + Cli.ANSI_RESET + " : Show all author");
            System.out.println(Cli.ANSI_GREEN + "3" + Cli.ANSI_RESET + " : Update a author");
            System.out.println(Cli.ANSI_GREEN + "4" + Cli.ANSI_RESET + " : Delete a author");
            System.out.println(Cli.ANSI_GREEN + "5" + Cli.ANSI_RESET + " : Show author by name");
            System.out.println(Cli.ANSI_GREEN + "0" + Cli.ANSI_RESET + " : Quit");

            String value = scanner.nextLine();
            int intValue;
            try {
                intValue = Cli.getIntValue(value);
                if (intValue >= 0) {
                    switch (intValue) {
                        case 1:
                            addAuthor();
                            break;
                        case 2:
                            showAllAuthor();
                            break;
                        case 3:
                            updateAuthor();
                            break;
                        case 4:
                            deleteAuthor();
                            break;
                        case 5:
                            showAuthorByName();
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

    private void showAuthorByName()
    {
        System.out.println(Cli.ANSI_GREEN+"Author Name"+Cli.ANSI_RESET);
        String value = scanner.nextLine();
        if(value.compareTo("") != 0) {
            AuthorModel[] authors = service.getAuthorByName(value);
            if(authors.length > 0) {
                printAuthors(authors);
            } else {
                System.out.println(Cli.ANSI_RED+"Author not find"+Cli.ANSI_RESET);
            }
        } else {
            System.out.println(Cli.ANSI_RED+"Author name cannot be empty"+Cli.ANSI_RESET);
        }
    }

    private void deleteAuthor()
    {
        boolean mainLoop = true;
        List<Integer> validId = new ArrayList<Integer>();
        AuthorModel[] authors = service.getAllAuthors();
        for(AuthorModel author : authors) {
            validId.add(author.getId());
        }
        while(mainLoop) {
            System.out.println("Type the author ID");
            String value = scanner.nextLine();
            int intValue;
            try {
                intValue = Cli.getIntValue(value);
                if (intValue > 0 && validId.contains(intValue)) {
                    service.deleteAuthor(intValue);
                    mainLoop = false;
                    System.out.println(Cli.ANSI_GREEN+"Author "+intValue+" removed."+Cli.ANSI_RESET);
                }
                else if (intValue == 0) {
                    System.out.println(Cli.ANSI_GREEN+"Abort delete"+Cli.ANSI_RESET);
                }
                else {
                    System.out.println(Cli.ANSI_RED+intValue+" is not a valid ID"+Cli.ANSI_RESET);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateAuthor()
    {
        boolean mainLoop = true;
        List<Integer> validId = new ArrayList<Integer>();
        AuthorModel[] authors = service.getAllAuthors();
        for(AuthorModel author: authors) {
            validId.add(author.getId());
        }
        while(mainLoop) {
            System.out.println("Type the author ID");
            String value = scanner.nextLine();
            int intValue;
            try {
                intValue = Cli.getIntValue(value);
                if(intValue > 0 && validId.contains(intValue)) {
                    AuthorModel currentAuthor = service.getAuthor(intValue);
                    if(currentAuthor != null) {
                        System.out.println("Change value of firstName : "+Cli.ANSI_GREEN+currentAuthor.getFirstName()+Cli.ANSI_RESET+" [nothing to keep current value]");
                        value = scanner.nextLine();
                        if (value.compareTo("") != 0) {
                            currentAuthor.setFirstName(value);
                        }
                        System.out.println("Change value of lastName : "+Cli.ANSI_GREEN+currentAuthor.getLastName()+Cli.ANSI_RESET+" [nothing to keep current value]");
                        value = scanner.nextLine();
                        if (value.compareTo("") != 0) {
                            currentAuthor.setLastName(value);
                        }
                        /* TODO add book add & remove system */
                        addBookForm(currentAuthor);

                        service.updateAuthor(currentAuthor);
                        System.out.println(Cli.ANSI_GREEN+"Author "+currentAuthor.getId()+" have been updated"+Cli.ANSI_RESET);
                        mainLoop = false;
                    }
                } else if(intValue == 0) {
                    System.out.println(Cli.ANSI_GREEN+"Abort upddate"+Cli.ANSI_RESET);
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

    private void addBookForm(AuthorModel currentAuthor)
    {
        boolean mainLoop = true;

        while (mainLoop) {
            System.out.println(Cli.ANSI_GREEN+"Need to add book to author ?"+Cli.ANSI_RESET);
            System.out.println(Cli.ANSI_GREEN + "0" + Cli.ANSI_RESET + " : No");
            System.out.println(Cli.ANSI_GREEN + "1" + Cli.ANSI_RESET + " : Yes");

            String value = scanner.nextLine();
            int intValue = 0;
            try {
                intValue = Cli.getIntValue(value);
                if (intValue > 0) {
                    boolean addLoop = true;
                    while(addLoop) {
                        System.out.println(Cli.ANSI_GREEN+"Type book ID to add it. 0 to quit. -1 to show all books."+Cli.ANSI_RESET);
                        String idValue = scanner.nextLine();
                        int idInt;
                        try {
                            idInt = Cli.getIntValue(idValue);
                            if(idInt == -1) {
                                bookCli.showAllBooks();
                            }
                            else if(idInt > 0) {
                                BookModel book = bookService.getBook(idInt);
                                if(book != null) {
                                    BookModel[] bookArray = new BookModel[currentAuthor.getBooks().length+1];
                                    int index = 0;
                                    for(BookModel bookModel : currentAuthor.getBooks()) {
                                        if(bookModel != null) {
                                            bookArray[index] = bookModel;
                                            index++;
                                        }
                                    }
                                    bookArray[index] = book;
                                    System.out.println(Cli.ANSI_GREEN+"Book "+book.getId()+" added to author."+Cli.ANSI_RESET);
                                    currentAuthor.setBooks(bookArray);
                                } else {
                                    System.out.println(Cli.ANSI_RED+"Book not find"+Cli.ANSI_RESET);
                                }
                            } else if(idInt == 0) {
                                addLoop = false;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println(Cli.ANSI_RED+intValue+" is not a valid ID"+Cli.ANSI_RESET);
                        }
                    }
                    mainLoop = false;
                } else if(intValue == 0) {
                    mainLoop = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(Cli.ANSI_RED+intValue+" is not a valid ID"+Cli.ANSI_RESET);
            }
        }
    }

    private void addAuthor()
    {
        System.out.println(Cli.ANSI_GREEN+"Add author"+Cli.ANSI_RESET);

        System.out.println("First name ?");
        String firstName = scanner.nextLine();

        System.out.println("Last name ?");
        String lastName = scanner.nextLine();

        AuthorModel author = new AuthorModel(firstName, lastName);
        author.setBooks(new BookModel[0]);
        addBookForm(author);

        service.addAuthor(author);
        System.out.println(Cli.ANSI_GREEN+"Author added"+Cli.ANSI_RESET);

    }

    public void showAllAuthor() {
        printAuthors(service.getAllAuthors());
    }

    private void printAuthors(AuthorModel[] authors) {
        for (AuthorModel author : authors) {
            StringBuilder books = new StringBuilder();
            for (BookModel b : author.getBooks()) {
                if (b != null) {
                    books.append(b.getTitle()).append("-").append(b.getISBN()).append(", ");
                }
            }
            System.out.println(
                    "ID : "
                            + Cli.ANSI_GREEN + author.getId() + Cli.ANSI_RESET
                            + ". Name : "
                            + Cli.ANSI_GREEN + author.getFirstName() + " " + author.getLastName() + Cli.ANSI_RESET
                            + ". Have writen : "
                            + Cli.ANSI_GREEN + books.toString() + Cli.ANSI_RESET
            );
        }
    }
}
