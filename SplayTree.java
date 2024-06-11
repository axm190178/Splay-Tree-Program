import java.util.Scanner;

public class SplayTree {
    
    
    public static void main(String[] args) {
        
    //Create variables
     Scanner input = new Scanner(System.in);
     int choice = 0;
     int num;
     int nodes;

     Node pass = null;

     //Get the user to input th eamount of nodes
     System.out.println("Enter the amount of nodes: ");
     nodes = input.nextInt();

     SplayTree splayTree = new SplayTree();

     //Create a for loop with the user input of nodes as a boundary and pass each individual node to insert func
     for(int i = 1; i <= nodes; i++){

        pass = splayTree.insert(pass, i);
    }

     //Create a while loop and display the choices 
    while(choice != 5){
     System.out.println("1.Insertion");
     System.out.println("2.Search");
     System.out.println("3.Deletion");
     System.out.println("4.Display ");
     System.out.println("press 5 to quit");
     System.out.println("Choose an operation");
     choice = input.nextInt();

     //If 1 is chosen insert user inout for integer and pass it on to insert func
     if (choice == 1){
        System.out.println("Enter the integer: ");
        num = input.nextInt();
        
        pass = splayTree.insert(pass, num);
     }

     //if 2 is chosen insert user input for integer and pass it on to search func
     if (choice == 2){

        System.out.println("Enter the integer: ");
        num = input.nextInt();

        pass = splayTree.search(pass,num);
     }

     //if 3 is chosen get integer and input and pass it on to delete func
     if (choice == 3){

        System.out.println("Enter the integer: ");
        num = input.nextInt();

        pass = splayTree.delete(pass, num);

     }

     //if 4 is chosen display the preorder traversal
     if(choice == 4) {

        splayTree.display(pass, true, false);
        System.out.println("");

     }
     
     //if 5 is chosen end prog
     if (choice == 5){

        return;
        
     }
    }

    
   }
 
    //This function will splay the tree
    public  Node splayingOp(Node root, int data){   

        //if the root is null or data match return the root
        if (root == null || root.data == data){
        
            return root;
        }

        //if the data is the root is greater check multiple tests
        if (root.data > data){
            
            //if root.left is null return root
            if (root.left == null){ 

                return root;
            }

            //if data in the left of root is greater 
            if (root.left.data > data){
                
                //call the splaying op and set root.left.left to the val that comes out
                root.left.left = splayingOp(root.left.left, data);

                //rotate the root to the right
                root = root.rotateRight();
            }   

            //if left root is less than the data call the splaying again and pass the diff value
            else if (root.left.data < data){
        
                root.left.right = splayingOp(root.left.right, data);

                //is not null rotate root.left to the left
                if (root.left.right != null){

                    root.left = root.left.rotateLeft();
                }
            }

            //if root.left is null return the root
            if(root.left == null){

                return root;
            }

            //else rotate to the right
            else{
                root = root.rotateRight();
            }   
        }

        //if root.right is null return the root
        else {

            if (root.right == null){

            return root;
        } 

        //if root right is greater than the data then call the splaying op func and pass root.right.left
        if (root.right.data > data){
        
            root.right.left = splayingOp(root.right.left, data);

            //if not null set root.right to the right rotated val
            if (root.right.left != null){

                root.right = root.right.rotateRight();
            }
        }

        //if root.right is less than data call the func and pass the val
        else if (root.right.data < data){

            root.right.right = splayingOp(root.right.right, data);

            //rotate the root to the left
            root = root.rotateLeft();
        }

        //if root.right is null return the root
        if (root.right == null){

            return root;
        }
        //else rotate to the left
        else{

            root = root.rotateLeft();
        }
    }
    
    return root;

    }
    
    //This function will search for a node and set it as the root
    public  Node search(Node root, int data){

        //call the splaying method function and return it
        return splayingOp(root, data);
    }

    //This function will delete a number in the treee
    public  Node delete(Node root, int data){

        //Create a new node and initialize it
        Node curr = null;

        //set root equal to the new root once passed through the splaying op
        root = splayingOp(root, data);

        //if data is does match return root
        if(data != root.data){
            return root;
        }

        //if root.left is null set curr to the root and root to root.right
        if(root.left == null){
            curr = root;
            root = root.right;
        }

        //if root.left is not null set root to curr
        else{

            curr = root;

            //set root equal to the new root after calling the splaying function
            root = splayingOp(root.left, data);

            //set root.right to curr.right
            root.right = curr.right;
        }

        //return root
        return root;
    }

    //This function will insert a number 
    public  Node insert(Node root, int a){

        //if the root is null return the num in the next node
        if (root == null){

            return new Node(a);
        }
        
        //splay the root
        root = splayingOp(root, a);
        
        //if the root is equal to the input num return the root
        if (root.data == a){

            return root;
        }
        
        //create a new node
        Node nextNode = new Node(a);
        
        if (root.data > a){

            //set the right of the node as the root
            nextNode.right = root;
            //set the left of the next node as root.left
            nextNode.left = root.left;
            //set root.left to null
            root.left = null;
        }

        //if the root is not greater than user input
        else{
            //set left of the next node to the root
            nextNode.left = root;
            //set the node.right in the other node to root.right
            nextNode.right = root.right;
            //set root.right to null
            root.right = null;
        }
        
        //return the diff node created
        return nextNode; 
    }

    //This function will display the final preorder traversal
    public  void display(Node root, boolean rootNode, boolean leftNode){

        //if root is not null
        if(root != null){

            //display the data
            System.out.print(root.data);
            //if the num is the rot of the node display RT
            if(rootNode)
                System.out.print("RT");
            //if the num is left tree print L
            else if(leftNode)
                System.out.print("L");
            //else print R for right tree
            else if(!leftNode)
                System.out.print("R");
            System.out.print(", ");

            //display 
            display(root.left, false, true);
            display(root.right, false, false);
        }
    
    }

}
