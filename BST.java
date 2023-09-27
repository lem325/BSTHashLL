public class BST<E extends Comparable<E>>{
    private TreeNode root;
    private int size;
    public class TreeNode{
        E value;
        TreeNode left;
        TreeNode right;
        TreeNode(E val){
            value = val;
            left = right = null;
        }
    }

    /**
    BST initialization method
    @param none
    Makes root null and size 0
     */
    public BST(){
        root = null;
        size = 0;
    }   

    /**
    size method
    @param none
    returns size of BST
     */
    public int size() {
        return size;
    }
    
    /**
    isEmpty method
    @param none
    returns t/f if size is 0 or nonzero
     */
    public boolean isEmpty(){//O(1)
        return (size == 0);
    }

    /**
    clear method
    @param none
    makes root null
     */
    public void clear() {//O(1)
        root = null;
    }

 // Search method

    /**
    contains method
    @return int 
    @param item E
    returns iterations to find if an item is contained in a bst
     */
    public int contains(E item) { //O(log n) - O(n)
        TreeNode node = root;
        int iterations =0;
        while (node != null) {
            iterations++;
            if( item.compareTo(node.value) < 0)
                node = node.left;
            else if (item.compareTo(node.value)> 0)
                node = node.right;
            else
                return iterations;
        }
        return iterations;
    }


    /**
    Add method
    @return int 
    @param item E
    returns iterations to for adding to a bst
     */
    public int add(E item) { //O(log(n))
        int iterations = 0;
        if (root == null) // first node to be inserted
            root = new TreeNode(item);
        else {
            TreeNode parent, node;
            parent = null; node = root;
            while (node != null) {// Looking for a leaf node
                parent = node;
                iterations++;
                if(item.compareTo(node.value) < 0) {
                    node = node.left; }
                else if (item.compareTo(node.value) > 0) {
                node = node.right; }
                else
                    return iterations; // duplicates are not allowed
            }
        
            if (item.compareTo(parent.value)< 0)
                parent.left = new TreeNode(item);
            else
                parent.right = new TreeNode(item);
        }
        size++;
        return iterations;
    }

/**
    Remove method
    @return int 
    @param item E
    returns iterations to remove item from bst
     */
public int remove(E item) {
    int iterations =0;
TreeNode parent, node;
parent = null; node = root;
 // Find item first
while (node != null) {
    iterations++;
if (item.compareTo(node.value) < 0) {
parent = node;
node = node.left;
}
else if (item.compareTo(node.value) > 0) {
parent = node;
node = node.right;
}
else
break; // item found
}
//method continued on next slide
if (node == null) // item not in the tree
return iterations;
// Case 1: node has no children
if(node.left == null && node.right == null){
if(parent == null){ // delete root
root = null;
}
else{
changeChild(parent, node, null);
}
}
//method continued on next slide
// case 2: one right child
else if(node.left == null){
if (parent == null){ // delete root
root = node.right;
}
else
changeChild(parent, node, node.right);
}
// case 2: one left child
else if(node.right == null){
if (parent == null){ // delete root
root = node.left;
}
else
changeChild(parent, node, node.left);
}
//method continued on next slide
// Case 3: node has two children
else {
TreeNode rightMostParent = node;
TreeNode rightMost = node.left;
// go right on the left subtree
while (rightMost.right != null) {
    iterations++;
rightMostParent = rightMost;
rightMost = rightMost.right;
}
 // copy the value of rigthMost to node
node.value = rightMost.value;
//delete rigthMost
 changeChild(rightMostParent, rightMost,
 rightMost.left);
}
size--;
return iterations;}

private void changeChild(TreeNode parent,
 TreeNode node, TreeNode newChild){
if(parent.left == node)
parent.left = newChild;
else
parent.right = newChild;
}

// Recursive method inorder()
public void inorder() {
inorder(root);
}
private void inorder(TreeNode node) {
if (node != null) {
inorder(node.left);
System.out.print(node.value + " ");
inorder(node.right);
}
}

// Recursive method preorder()
public void preorder() {
preorder(root);
}
private void preorder(TreeNode node) {
if (node != null) {
System.out.print(node.value + " ");
preorder(node.left);
preorder(node.right);
}
}

// Recursive method postorder()
public void postorder() {
postorder(root);
}
private void postorder(TreeNode node) {
if (node != null) {
postorder(node.left);
postorder(node.right);
System.out.print(node.value + " ");
}
}

/**
 height method
 @return int
 @param none
 */
    public int height(){
        return height(root);
    }
/**
 height method
 @return int
 @param node TreeNode
 */
    public int height(TreeNode node){
        if(node != null){
            int lHeight = height(node.left);
            int rHeight = height(node.right);
            return 1 + Math.max(lHeight, rHeight);
        }
        return 0;
    }
/**
 isBalanced method
 @return int
 @param none
 */
    public boolean isBalanced(){//O(n^2)
        return isBalanced(root);
    }
/**
 isbalanced method
 @return int
 @param node TreeNode
 */
    public boolean isBalanced(TreeNode node){//O(n^2)
        if(node ==null){
            return true;
        }
        int lHeight=height(node.left); //O(n)
        int rHeight=height(node.right);//O(n)
        int diff = Math.abs(lHeight - rHeight);
        if(diff >1){
            return false;
        }
        
        return isBalanced(node.left) && isBalanced(node.right);
    }

}