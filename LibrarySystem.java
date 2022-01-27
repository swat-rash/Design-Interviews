
class Book{
    int id;
    String name;
    String author;
    String category;
    int count;
}

class BooksCopy{
    int id;
    Book book;
    String barCode;
}

class User{
    int userId;
    String name;
    List<BookCopy> bookCopies;
}

class UserBooks{
    User user;
    BookCopy bookCopy;
    long timestamp;
}

class LibrarySlot{
    int slotId;
}

class Library{
    LibrarySlot[] librarySlots;
    
    Library(int n){
        librarySlots = new LibrarySlot[n];
    }
}

class LibrarySearchService{
    
    
    Book findBookByName(String name){
        //return book;
    }
    
    List<Book> findBooksByCategory(String name){
        //return bookId;
    }
    
    List<Book> findBooksByAuthor(String author){
        //return bookId;
    }
}

class LibraryAdminService{
    
    
    
    void placeBooks(BookCopy copy){
        
    }
    
    User getBookReservedBy(BookCopy bookCopy){
        
    }
}

class LibraryUserService{
    Library library;
    
    Map<Integer, User> users;
    
    LibraryUserService(){
        this.users = new HashMap<>();
    }
    
    
    void addUser(User user){
        
    }
    
    int[] retrieveBook(Book book){
        
    }
    
    void reserveBook(BookCopy bookCopy){
        
    }
    
    List<BookCopy> getBooksReservedByUser(User user){
        
    }
}

class LibraryService{
    LibraryUserService lus;
    LibraryAdminService las;
    LibrarySearchService lss;
    
    LibraryService(){
        lus = new LibraryUserService();
        las = new LibraryAdminService();
        lss = new LibrarySearchService();
    }
    
    LibraryUserService getLUS(){
        return this.lus;
    }
    
    LibraryAdminService getLAS(){
        return this.las;
    }
    
    LibrarySearchService getLSS(){
        return this.lss;
    }
}


public class Main
{
    LibraryService libraryService = new LibraryService();
    User a = new User(1, name);
    User b = new User(1, name);
    libraryService.getLUS().addUser(a);
    libraryService.getLUS().addUser(b);
    
    Book bookA = new Book(1, "A", "AuthorA", "CategoryA");
    Book bookB = new Book(2, "B", "AuthorB", "CategoryB");
    libraryService.getLAS().addBook(bookA);
    libraryService.getLAS().addBook(bookB);
    
}
