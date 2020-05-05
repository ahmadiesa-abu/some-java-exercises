import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Ahmad
 *
 */

class Book {
	String isbn;
	String title;
	String author;
	int edition;
	int publishedYear;
	boolean borrowed;
	
	
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Book(String isbn, String title, String author, int edition,
			int publishedYear, boolean borrowed) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.edition = edition;
		this.publishedYear = publishedYear;
		this.borrowed = borrowed;
	}



	public String getIsbn() {
		return isbn;
	}



	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public int getEdition() {
		return edition;
	}



	public void setEdition(int edition) {
		this.edition = edition;
	}



	public int getPublishedYear() {
		return publishedYear;
	}



	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}



	public boolean isBorrowed() {
		return borrowed;
	}



	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}



	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author
				+ ", edition=" + edition + ", publishedYear=" + publishedYear
				+ ", borrowed=" + borrowed + "]";
	}
	
	

}

public class BookLibrary {
	
	private static ArrayList<Book> library=new ArrayList<Book>();
	
	// Temporary variables used during the handling of parsing passed value from system input
	private static String arg1 = ""; // this is the first argument passed after the action
	private static String arg2 = ""; // this is the first argument passed after the action
	private static String arg3 = ""; // this is the first argument passed after the action
	
	public static String getExceptionTraceString(Exception e){
		StackTraceElement[] trace = e.getStackTrace();
		String stackTraceStr = "\n----------------------\n";
		stackTraceStr += "" + e.getCause() + "\n";
		int index = 0;
		for (StackTraceElement traceElement : trace){
			for (int i=0;i<index;i++){	// this trick is just for formatting
				stackTraceStr += "\t";
			}
			stackTraceStr += traceElement.getClassName() + ":" + traceElement.getMethodName() + ":" + traceElement.getLineNumber() + "\n";
			index++;
		}
		stackTraceStr += "----------------------\n";
		return stackTraceStr;
	}
	
	public static char handleUserInput(){
		char action = ' ';
		try{
			// Always clear these temporary variables
			arg1 = "";
			arg2 = "";
			arg3 = "";
			System.out.print("Please Provide your choice: ");
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			String nextLine = input.nextLine();
			if (nextLine.indexOf(" ")>-1){
				// Handle the action by splitting on space and convert to lower-case
				String action_string = nextLine.split(" ")[0].toLowerCase();
				if (action_string.equals("add")){
					action = 'a';  // will be handled in the main 
					arg1 = nextLine.split(" ")[1];  // in add case one argument {isbn}
				}else if(action_string.equals("delete")){
					action = 'd';  // will be handled in the main 
					arg1 = nextLine.split(" ")[1];  // in add case one argument {isbn}
				}else if(action_string.equals("set")){
					action = 's';  // will be handled in the main 
					String[] splits = nextLine.split(" ");
					arg1 = splits[1]; 
					arg2 = splits[2]; 
					arg3 = "";
					for (int i=3;i<splits.length;i++)
						arg3 += splits[i] + " ";  
					arg3 = arg3.trim();
				}else if(action_string.equals("get")){
					action = 'g';  // will be handled in the main 
					String[] splits = nextLine.split(" ");
					arg1 = splits[1]; 
					arg2 = splits[2]; 
				}else if(action_string.equals("rent")){
					action = 'r';  // will be handled in the main 
					arg1 = nextLine.split(" ")[1];
				}else if(action_string.equals("return")){
					action = 't';  // will be handled in the main 
					arg1 = nextLine.split(" ")[1];
				}else if(action_string.equals("search")){
					action = 'e';  // will be handled in the main 
					String[] splits = nextLine.split(" ");
					arg1 = splits[1]; 
					arg2 = "";
					for (int i=2;i<splits.length;i++)
						arg2 += splits[i] + " ";  
					arg2 = arg2.trim();
				} 
			}else{  // might be the case of quit/print
				String action_string = nextLine.toLowerCase();
				if(action_string.equals("print")){
					action = 'p';  // will be handled in the main 
				}else if(action_string.equals("quit")){
					action = 'q';  // will be handled in the main 
				}
			}
		}catch(Exception e){
			System.out.println("handleUserInput exception : "+getExceptionTraceString(e));
		}
		return action;
	}
	
	private static boolean addNewBook(String isbn){
		boolean appended = false;
		try{
			Book temp = new Book(isbn, null, null, 0, 0, false);
			library.add(temp);
			appended = true;
		}catch(Exception e){
			System.out.println("addNewBook exception : "+getExceptionTraceString(e));
			appended = false;
		}
		return appended;
	}
	
	private static boolean updateBook(String isbn, String attribute, String value){
		boolean updated = false;
		try{
			int index = 0;
			for (Book book : library){
				if (book.getIsbn().equalsIgnoreCase(isbn)){
					if (attribute.equalsIgnoreCase("title")){
						book.setTitle(value);
					}else if(attribute.equalsIgnoreCase("author")){
						book.setAuthor(value);
					}else if(attribute.equalsIgnoreCase("edition")){
						book.setEdition(Integer.parseInt(value));
					}else if(attribute.equalsIgnoreCase("publishedYear")){
						book.setPublishedYear(Integer.parseInt(value));
					}else if(attribute.equalsIgnoreCase("borrowed")){
						book.setBorrowed(Boolean.parseBoolean(value));
					}
					library.set(index, book);
					break;
				}
				index++;
			}
			updated = true;
		}catch(Exception e){
			System.out.println("updateBook exception : "+getExceptionTraceString(e));
			updated = false;
		}
		return updated;
	}
	
	private static String getBookAttrib(String isbn, String attribute){
		String value = "";
		try{
			for (Book book : library){
				if (book.getIsbn().equalsIgnoreCase(isbn)){
					if (attribute.equalsIgnoreCase("title")){
						value = "" + book.getTitle();
					}else if(attribute.equalsIgnoreCase("author")){
						value = "" + book.getAuthor();
					}else if(attribute.equalsIgnoreCase("edition")){
						value = "" + book.getEdition();
					}else if(attribute.equalsIgnoreCase("publishedYear")){
						value = "" + book.getPublishedYear();
					}else if(attribute.equalsIgnoreCase("borrowed")){
						value = "" + book.isBorrowed();
					}
					System.out.format("%s ~ %s : %s\n", isbn, attribute, value); 
					break;
				}
			}
		}catch(Exception e){
			System.out.println("getBookAttrib exception : "+getExceptionTraceString(e));
			value = "";
		}
		return value;
	}
	
	private static boolean deleteBook(String isbn){
		boolean deleted = false;
		try{
			for (Book book : library){
				if (book.getIsbn().equalsIgnoreCase(isbn)){
					library.remove(book);
					break;
				}
			}
			deleted = true;
		}catch(Exception e){
			System.out.println("deleteBook exception : "+getExceptionTraceString(e));
			deleted = false;
		}
		return deleted;
	}
	
	private static boolean updateBorrow(String isbn, String action){
		boolean updated = false;
		try{
			int index = 0;
			for (Book book : library){
				if (book.getIsbn().equalsIgnoreCase(isbn)){
					if (action.equalsIgnoreCase("rent")){
						book.setBorrowed(true);
					}else if(action.equalsIgnoreCase("return")){
						book.setBorrowed(false);
					}
					library.set(index, book);
					break;
				}
				index++;
			}
			updated = true;
		}catch(Exception e){
			System.out.println("updateBook exception : "+getExceptionTraceString(e));
			updated = false;
		}
		return updated;
	}
	
	
	private static void printAllBooksInLibrary(){
		System.out.format("%s\t%s\t%s\t%s\t%s\t%s\n", "ISBN", "Title",
						  "Edition", "Author", "Year", "Available"); 
		for (Book book : library){
			System.out.format("%s\t%s\t%s\t%s\t%s\t%s\n", book.getIsbn(), book.getTitle(),
					  book.getEdition(), book.getAuthor(), book.getPublishedYear(), !book.isBorrowed()); 
		}
	}
	
	private static void printBooksContainingKeyword(String attribute, String keyword){
		System.out.format("%s\t%s\t%s\t%s\t%s\t%s\n", "ISBN", "Title",
						  "Edition", "Author", "Year", "Available");
		boolean printBook = false;
		for (Book book : library){
			if(attribute.toLowerCase().equalsIgnoreCase("isbn")){
				String value = book.getIsbn();
				if (value.contains(keyword)){
					printBook = true;
				}else{
					printBook = false;
				}
			}else if(attribute.toLowerCase().equalsIgnoreCase("title")){
				String value = book.getTitle();
				if (value.contains(keyword)){
					printBook = true;
				}else{
					printBook = false;
				}
			}else if(attribute.toLowerCase().equalsIgnoreCase("author")){
				String value = book.getAuthor();
				if (value.contains(keyword)){
					printBook = true;
				}else{
					printBook = false;
				}
			}else if(attribute.toLowerCase().equalsIgnoreCase("edition")){
				int value = book.getEdition();
				if (value==Integer.parseInt(keyword)){
					printBook = true;
				}else{
					printBook = false;
				}
			}else if(attribute.toLowerCase().equalsIgnoreCase("publishedYear")){
				int value = book.getPublishedYear();
				if (value==Integer.parseInt(keyword)){
					printBook = true;
				}else{
					printBook = false;
				}
			}else if(attribute.toLowerCase().equalsIgnoreCase("borrowed")){
				boolean value = book.isBorrowed();
				if (value==Boolean.parseBoolean(keyword)){
					printBook = true;
				}else{
					printBook = false;
				}
			}
			if (printBook){
				System.out.format("%s\t%s\t%s\t%s\t%s\t%s\n", book.getIsbn(), book.getTitle(),
						  book.getEdition(), book.getAuthor(), book.getPublishedYear(), !book.isBorrowed());
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char action = ' ';
		do{
			action = handleUserInput();
			switch(action){
				case 'a':
					addNewBook(arg1); // case of add only 1 argument {isbn}
					break;
				case 'd':
					deleteBook(arg1); // case of delete only 1 argument {isbn}
					break;
				case 'r':
					updateBorrow(arg1, "rent"); // case of rent only 1 argument {isbn}
					break;
				case 't':
					updateBorrow(arg1, "return"); // case of return only 1 argument {isbn}
					break;
				case 'e':
					printBooksContainingKeyword(arg1, arg2); // print with filter given the passed values
					break;
				case 's':
					updateBook(arg1, arg2, arg3); // case of set 3 argument {isbn,attribute,value}
					break;
				case 'g':
					getBookAttrib(arg1, arg2); // case of get only 1 argument {isbn}
					break;
				case 'p': 
					printAllBooksInLibrary(); // print all books in the arraylist
					break;
				case 'q':
					System.out.println("exiting");
					break;
				default:
					System.out.println("invalid option");
					break;
			}
		}while(action!='q');
	}

}
