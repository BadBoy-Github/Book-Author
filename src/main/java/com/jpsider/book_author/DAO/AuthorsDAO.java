package com.jpsider.book_author.DAO;

import java.util.List;
import java.util.Scanner;

import com.jpsider.book_author.Entity.Authors;
import com.jpsider.book_author.Entity.Books;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class AuthorsDAO {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("development");
	static EntityManager em = emf.createEntityManager();
	static EntityTransaction et = em.getTransaction();
	static Scanner  sc = new Scanner(System.in);
	
	public static void addAuthor() {
		System.out.print("Enter Author ID: ");
        int id = sc.nextInt();
        sc.nextLine(); 
        
		if (em.find(Authors.class, id) != null) {
			System.out.println("Author with ID " + id + " already exists. Please try again.");
			return;
		}
		
		Authors author = new Authors();
		
		System.out.print("Enter Author Name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter Author Gender: ");
        String gender = sc.nextLine();
        
        System.out.print("Enter Author Age: ");
        int age = sc.nextInt();
        sc.nextLine(); 
        
        author.setAuthorId(id);
        author.setAuthorName(name);
        author.setGender(gender);
        author.setAge(age);
        
        et.begin();
        em.persist(author);
        et.commit();
        System.out.println("Author added successfully!");
	}
	
	public static void viewAllAuthors() {
		
		System.out.println("Authors List:");
		System.out.println("-----------");
		
		List<Authors> authorList = em.createQuery("SELECT a FROM Authors a", Authors.class).getResultList();
		
		if (authorList.isEmpty()) {
			System.out.println("No authors found.");
			return;
		}
		
		for(Authors author : authorList) {
            System.out.println("Author ID: " + author.getAuthorId());
            System.out.println("Author Name: " + author.getAuthorName());
            System.out.println("Author Gender: " + author.getGender());
            System.out.println("Author Age: " + author.getAge());
            System.out.println("Total Books: " + author.getTotalBooks());
            System.out.println("-----------------------------------");
        }
	}
	
	public static void viewAuthorByBookId() {
		System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();
        
        Books book = em.find(Books.class, bookId);
        
		if (book == null) {
			System.out.println("Book with ID " + bookId + " not found.");
			return;
		}
		
		Authors author = book.getAuthor();
		
		System.out.println("Author Details:");
		System.out.println("-----------------");
		System.out.println("Author ID: " + author.getAuthorId());
		System.out.println("Author Name: " + author.getAuthorName());
		System.out.println("Gender: " + author.getGender());
		System.out.println("Age: " + author.getAge());
		System.out.println("Total Books: " + author.getTotalBooks());
        
	}
	
	public static void updateAuthor() {
		System.out.print("Enter Author ID to update: ");
        int authorId = sc.nextInt();
        sc.nextLine(); 
        
        Authors author = em.find(Authors.class, authorId);
        
        if (author == null) {
            System.out.println("Author with ID " + authorId + " not found.");
            return;
        }
        
        System.out.println("Author Details: ");
        System.out.println("-----------------");
        System.out.println("Author ID: " + author.getAuthorId());
        System.out.println("Author Name: " + author.getAuthorName());
        System.out.println("Gender: " + author.getGender());
		System.out.println("Age: " + author.getAge());
		System.out.println("Total Books: " + author.getTotalBooks());
        System.out.println("\n\n");
        
        System.out.print("Enter new Author Name: ");
        String newName = sc.nextLine();
        System.out.print("Enter new Author Age: ");
        int newAge = sc.nextInt();
        
        author.setAuthorName(newName);
        author.setAge(newAge);
        
        et.begin();
        em.persist(author);
        et.commit();
        
        System.out.println("Author details updated successfully!");
	}
	
	public static void deleteAuthor() {
		System.out.print("Enter Author ID to delete: ");
		int authorId = sc.nextInt();
		boolean haveBook = false;

		Authors author = em.find(Authors.class, authorId);

		if (author == null) {
			System.out.println("Author with ID " + authorId + " not found.");
			return;
		}

		et.begin();
		
		List<Books> bookList = em.createQuery("SELECT b FROM Books b", Books.class).getResultList();
		
		if (bookList.isEmpty()) {
			System.out.println("No books found for Author " + author.getAuthorName() + " with id " + authorId);
			return;
		}
		
		for (Books book : bookList) {
			if (book.getAuthor().getAuthorId() == authorId) {
				book.setAuthor(null);
				em.persist(book);
				haveBook = true;
			}
		}
		
		em.remove(author);
		et.commit();

		System.out.println("Author deleted successfully!");
		if (haveBook) {
			System.out.println("All books associated with Author " + author.getAuthorName()
					+ " have been updated to remove the association.");
		}
	}

}
