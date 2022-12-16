import java.util.*;

public class Library {
    String name;

    private  static TreeSet<Book> regiseterdBooks;
    private  static HashSet<Borrower> registerdBorrowers;
    private  static LinkedList<Loan> registeredLoans;
    public Library(String name){
        this.name = name;
        regiseterdBooks = new TreeSet<Book>();
        registerdBorrowers = new HashSet<Borrower>();
    }
    public String registerOneBorrower(String name){
        boolean result =  registerdBorrowers.contains(name);
        if(!result) {
            Borrower borrower = new Borrower(name);
            if (registerdBorrowers.add(borrower)) {
                return "Borrower registration completed"; // borrower collection?�� ?��?��?�� ?���?
            }
        }
        return "registered borrower"; //?��?��?���? ?��록되?�� ?��?��.
    }
    public String registerOneBook(String title, String author){
        int catalogNumber =  regiseterdBooks.size();
        regiseterdBooks.add(new Book(title,author,catalogNumber));
        return "book registration completed";
    }
    public void displayBookForLoan(){
        Iterator it = regiseterdBooks.iterator();
        while(it.hasNext()){
            Book book = (Book)it.next();
            if(book.loanableBook()==true){
                System.out.println(book.getTitle()+" "+book.getAuthor());
                //책들?�� ??출상?���? loanableBook?���? ?��?��?���? true?�� 경우 ?��?�� 책들?�� ?��보�?? 출력?��?��.
            }
        }

    }

    public void displayBookOnLoan() {
        Iterator it = regiseterdBooks.iterator();
        while(it.hasNext()){
            Book book = (Book)it.next();
            if(book.loanableBook()==false){
                System.out.println(book.getTitle()+" "+book.getAuthor());
                //책들?�� ??출상?���? loanableBook?���? ?��?��?���? false?�� 경우 ?��?�� 책들?�� ?��보�?? 출력?��?��.
            }
        }
    }

    public String lendOneBook(String name,String title,String author,int catalogNumber){
        //책을 ??�? ?��?�� 책이 ?��?���? ?��?��?���? ?��록되?�� ?��?���? ?��?��?��, 책이 ??출�??��?���?, ?��?��?���? 책을 ??�? ?��?��
        //?��?���?�? ?��?��?��?�� 코드
        boolean borrowerFlag = registerdBorrowers.contains(name);
        boolean bookFlag = regiseterdBooks.contains(catalogNumber);

        if(!borrowerFlag && !bookFlag){
            return "unregistered borrower and unregistered book";
        }
        else if(!borrowerFlag && bookFlag){
            return "unregistered borrower";
        }
        else if(borrowerFlag&&!bookFlag){
            return "unregistered book";
        }
        else{
            //책이 ?��?���??�� ?��록되?���?, ?��?��?���? ?��?��?��?�� ?��록되?�� ?��?���? ?��쪽으�? ?��?��처리 ?��?��.
            Borrower borrower = (Borrower) getBorrower(name); // Iterator�? ?��?��?�� 메소?��
            Book book = (Book) getBook(catalogNumber); // Iterator�? ?��?��?�� 메소?��

            boolean loanAbleBorrower = borrower.loanableBorrower();
            boolean loanAbleBook = book.loanableBook();

            if(!loanAbleBorrower&&!loanAbleBook){
                return "borrower on loan and book on loan ";
            }
            else if(!loanAbleBorrower&&loanAbleBook){
                return "borrower on loan";
            }
            else if(loanAbleBorrower&&!loanAbleBook){
                return "book on loan";
            }
            else{
                borrower.attachBook(book);
                book.attachBorrower(borrower);

                Loan loan = new Loan(book, borrower); //책이 ??출이 �??��?���?�? loan 객체�? ?��?��?��?��.
                if(registeredLoans.add(loan)){
                    return "loan competion";
                };

            }
        }
        return "lend competion";
    }
    public String returnOneBook(String name,String author,int catalogNumber){
        //반납?��?�� 기능?�� ?��?�� ?��?�� 반납?�� 책의 바코?���? 찍으�? ?��?��?���? name,author,catalogNumber�? ?��?��?��?��.
        //?��?��?�� ?��보들�? book객체�? ?��?��?���? �? 책이 regiseterdBooks Collection?�� ?��?���? ?��?��?��?��.
        Book book = new Book(name,author,catalogNumber);
        boolean bookFlag = regiseterdBooks.contains(book); //책을 반납?��?�� 코드 book객체�? Book Collection?�� ?��?���? ?��?��
        if(bookFlag){
            book =(Book) getBook(catalogNumber);
            boolean loanAbleBook = book.loanableBook();
            if(!loanAbleBook){
                //책이 ??�? 불�??�� ?��?��?���?
                Borrower borrower =(Borrower) book.returnBorrower();
                book.detachBorrower();
                borrower.detachBook();
                return "Return one book completed";
            }else{
                return "loanable Book";
            }
        }
        else{
            return "Unregistered book";
        }
    }
    public Object getBook(int catalogNumber){
        //Iterator?�� ?��?��?��?�� book 객체�? 찾아?��?��.
        Book result = null;
        Iterator it = regiseterdBooks.iterator();
        while(it.hasNext()){
            Book book = (Book)it.next();
            int bookCatalogNumber = book.getcatalogNumber();
            if(bookCatalogNumber == catalogNumber){
                result = book;
                break;
            }
        }
        return result;
    }
    public Object getBorrower(String name){
        //Iterator?�� ?��?��?��?�� ?��?��?��?�� borrower 객체�? 찾아?��?��.
        Borrower result = null;
        Iterator it = registerdBorrowers.iterator();
        while(it.hasNext()){
            Borrower borrower = (Borrower)it.next();
            String borrowerName = borrower.getName();
            if(borrowerName.equals(name)){
                result = borrower;
                break;
            }
        }
        return result;
    }
}