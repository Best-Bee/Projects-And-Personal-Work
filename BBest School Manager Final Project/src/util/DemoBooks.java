package util;

public class DemoBooks {

	public static void main(String[] args) {
		String[][] books = Utilities.emitTitleAndIsbn("rawData/Textbooks/textbook_titles.txt", "rawData/Textbooks/textbook_isbns.txt");
	
		for(int i = 0; i< books.length; i++) {
			for(int j = 0; j < books[i].length; j++) {
				System.out.print(books[i][j] + " ");
			}
			System.out.println();
		}
		
		
	}

}