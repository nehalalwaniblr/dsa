package striver.binary_search;

/*https://takeuforward.org/data-structure/allocate-minimum-number-of-pages*/
public class BookAllocation {
    public static void main(String[] args) {
        BookAllocation bookAllocation = new BookAllocation();
        int[] pages = new int[]{10, 20, 30, 40};
        int students = 2;
        System.out.println(bookAllocation.bookAllocation(pages, students)); //60

        pages = new int[]{10, 20, 30, 40};
        students = 3;
        System.out.println(bookAllocation.bookAllocation(pages, students)); //40

        pages = new int[]{5, 17, 100, 11};
        students = 4;
        System.out.println(bookAllocation.bookAllocation(pages, students)); //100

        pages = new int[]{12, 34, 67, 90};
        students = 2;
        System.out.println(bookAllocation.bookAllocation(pages, students)); //113

        pages = new int[]{25, 46, 28, 49, 24};
        students = 4;
        System.out.println(bookAllocation.bookAllocation(pages, students)); //71

    }

    public int bookAllocation(int[] pages, int students) {
        // Find maximum pages in a single book and total pages
        // Range lies between maxPages and totalPages
        if (students > pages.length)
            return -1;
        int maxPages = 0;
        int totalPages = 0;
        for (int page : pages) {
            maxPages = Math.max(maxPages, page);
            totalPages += page;
        }
        int left = maxPages;
        int right = totalPages;
        for (int i = left; i <= right; i++) {
            if (numberOfStudents(pages,i)==students) {
                return i;
            }
        }
        return left;
    }

    private int numberOfStudents(int[] pages, int maxPages) {
        int student =1;
        int currentPages=0;
        for(int i =0;i<pages.length;i++){
            if(currentPages+pages[i]<=maxPages){
                currentPages+=pages[i];
            }else {
                student++;
                currentPages=pages[i];
            }
        }
        return student;
    }
}
