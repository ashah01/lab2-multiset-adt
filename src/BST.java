/**
 * A minimal implementation of a binary search tree. See the python version for
 * additional documentation.
 * You can also see <a href="https://www.teach.cs.toronto.edu/~csc148h/notes/binary-search-trees/bst_implementation.html">
 *     CSC148 Course Notes Section 8.5 BST Implementation and Search</a>
 * if you want a refresher on BSTs, but it is not required to complete this assignment.
 */
public class BST {
    // we use Integer here so that we can set the root to null. This is the same idea as
    // how the Python code uses None in the BST implementation.
    private Integer root;

    private BST left;
    private BST right;

    public BST(int root) {
        this.root = root;
        this.left = new BST();
        this.right = new BST();
    }

    /**
     * Alternate constructor, so we don't have to explicitly pass in null.
     */
    public BST() {
        root = null;
        // left and right default to being null
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean contains(int item) {
        // provided as an example
        if (this.isEmpty()) {
            return false;
        } else if (item == this.root) {
            return true;
        } else if (item < this.root) {
            return this.left.contains(item);
        }
        return this.right.contains(item);
    }

    public void insert(int item) {
        if (this.isEmpty()) {
            // Make new leaf; when non-empty, left/right must be non-null empty BSTs
            this.root = item;
            this.left = new BST();
            this.right = new BST();
        } else if (item <= this.root) {
            this.left.insert(item);
        } else {
            this.right.insert(item);
        }
    }

    public void delete(int item) {
        if (this.isEmpty()) {
            return;
        } else if (this.root == item) {
            this.deleteRoot();
        } else if (item < this.root) {
            this.left.delete(item);
        } else {
            this.right.delete(item);
        }
    }

    private void deleteRoot() {
        // Precondition: non-empty
        if (this.left.isEmpty() && this.right.isEmpty()) {
            // make this tree empty
            this.root = null;
            this.left = null;
            this.right = null;
        } else if (this.left.isEmpty()) {
            // promote right subtree
            this.root = this.right.root;
            this.left = this.right.left;
            this.right = this.right.right;
        } else if (this.right.isEmpty()) {
            // promote left subtree
            this.root = this.left.root;
            this.right = this.left.right;
            this.left = this.left.left;
        } else {
            // both subtrees non-empty: replace root with max of left subtree
            this.root = this.left.extractMax();
        }
    }

    private int extractMax() {
        // Precondition: non-empty
        if (this.right.isEmpty()) {
            int maxItem = this.root;
            // promote left subtree into this node
            if (this.left == null) {
                // should not happen for a non-empty node given our invariants,
                // but guard just in case
                this.root = null;
                this.right = null;
                this.left = null;
            } else {
                this.root = this.left.root;
                this.right = this.left.right;
                this.left = this.left.left;
            }
            return maxItem;
        } else {
            return this.right.extractMax();
        }
    }

    public int height() {
        if (this.isEmpty()) {
            return 0;
        }
        int lh = this.left.height();
        int rh = this.right.height();
        return Math.max(lh, rh) + 1;
    }

    public int count(int item) {
        if (this.isEmpty()) {
            return 0;
        } else if (this.root > item) {
            return this.left.count(item);
        } else if (this.root == item) {
            return 1 + this.left.count(item) + this.right.count(item);
        } else {
            return this.right.count(item);
        }
    }

    public int getSize() {
        if (this.isEmpty()) {
            return 0;
        }
        return 1 + this.left.getSize() + this.right.getSize();
    }

    public static void main(String[] args) {
        // You can also add code here to do some basic testing if you want,
        // but make sure it doesn't contain syntax errors
        // or else we won't be able to run your code on MarkUs since the file won't
        // compile. Always make sure to run the self tests on MarkUs after you update your code.
        BST bst = new BST();
        int a = 1;
        bst.insert(a);
        System.out.println(bst.contains(a));
    }
}

