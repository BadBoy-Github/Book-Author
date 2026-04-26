package com.jpsider.book_author.DAO;

import java.util.List;
import java.util.Scanner;

import com.jpsider.book_author.Entity.Authors;
import com.jpsider.book_author.Entity.Books;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class BooksDAO {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("development");
	static EntityManager em = emf.createEntityManager();
	static EntityTransaction et = em.getTransaction();
	static Scanner  sc = new Scanner(System.in);
	
	public static void addBook() {
		System.out.print("Enter Author ID for this Book: ");
		int authorId = sc.nextInt();
		
		Authors author = em.find(Authors.class, authorId);
		if (author == null) {
			System.out.println("Author with ID " + authorId + " does not exist. Please add the author first.");
			return;
		}
		
		
		System.out.print("Enter Book ID: ");
		int id = sc.nextInt();
		sc.nextLine();

		if (em.find(Books.class, id) != null) {
			System.out.println("Book with ID " + id + " already exists. Please try again.");
			return;
		}

		Books book = new Books();

		System.out.print("Enter Book Title: ");
		String title = sc.nextLine();

		System.out.print("Enter Book Genre: ");
		String genre = sc.nextLine();
		
		System.out.print("Enter Published Year: ");
		String publishedYear = sc.nextLine();
		
		System.out.print("Enter Total Pages: ");
		int totalPages = sc.nextInt();

		

		book.setBookId(id);
		book.setBookName(title);
		book.setGenre(genre);
		book.setPublishedOn(publishedYear);
		book.setTotalPages(totalPages);
		book.setAuthor(author);
		author.setTotalBooks(author.getTotalBooks()+1);

		et.begin();
		em.persist(book);
		et.commit();
		System.out.println("Book added successfully!");
	}
	
	public static void viewAllBooks() {
		System.out.println("Books List:");
		System.out.println("-----------");
		
		List<Books> bookList = em.createQuery("SELECT b FROM Books b", Books.class).getResultList();
		
		if (bookList.isEmpty()) {
			System.out.println("No books found.");
			return;
		}

		for (Books book : bookList) {
			System.out.println("Book ID: " + book.getBookId());
			System.out.println("Book Title: " + book.getBookName());
			System.out.println("Genre: " + book.getGenre());
			System.out.println("Published Year: " + book.getPublishedOn());
			System.out.println("Total Pages: " + book.getTotalPages());
			System.out.println("Author Name: " + book.getAuthor().getAuthorName());
			System.out.println("-----------------------------------");
		}
	}
	
	
	public static void viewBooksByAuthorId() {
		System.out.print("Enter Author ID to view their books: ");
		int authorId = sc.nextInt();
		boolean haveBook = false;

		Authors author = em.find(Authors.class, authorId);
		if (author == null) {
			System.out.println("Author with ID " + authorId + " does not exist.");
			return;
		}

		List<Books> bookList = em.createQuery("SELECT b FROM Books b", Books.class).getResultList();

		if (bookList.isEmpty()) {
			System.out.println("No books found ");
			return;
		}

		System.out.println("Books by Author: " + author.getAuthorName());
		System.out.println("-----------------------------------");
		for (Books book : bookList) {
			if (book.getAuthor().getAuthorId() == authorId) {
				System.out.println("Book ID: " + book.getBookId());
				System.out.println("Book Title: " + book.getBookName());
				System.out.println("Genre: " + book.getGenre());
				System.out.println("Published Year: " + book.getPublishedOn());
				System.out.println("Total Pages: " + book.getTotalPages());
				System.out.println("-----------------------------------");
				haveBook = true;
			}
		}
		if (!haveBook) {
			System.out.println("No books found for Author " + author.getAuthorName() + " with id " + authorId);
		}
	}
	
	
	public static void updateBook() {
		System.out.print("Enter Book ID to update: ");
		int bookId = sc.nextInt();
		sc.nextLine();

		Books book = em.find(Books.class, bookId);
		if (book == null) {
			System.out.println("Book with ID " + bookId + " not found.");
			return;
		}

		System.out.println("Current Book Details:");
		System.out.println("---------------------");
		System.out.println("Book ID: " + book.getBookId());
		System.out.println("Book Title: " + book.getBookName());
		System.out.println("Genre: " + book.getGenre());
		System.out.println("Published Year: " + book.getPublishedOn());
		System.out.println("Total Pages: " + book.getTotalPages());

		System.out.print("Enter new Book Title: ");
		String title = sc.nextLine();
		book.setBookName(title);
		
		et.begin();
		em.persist(book);
		et.commit();

		System.out.println("Book updated successfully!");
	}
	
	public static void deleteBook() {
		System.out.print("Enter Book ID to delete: ");
		int bookId = sc.nextInt();

		Books book = em.find(Books.class, bookId);
		if (book == null) {
			System.out.println("Book with ID " + bookId + " not found.");
			return;
		}
		
		Authors author = book.getAuthor();
		
		if (author != null) {
			author.setTotalBooks(author.getTotalBooks()-1);
		}

		et.begin();
		em.remove(book);
		et.commit();

		System.out.println("Book deleted successfully!");
	}

}
