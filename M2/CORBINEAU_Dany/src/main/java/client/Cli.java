package client;

import server.ws.IAuthor;
import server.ws.IBook;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Cli {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean mainLoop = true;
        try {
            /* Change it by singleton */
            BookCli bookCli = new BookCli(scanner);
            AuthorCli authorCli = new AuthorCli(scanner);
            bookCli.authorCli = authorCli;
            authorCli.bookCli = bookCli;
            System.out.println("Welcome to SOAP Client");
            while (mainLoop) {
                System.out.println("What do you want to do ?");
                System.out.println(ANSI_GREEN + "1" + ANSI_RESET + " : Actions on books");
                System.out.println(ANSI_GREEN + "2" + ANSI_RESET + " : Actions on authors");
                System.out.println(ANSI_GREEN + "0" + ANSI_RESET + " : Quit");

                String value = scanner.nextLine();
                int intValue;
                intValue = getIntValue(value);
                if (intValue >= 0) {
                    switch (intValue) {
                        case 1:
                            bookCli.run();
                            break;
                        case 2:
                            authorCli.run();
                            break;
                        case 0:
                            System.out.println("Good by");
                            mainLoop = false;
                            break;
                        default:
                            System.out.println(ANSI_RED + "Not accepted value" + ANSI_RESET);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.close();
    }


    public static int getIntValue(String value) throws Exception {
        if (value.compareTo("") == 0) {
            System.out.println(ANSI_RED + "A empty value is not accept." + ANSI_RESET);
            throw new Exception();
        }
        int intVal;
        try {
            intVal = Integer.parseInt(value);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "The value is not a number." + ANSI_RESET);
            throw new Exception();
        }
        return intVal;
    }

    public static IBook getBookService() throws MalformedURLException {
        URL url = new URL("http://localhost:8081/bookImp?wsdl");
        QName qName = new QName("http://ws.server/", "BookImplService");
        Service ser = Service.create(url, qName);
        return ser.getPort(IBook.class);
    }

    public static IAuthor getAuthorService() throws MalformedURLException {
        URL url = new URL("http://localhost:8081/authorImp?wsdl");
        QName qName = new QName("http://ws.server/", "AuthorImplService");
        Service ser = Service.create(url, qName);
        return ser.getPort(IAuthor.class);
    }
}
