//Create a class to store variables 
public class Node {
    
    int data;
    Node left;
    Node right;

    //initialize the var
    public Node(int data)
    {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    //this function will rotate the node to the right
    public Node rotateRight()
    {
        Node y = this.left;
        this.left = y.right;
        y.right = this;
        return y; 
    }

    //this function will rotate the node to the left
    public Node rotateLeft()
    {
        Node y = this.right;
        this.right = y.left;
        y.left = this;
        
        return y; 
    }
}
