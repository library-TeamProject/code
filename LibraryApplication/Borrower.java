
/**
 * 여기에 Borrower 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
public class Borrower
{ 
    private String name;
    private Book book;
    public Borrower(String name) {
        this.name = name;
    }
    /*
     */
    public void attachBook(Book book) {
        this.book = book;
    }

    public void detachBook() {
        this.book = null;
    }

    /*
     */
    public boolean loanableBorrower() {
        if (this.book == null) {
            return true;// able to loan
        } else {
            return false;// unable to loan
        }
    }
    public String getName(){

        return name;
    }
}

