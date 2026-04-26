# Book-Author Management System

A Java console application for managing authors and their books using JPA (Java Persistence API) with Hibernate and MySQL database.

## Features

### Author Management
- **Add New Author**: Create new author records with ID, name, gender, and age
- **View All Authors**: Display all authors with their details
- **Update Author**: Modify author name and age
- **Delete Author**: Remove authors (automatically disassociates their books)
- **View Author by Book ID**: Find author details using a book ID

### Book Management
- **Add New Book**: Create new book records linked to existing authors
- **View All Books**: Display all books with their details and author information
- **Update Book**: Modify book title
- **Delete Book**: Remove books and update author's book count
- **View Books by Author ID**: List all books written by a specific author

## Technology Stack

- **Java**: 17
- **JPA Implementation**: Hibernate 6.5.2
- **Database**: MySQL 8.x
- **Build Tool**: Maven
- **Testing**: JUnit 5

## Prerequisites

- Java 17 or higher
- MySQL Server running on localhost:3306
- Maven 3.x

## Database Setup

1. Install and start MySQL Server
2. Create a database user with the following credentials:
   - Username: `root`
   - Password: `root`
3. The application will automatically create the `book_author` database if it doesn't exist

**Note**: If you want to use different database credentials, update the `persistence.xml` file in `src/main/resources/META-INF/persistence.xml`

## Installation and Setup

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd book-author
   ```

2. Build the project:
   ```bash
   mvn clean compile
   ```

3. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="com.jpsider.book_author.App"
   ```

## Usage

The application presents a console menu with the following options:

1. Add New Author
2. Add New Book
3. View All Authors
4. View All Books
5. View Books by Author ID
6. View Author by Book ID
7. Update Author Details
8. Update Book Details
9. Delete Author
10. Delete Book
11. Exit

### Example Workflow

1. **Start by adding authors** (option 1)
2. **Add books linked to authors** (option 2)
3. **Browse and manage** your collection using the view options
4. **Update information** as needed
5. **Remove entries** when necessary

## Project Structure

```
src/
├── main/
│   ├── java/com/jpsider/book_author/
│   │   ├── App.java                 # Main application class with menu
│   │   ├── DAO/
│   │   │   ├── AuthorsDAO.java      # Author data access operations
│   │   │   └── BooksDAO.java        # Book data access operations
│   │   └── Entity/
│   │       ├── Authors.java         # Author entity
│   │       └── Books.java           # Book entity
│   └── resources/META-INF/
│       └── persistence.xml          # JPA configuration
└── test/
    └── java/com/jpsider/book_author/
        └── AppTest.java             # Unit tests (placeholder)
```

## Database Schema

### Authors Table
- `authorId` (INT, Primary Key)
- `authorName` (VARCHAR)
- `gender` (VARCHAR)
- `age` (INT)
- `totalBooks` (INT)

### Books Table
- `bookId` (INT, Primary Key)
- `bookName` (VARCHAR)
- `genre` (VARCHAR)
- `publishedOn` (VARCHAR)
- `totalPages` (INT)
- `author_authorId` (INT, Foreign Key to Authors)

## Dependencies

- **Hibernate Core**: ORM framework for JPA
- **MySQL Connector/J**: MySQL JDBC driver
- **JUnit Jupiter**: Testing framework

## Configuration

Database connection settings are configured in `persistence.xml`:

```xml
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/book_author?createDatabaseIfNotExist=true"/>
<property name="javax.persistence.jdbc.user" value="root"/>
<property name="javax.persistence.jdbc.password" value="root"/>
```

## Building and Testing

- **Compile**: `mvn compile`
- **Run Tests**: `mvn test`
- **Clean Build**: `mvn clean package`
- **Run Application**: `mvn exec:java -Dexec.mainClass="com.jpsider.book_author.App"`

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Run tests and ensure they pass
6. Submit a pull request

