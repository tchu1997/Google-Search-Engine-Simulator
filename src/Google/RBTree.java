/** RBTree.java **/
package Google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * Class RBTree creates a RBTree object that contains of PageRank objects.
 */
public class RBTree extends PageRank {
    private PageRank root;
    private PageRank nil; // nil is black
    private int rank = 1;
    private int size;

    /**
     * Default constructor. Each time it is called,
     * a new RB tree is created and it has only a nil node.
     */
    public RBTree(){
        nil = new PageRank();
        nil.setColor("black");
        root = nil;
        root.setLeft(nil);
        root.setRight(nil);
        root.setParent(nil);
        size = 0;
    }

    /**
     * In the RB Tree, find the smallest node in the left subtree of a node
     * @param x A PageRank object
     * @return
     */
    public PageRank Tree_Miminum(PageRank x){
        while (x.getLeft() != nil){
            x = x.getLeft();
        }
        return x;
    }

    /**
     * Left rotate on a node in the RB Tree
     * @param x The PageRank object to be rotated
     */
    public void Left_Rotate(PageRank x){
        // code is based on pseudo code from the textbook
            PageRank y = x.getRight();
            x.setRight(y.getLeft()); // turn y's left subtree into x's right subtree
            if (y.getLeft() != nil) {
                y.getLeft().setParent(x);
            }
            y.setParent(x.getParent());
            if (x.getParent() == nil) {
                root = y;
            } else if (x == x.getParent().getLeft()) {
                x.getParent().setLeft(y);

            } else {
                x.getParent().setRight(y);
            }
            y.setLeft(x);
            x.setParent(y);

    }

    /**
     * Right totate on a node in the RB Tree
     * @param x The PageRank object to be rotated
     */
    public void Right_Rotate( PageRank x){
        // code is based on pseudo code from the textbook
        PageRank y = x.getLeft();
        x.setLeft(y.getRight()); // turn y's right subtree into x's left subtree
        if (y.getRight() != nil) {
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == nil) {
            root = y;
        } else if (x == x.getParent().getRight()) {
            x.getParent().setRight(y);

        } else {
            x.getParent().setLeft(y);
        }
        y.setRight(x);
        x.setParent(y);
    }

    /**
     * Fix the colors of the RB Tree if the RB Tree's properties are violated when
     * a new node is added.
     * @param z a newly added PageRank object
     */
    public void RB_Insert_FixUp(PageRank z){
        // code is based on pseudo code in the textbook
            PageRank y = nil;
            while (z.getParent().getColor().equals("red")) {
                if (z.getParent() == z.getParent().getParent().getLeft()) {
                    y = z.getParent().getParent().getRight();
                    // case 1: y's uncle is red
                    if (y.getColor().equals("red")) {
                        z.getParent().setColor("black");
                        y.setColor("black");
                        z.getParent().getParent().setColor("red");
                        z = z.getParent().getParent();
                    }
                    // case 2: y is black, z is y's right child
                    else if (z == z.getParent().getRight())
                    {
                        z = z.getParent();
                        Left_Rotate(z);
                    }
                    // case 3: y is black, z is y's left child
                    else {
                        z.getParent().setColor("black");
                        z.getParent().getParent().setColor("red");
                        Right_Rotate(z.getParent().getParent());
                    }

                } else {
                    y = z.getParent().getParent().getLeft();
                    //case 1
                    if (y.getColor().equals("red")) {
                        z.getParent().setColor("black");
                        y.setColor("black");
                        z.getParent().getParent().setColor("red");
                        z = z.getParent().getParent();
                    }
                    // case 2
                    else if (z == z.getParent().getLeft()) {
                        z = z.getParent();
                        Right_Rotate(z);
                    }
                    // case 3
                    else{
                        z.getParent().setColor("black");
                        z.getParent().getParent().setColor("red");

                        Left_Rotate(z.getParent().getParent());
                    }
                }
            }
            root.setColor("black"); //recolor root
    }

    /**
     * Insert a node to the RB Tree
     * @param z a PageRank object
     */
    public  void RB_Insert( PageRank z){
        // code is derived from the textbook
            z.setIndex(size++); // set index for each newly added object
            PageRank y = nil;
            PageRank x = root;
            while (x != nil ) {
                y = x;
                if (z.getScore() < x.getScore()) {
                    x = x.getLeft();
                } else {
                    x = x.getRight();
                }
            }
            z.setParent(y);
            if (y == nil) {
                root = z;
            } else if (z.getScore() < y.getScore()) {
                y.setLeft(z);
            } else {
                y.setRight(z);
            }
            z.setLeft(nil); // set newly added object's left child to nil
            z.setRight(nil); // set newly added object's right child to nil
            z.setColor("red"); // newly added object's color is red
            RB_Insert_FixUp(z);
    }

    /**
     * Contains Inorder_Tree_Walk(), but returns rank to 1 when finished.
     */
    public void PrintTree() {
        System.out.print("  Color   PageRank  Index                      URL\n");
        Inorder_Tree_Walk(root);
        rank = 1; // rank returns to 1 after Inorder_Tree_Walk finishes
    }

    /**
     *  Print the RB Tree in a decreasing order (from biggest to smallest node)
     * @param x Reference of the RB Tree
     */
    public void Inorder_Tree_Walk(PageRank x){
            // print in a decreasing order
            if (x != nil) {
                Inorder_Tree_Walk(x.getRight());
                // set rank number for each object
                x.setRank(rank++); // increments rank by 1
                printSinglePage(x);
                Inorder_Tree_Walk(x.getLeft());
            }
    }

    /**
     * Check if RB Tree is empty or not
     * @return True or false
     */
    public boolean isEmpty(){
        if (root == nil){
            return true;
        }
        return false;
    }

    /**
     * Search for a PageRank object in the RB Tree based on rank
     * @param root Reference of a RB Tree
     * @param k A rank number
     * @return a PageRank object
     */
    public PageRank Tree_Search(PageRank root,int k) {
        // code is derived from the textbook
        if (root == nil | k == root.getRank()) {
            return root;
        }
        if (k < root.getRank()) {
            return Tree_Search(root.getRight(), k);
        } else {
            return Tree_Search(root.getLeft(), k);
        }
    }

    /**
     * Search for a Web page in the 30 Web pages
     */
    public void SearchPage(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the rank of a page you want to find: ");
        String s = input.next();
        PageRank page = Tree_Search(root, Integer.valueOf(s)); // get the page
        System.out.print("  Color   PageRank  Index       URL\n");
        printSinglePage(page); // print the page
    }

    /**
     * Used for RB_Delete method
     * @param u A PageRank object whose subtrees will be replaced
     * @param v A PageRank object whose subtrees will replace u's subtrees
     */
    public void RB_Transplant(PageRank u, PageRank v){
            if (u.getParent() == nil) {
                root = v;
            } else if (u == u.getParent().getLeft()) {
                u.getParent().setLeft(v);
            } else {
                u.getParent().setRight(v);
            }
            v.setParent(u.getParent());
    }

    /**
     * Fix the colors of the RB Tree when the properties are violated when a node is deleted
     * @param x a PageRank object to be deleted
     */
    public void RB_Delete_FixUp(PageRank x){
        // code is derived from the text book
        PageRank w;
        while(x!=root && x.getColor().equals("black")) {
            if (x == x.getParent().getLeft()) {
                w = x.getParent().getRight();
                //case 1: x's sibling is red and has black children
                if (w.getColor().equals("red")) {
                    w.setColor("black");
                    x.getParent().setColor("red");
                    Left_Rotate(x.getParent());
                    w = x.getParent().getRight();
                }
                //case 2: x's sibling is black and its children are black
                if (w.getLeft().getColor().equals("black") && w.getRight().getColor().equals("black")) {
                    w.setColor("red");
                    x = x.getParent();
                }
                //case 3: x's sibling is black and its left child is red, right child is black
                else {
                    if (w.getRight().getColor().equals("black")) {
                        w.getLeft().setColor("black");
                        w.setColor("red");
                        Right_Rotate(w);
                        w = x.getParent().getRight();
                    }
                    //case 4: x's sibling is black and its left child is black, right child is red
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor("black");
                    w.getRight().setColor("black");
                    Left_Rotate(x.getParent());
                    x = root;
                }
            } else {
                w = x.getParent().getLeft();
                //case 1
                if (w.getColor().equals("red")) {
                    w.setColor("black");
                    x.getParent().setColor("red");
                    Right_Rotate(x.getParent());
                    w = x.getParent().getLeft();
                }
                //case 2
                if (w.getRight().getColor().equals("black") && w.getLeft().getColor().equals("black")) {
                    w.setColor("red");
                    x = x.getParent();
                }
                //case 3
                else {
                    if (w.getLeft().getColor().equals("black")) {
                        w.getRight().setColor("black");
                        w.setColor("red");
                        Left_Rotate(w);
                        w = x.getParent().getLeft();
                    }
                    //case 4
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor("black");
                    w.getLeft().setColor("black");
                    Right_Rotate(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor("black"); // set x to black
    }

    /**
     * Delete a node from the RB Tree
     * @param z A PageRank object to be deleted
     */
    public void RB_Delete(PageRank z){
        // code is derived from text book
            PageRank y = z;
            PageRank x = nil;
            y.setOriginColor(y.getColor());
            if (z.getLeft()== nil) {
                x = z.getRight();
                RB_Transplant(z, z.getRight());
            } else if (z.getRight() == nil) {
                x = z.getLeft();
                RB_Transplant(z, z.getLeft());
            } else {
                y = Tree_Miminum(z.getRight());
                y.setOriginColor(y.getColor());
                x = y.getRight();
                if (y.getParent() == z) {
                    x.setParent(y);
                } else {
                    RB_Transplant(y, y.getRight());
                    y.setRight(z.getRight());
                    y.getRight().setParent(y);
                }
                RB_Transplant(z, y);
                y.setLeft(z.getLeft());
                y.getLeft().setParent(y);
                y.setColor(z.getColor());
            }
            if (y.getOriginColor().equals("black")) {
                RB_Delete_FixUp(x);
            }
    }

    /**
     * Add a new Web page to the list of 30
     */
    public void AddPage(){
        Scanner input = new Scanner(System.in);
        System.out.print("Add a page (URL: ");
        String s = input.next();
        System.out.print("Enter 4 factors: \n");
        System.out.print("Factor 1: ");
        int f1 = Integer.valueOf(input.next());
        System.out.print("Factor 2: ");
        int f2 = Integer.valueOf(input.next());
        System.out.print("Factor 3: ");
        int f3 = Integer.valueOf(input.next());
        System.out.print("Factor 4: ");
        int f4 = Integer.valueOf(input.next());

        PageRank page = new PageRank(); // new PageRank object to be added
        page.setURL(s); // set url for the object
        List<Integer> f = new ArrayList<>();
        f = Arrays.asList(f1,f2,f3,f4);
        page.setFactors(f);// set factors
        page.setLeft(nil);
        page.setRight(nil);
        page.setParent(nil);

        RB_Insert(page); //insert to Tree
    }

    /**
     * Delete a Web page from the list of 30 based
     */
    public void DeletePage(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the rank of a page you want to delete: ");
        String s = input.next(); // get the rank of the page we want to delete
        PageRank page = Tree_Search(root, Integer.valueOf(s)); // retrieve the page
        System.out.print("Deleting... :( \n");
        System.out.print("  Color   PageRank   Index               URL\n");
        printSinglePage(page); // print the page
        RB_Delete(page); // delete the page
        System.out.print("Page is deleted.\n");
    }

    /**
     * Set the size of a RB Tree
     * @param i An integer
     */
    public void setSize(int i){
        size = i;
    }

}
