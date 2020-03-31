/** PageRank.java **/

package Google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
Class PageRank creates PageRank objects to be inserted into a Red-Black Tree.
 Each object has its key, children, and parent objects
 */
public class PageRank {
    private int index;
    private int rank;
    private String url;
    private int score;
    private List<Integer> factors = new ArrayList<>();
    private PageRank left;
    private PageRank right;
    private PageRank parent;
    private String color, originalColor;

    /**
     * Default constructor
     */
    public PageRank(){
        this.index = 0;
        this.rank = 0;
        this.url = "?";
        this.score = 0;
        this.factors = Arrays.asList(0,0,0,0);
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = "red";
    }

    /**
     * Set the original color of a PageRank object
     * @param s Original color
     */
    public void setOriginColor(String s){originalColor = s;}

    /**
     * Get the original color of a PageRank object
     * @return Original color
     */
    public String getOriginColor(){return originalColor;}

    /**
     * Set URL of a PageRank object
     * @param s URL
     */
    public void setURL(String s){
        this.url = s;
    }

    /**
     * Set random total score of a PageRank object
     */
    public void setRandomScore(){ // set random score for a page
        Random rand = new Random();
        int f1 = rand.nextInt(10) + 1;
        int f2 = rand.nextInt(10) + 1;
        int f3 = rand.nextInt(10) + 1;
        int f4 = rand.nextInt(10) + 1;
        this.factors = Arrays.asList(f1,f2,f3,f4);

    }

    /**
     * Set index of a PageRank object
     * @param i An integer
     */
    public void setIndex(int i){ // set index for a page
        index = i;
    }

    /**
     * Set rank number of a PageRank object
     * @param i An integer
     */
    public void setRank(int i){ // set rank for a page
        rank = i;
    }
    /**
     * Get rank number of a PageRank object
     * @return An integer
     */
    public int getRank(){return rank;}

    /**
     * Get total score of a PageRank object
     * @return An integer
     */
    public int getScore(){return (factors.get(0) + factors.get(1) +
    factors.get(2) + factors.get(3));}

    /**
     * Set four factors of a PageRank object
     * @param f A list of four integers
     */
    public void setFactors(List<Integer> f){ // set a specific score for a page
        this.factors = f;
    }

    /**
     * Print color, rank, index, url, factors, and score of a PageRank object
     * @param z A PageRank object
     */
    public static void printSinglePage(PageRank z){ // print attributes of a page
        System.out.printf("%7s %7s %7s",z.color,z.rank,z.index);
        System.out.print("   " + z.url);
        System.out.print (" Total score: " + z.getScore() );
        System.out.print(" (" + z.factors.get(0) + " ");
        System.out.print(z.factors.get(1) + " ");
        System.out.print(z.factors.get(2) + " ");
        System.out.print(z.factors.get(3) + ")\n");

    }

    /**
     * Get the color of a PageRank object
     * @return A string of "red" or "black"
     */
    public String getColor(){return this.color;}

    /**
     * Set the color of a PageRank object
     * @param s A string of "red" or "black
     */
    public void setColor(String s){this.color = s;}

    /**
     * Get the left child of a PageRank object
     * @return A PageRank object
     */
    public PageRank getLeft(){return this.left;}

    /**
     * Get the right child of a PageRank object
     * @return A PageRank object
     */
    public PageRank getRight(){ return this.right;}

    /**
     * Get the parent of a PageRank object
     * @return A PageRank object
     */
    public PageRank getParent(){return this.parent;}

    /**
     * Set a PageRank object to the left child of another object
     * @param z A PageRank object
     */
    public void setLeft(PageRank z){this.left = z;}

    /**
     * Set a PageRank object to the right child of another object
     * @param z A PageRank object
     */
    public void setRight(PageRank z){this.right = z;}

    /**
     * Set a PageRank object to the parent of another object
     * @param z A PageRank object
     */
    public void setParent(PageRank z){this.parent = z; }

}

