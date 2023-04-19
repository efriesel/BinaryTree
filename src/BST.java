import java.util.ArrayList;
import java.util.Stack;

/**
 * An Integer Binary Search Tree
 * @author: Ethan Friesel
 * @version: 4/19/23
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     * 4/7/23 Ethan changed to make a more complex tree
     */
    public void setupTestData() {
        this.root = new BSTNode(8);
        this.root.setLeft(new BSTNode(3));
        this.root.setRight(new BSTNode(10));
        this.root.getLeft().setLeft(new BSTNode(1));
        this.root.getLeft().setRight(new BSTNode(6));
        this.root.getLeft().getRight().setLeft(new BSTNode(4));
        this.root.getLeft().getRight().setRight(new BSTNode(7));
        this.root.getRight().setRight(new BSTNode(14));
        this.root.getRight().getRight().setLeft(new BSTNode(13));

//        this.root = new BSTNode(10);
//        this.root.setLeft(new BSTNode(5));
//        this.root.setRight(new BSTNode((15)));
//        this.root.getLeft().setLeft(new BSTNode(3));
//        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree, calling recursive subSearch() method
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val) {
        return subSearch(getRoot(), val);
    }

    /**
     * This method will search a node and returning true iff the value is found, using recursion
     * @param n the node in question
     * @param val the value that is being searched for
     * @return false if the given node is null or if the val is equal to the nodes value (base cases)
     */
    public boolean subSearch(BSTNode n, int val){
        if (n == null)
            return false;
        if (n.getVal() == val)
            return true;
        else if (n.getVal() < val)
            return subSearch(n.getRight(), val);
        else
            return subSearch(n.getLeft(), val);
    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder() {
        ArrayList<BSTNode> order = new ArrayList<>();
        runGetInorder(getRoot(), order);
        return order;

    }
    /**
     * this method will add each node to the list using the inorder format
     * this method uses recursion
     * @param current the inputted current node
     * @param order the arraylist that objects will be added to
     * order will be added to after the left node is aquired, in order to accurately traverse from left to right
     * over the full tree
     */

    public void runGetInorder(BSTNode current, ArrayList<BSTNode> order) {
        if (current.getLeft() != null)
            runGetInorder(current.getLeft(), order);
        order.add(current);
        if (current.getRight() != null)
            runGetInorder(current.getRight(), order);
    }

    /**
     * @return ArrayList of BSTNodes in preorder
     * uses recursive runGetPreorder() method to do so
     */
    public ArrayList<BSTNode> getPreorder() {
        ArrayList<BSTNode> order = new ArrayList<>();
        runGetPreorder(getRoot(), order);
        return order;
    }

    /**
     * this method will add each node to the list using the preorder format
     * this method uses recursion
     * @param current the inputted current node
     * @param order the arraylist that objects will be added to
     * order will be added to first because the preorder will go down and add parent nodes first
     */
    public void runGetPreorder(BSTNode current, ArrayList<BSTNode> order) {
        order.add(current);
        if (current.getLeft() != null)
            runGetPreorder(current.getLeft(), order);
        if (current.getRight() != null)
            runGetPreorder(current.getRight(), order);
    }

    /**
     * @return ArrayList of BSTNodes in postorder
     */
    public ArrayList<BSTNode> getPostorder() {
        ArrayList<BSTNode> order = new ArrayList<>();
        runGetPostorder(getRoot(), order);
        return order;
    }
    /**
     * this method will add each node to the list using the postorder format
     * this method uses recursion
     * @param current the inputted current node
     * @param order the arraylist that objects will be added to
     * the order will be added to last because the child will be first (to the left then right)
     */
    public void runGetPostorder (BSTNode current, ArrayList<BSTNode> order){
        if (current.getLeft() != null)
            runGetPostorder(current.getLeft(), order);
        if (current.getRight() != null)
            runGetPostorder(current.getRight(), order);
        order.add(current);
    }

    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    public void insert(int val) {
        this.setRoot(runInsert(getRoot(), val));
    }
    public BSTNode runInsert(BSTNode current, int val){
        if (current == null)
            return new BSTNode(val);
        if (current.getVal() > val)
            current.setLeft(runInsert(current.getLeft(), val));
        else
            current.setRight(runInsert(current.getRight(), val));
        return current;
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        // TODO: Optional Challenge!
        return false;
    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(9);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);
    }
}
