package com.jpsider.book_author;

import java.util.Scanner;

import com.jpsider.book_author.DAO.AuthorsDAO;
import com.jpsider.book_author.DAO.BooksDAO;
import com.jpsider.book_author.Entity.Authors;
import com.jpsider.book_author.Entity.Books;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * Hello world!
 */
public class App {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("development");
	static EntityManager em = emf.createEntityManager();
	static EntityTransaction et = em.getTransaction();
	static Scanner  sc = new Scanner(System.in);
	
    public static void main(String[] args) {
    	
        System.out.println("\n\n _______     ______      ______    __   ___   ________     -          __       ____  ____  ___________  __    __     ______     _______   \r\n"
        		+ "|   _  \"\\   /    \" \\    /    \" \\  |/\"| /  \") /\"       )              /\"\"\\     (\"  _||_ \" |(\"     _   \")/\" |  | \"\\   /    \" \\   /\"      \\  \r\n"
        		+ "(. |_)  :) // ____  \\  // ____  \\ (: |/   / (:   \\___/              /    \\    |   (  ) : | )__/  \\\\__/(:  (__)  :) // ____  \\ |:        | \r\n"
        		+ "|:     \\/ /  /    ) :)/  /    ) :)|    __/   \\___  \\               /' /\\  \\   (:  |  | . )    \\\\_ /    \\/      \\/ /  /    ) :)|_____/   ) \r\n"
        		+ "(|  _  \\\\(: (____/ //(: (____/ // (// _  \\    __/  \\\\             //  __'  \\   \\\\ \\__/ //     |.  |    //  __  \\\\(: (____/ //  //      /  \r\n"
        		+ "|: |_)  :)\\        /  \\        /  |: | \\  \\  /\" \\   :)           /   /  \\\\  \\  /\\\\ __ //\\     \\:  |   (:  (  )  :)\\        /  |:  __   \\  \r\n"
        		+ "(_______/  \\\"_____/    \\\"_____/   (__|  \\__)(_______/           (___/    \\___)(__________)     \\__|    \\__|  |__/  \\\"_____/   |__|  \\___) \r\n"
        		+ "                                                                                                                                          \n\n");
        
        System.out.println("Welcome to the Book-Author Management System!");
        System.out.println("Here, you can \n\t1. Add New Author\n\t2. Add New Book\n\t3. View All Authors\n\t4. View All Books\n\t5. View Books by Author ID\n\t6. View Author by Book ID\n\t7. Update Author Details\n\t8. Update Book Details\n\t9. Delete Author\n\t10. Delete Book\n\t11. Exit");
        System.out.print("Please enter your choice: ");
        int choice = sc.nextInt();
        
        switch(choice) {
        
        case 1:
        	AuthorsDAO.addAuthor();
        	break;
        	
        case 2:
			BooksDAO.addBook();
			break;
			
        case 3:
        	AuthorsDAO.viewAllAuthors();
        	break;
        	
        case 4:
        	BooksDAO.viewAllBooks();
        	break;
        	
        case 5:
			BooksDAO.viewBooksByAuthorId();
			break;
			
        case 6:
        	AuthorsDAO.viewAuthorByBookId();
        	break;
        	
        case 7:
			AuthorsDAO.updateAuthor();
			break;
			
        case 8:
        	BooksDAO.updateBook();
        	break;
        	
        case 9:
			AuthorsDAO.deleteAuthor();
			break;
			
        case 10:
			BooksDAO.deleteBook();
			break;
        
        case 11:
			System.out.println("Thank you for using the Book-Author Management System. Goodbye!");
			sc.close();
			return;
        
        default:
			System.out.println("Invalid choice! Please try again.");
			break;
        }
        
        main(args);
    }
}
