/** Spider.java **/
package Google;
import java.util.*;

import Google.SpiderLeg;
import Google.PageRank;

import static Google.PageRank.printSinglePage;

public class Spider{
    private static final int MAX_PAGES_TO_SEARCH = 30;
    private Set<String> pagesVisited = new HashSet<String>();
    private List<String> pagesToVisit = new LinkedList<String>();

    /**
     * Our main launching point for the Spider's functionality. Internally it creates spider legs
     * that make an HTTP request and parse the response (the web page).
     *
     * @param url
     *            - The starting point of the spider
     * @param searchWord
     *            - The word or string that you are searching for
     */
    public void search(String url, String searchWord, RBTree T)
    {
        while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH) //MAX_PAGES_TO_SEARcH
        {
            String currentUrl;
            SpiderLeg leg = new SpiderLeg();
            if(this.pagesToVisit.isEmpty())
            {
                currentUrl = url;
                this.pagesVisited.add(url);

            }
            else
            {
                currentUrl = this.nextUrl();
            }

            leg.crawl(currentUrl); // Lots of stuff happening here. Look at the crawl method in


            // SpiderLeg
            boolean success = leg.searchForWord(searchWord);
            if(success)
            {
                System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));

                break;
            }

            this.pagesToVisit.addAll(leg.getLinks());

        }
        // get the links from pagesVisited
        ArrayList<String> URL = new ArrayList<String>(pagesVisited);
        System.out.print("Original 30 search results:\n");
        System.out.print("  Color   PageRank   Index               URL\n");

        for (int i = 0; i < 30; i++){
            //Add a page to the list and assign a random score to it
            PageRank page = new PageRank();
            page.setRandomScore();
            page.setURL(URL.get(i));
            page.setIndex(i);
            printSinglePage(page); // print original page before adding it to tree
            T.RB_Insert(page);
        }

        System.out.println("\n**Done** Visited " + this.pagesVisited.size() + " web page(s)\n");
    }



    /**
     * Returns the next URL to visit (in the order that they were found). We also do a check to make
     * sure this method doesn't return a URL that has already been visited.
     *
     * @return
     */
    private String nextUrl()
    {
        String nextUrl;
        do
        {
            nextUrl = this.pagesToVisit.remove(0);
        } while(this.pagesVisited.contains(nextUrl));
        this.pagesVisited.add(nextUrl);
        return nextUrl;
    } // T(n) = O(n)


}