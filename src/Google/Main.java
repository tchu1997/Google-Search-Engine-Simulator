/** Main.java **/
package Google;
import Google.Spider;
import Google.PageRank;
import Google.RBTree;
import java.util.ArrayList;
import java.util.Scanner;

/** Main class**/
public class Main{
    /**
     * Search for a keyword, retrieve 30 urls and build the RBTree
     * @param T Red-Black Tree
     */
    public static void Search(RBTree T){
        Scanner url = new Scanner(System.in);
        System.out.print("Enter a keyword: ");
        String word = url.next();
        System.out.print("Searching for " + word + "...\n");

        Spider spider = new Spider();
        // get 30 URLS and build RBTree
        spider.search("https://www.alexa.com/topsites", word, T);
    }

    /**
     * List of 6 options for the user to choose
     * @return An integer
     */
    public static int Options(){
        Scanner input = new Scanner(System.in);
        System.out.print("****************************\n");
        System.out.print("1-Search a keyword\n");
        System.out.print("2-Print sorted results\n");
        System.out.print("3-Add a page\n");
        System.out.print("4-Delete a page\n");
        System.out.print("5-Search for a page\n");
        System.out.print("6-Exit\n");
        System.out.print("****************************\n");
        System.out.print("Choose: ");

        String choice = input.next();
        return Integer.valueOf(choice);
    }
    /*************************/
    /******Main Program ******/
    /*************************/
    public static void main(String[] args) {
        RBTree Tree = new RBTree(); // new RB Tree
        boolean flag = true;
        while(flag) {
            int choice = Options();
            // case 1, build tree
            if (choice == 1) {
                Tree.setSize(0);
                Search(Tree); // build tree
            }
            //case 2, print sorted Tree
            else if (choice == 2){
                if (Tree.isEmpty())
                    System.out.print("tree is empty.\n");
                else
                    Tree.PrintTree();
            }
            //case 3, add a page
            else if(choice == 3){
                Tree.AddPage(); // add a page to the tree
            }
            //case 4, delete a page
            else if(choice == 4){
                if (Tree.isEmpty()) // if tree is empty
                    System.out.print("tree is empty.\n");
                else
                    Tree.DeletePage();
            }
            //case 5, search for a page
            else if(choice == 5){
                Tree.SearchPage();
            }
            // case 6, exits
            else if (choice == 6) {
                flag = false;
                System.out.print("Exit the program.\n");
            }
            else{
                System.out.print("Invalid input!!\n");
            }
        }
    }
}