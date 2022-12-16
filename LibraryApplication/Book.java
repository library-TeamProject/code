
/**
 * 여기에 Book 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
public class Book
{

    private String author;
    private String title;
    private Borrower borrower;
    private int catalogNumber;

    public Book(String title,String author,int catalogNumber)
    {
        this.author = author;
        this.title = title;
        this.catalogNumber = catalogNumber;
    }

    public boolean loanableBook(){
        if(this.borrower == null){
            return true;// able to loan
        }
        else{
            return false;// unable to loan
        }
    }

    public Object returnBorrower(){
        return borrower;
    }

    public void attachBorrower(Borrower borrower){
        this.borrower = borrower;
    }

    public void detachBorrower()
    {
        this.borrower = null;
    }

    public int getcatalogNumber(){
        return catalogNumber;
    }

    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
}
