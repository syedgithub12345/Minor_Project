import java.util.*;

class Node { 
    int key, height; 
	Node left, right; 
    Node(){}
    Node(Node node){}
	Node(int d) { 
        key = d; 
		height = 1; 
	} 
} 

public class AVL_Tree_3 { 

	Node root; 

	int height(Node N) { 
		if (N == null) 
			return 0; 

		return N.height;  
	} 

	int max(int a, int b) { 
	    if(a>b){
	        return a;
	    }
	    else return b;
		//return (a > b) ? a : b; 
	} 

	Node rightRotate(Node y) { 
		Node x = y.left; 
		Node T2 = x.right; 

		x.right = y; 
		y.left = T2; 

		y.height = max(height(y.left), height(y.right)) + 1; 
		x.height = max(height(x.left), height(x.right)) + 1; 

		return x; 
	} 

	Node leftRotate(Node x) { 
		Node y = x.right; 
		Node T2 = y.left; 

		y.left = x; 
		x.right = T2; 

		x.height = max(height(x.left), height(x.right)) + 1; 
		y.height = max(height(y.left), height(y.right)) + 1; 

		return y; 
	} 

	int getBalance(Node N) { 
		if (N == null) 
			return 0; 

		return height(N.left) - height(N.right); 
	} 

	Node insert(Node node, int key) { 

		if (node == null) 
			return (new Node(key)); 

		if (key < node.key) 
			node.left = insert(node.left, key); 
		else if (key > node.key) 
			node.right = insert(node.right, key); 
		else
			return node; 

		node.height = 1 + max(height(node.left), 
							height(node.right)); 
		int balance = getBalance(node); 
		
		if (balance > 1 && key < node.left.key) 
			return rightRotate(node); 

		if (balance < -1 && key > node.right.key) 
			return leftRotate(node); 

		if (balance > 1 && key > node.left.key) { 
			node.left = leftRotate(node.left); 
			return rightRotate(node); 
		} 

		if (balance < -1 && key < node.right.key) { 
			node.right = rightRotate(node.right); 
			return leftRotate(node); 
		} 

		return node; 
	} 

	void inOrder(Node node) { 
		if (node != null) { 
			inOrder(node.left); 
			System.out.print(node.key + " "); 
			inOrder(node.right); 
		} 
	} 
	void preOrder(Node node) { 
		if (node != null) { 
            System.out.print(node.key + " "); 
			preOrder(node.left); 
			preOrder(node.right); 
		} 
	} 
    
    int [] ranArray2(int nums){
        if(nums>30 || nums<0){
            System.out.println("\nInput out of bound");
            return null;
        }
        int[] originalArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        int[] randomArray = new int[nums];
        HashSet<Integer> selectedIndices = new HashSet<>();
        Random random = new Random();
        int index = 0;
        while (index < nums) {
            int randomIndex = random.nextInt(originalArray.length); 
            if (!selectedIndices.contains(randomIndex)) {
                selectedIndices.add(randomIndex);
                randomArray[index] = originalArray[randomIndex];
                index++;
            }
        }
        System.out.println("Random Array of "+nums+" Unique Integers:");
        for (int num : randomArray) {
            System.out.print(num + " ");
        }
        return randomArray;
    }

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        System.out.println("How many trees do you want to create :");
        int numTrees = sc.nextInt();
	    Node[] nodeArr = new Node[numTrees];
		int cnt,j=0,check=0;
		while(check<numTrees){
            System.err.println("\nTree"+(check+1));
            cnt = 1;
		    AVL_Tree_3 tree = new AVL_Tree_3();
		    System.out.println("\nNo. of inputs in the array:");
		    int ranArrNum = sc.nextInt();
		    int[] arr = tree.ranArray2(ranArrNum);
		    if(arr == null){
		        continue;
		    }
		    else{
                for(int i=0;i<arr.length;i++){
                    tree.root = tree.insert(tree.root, arr[i]);
    		        if(cnt == 1){
                        cnt++;
    		        }
                    else{
                        cnt++;
                    }
    		    }
                nodeArr[j] = tree.root;
    		    System.out.println("\nInorder traversal of the AVL tree : ");
    		    tree.inOrder(tree.root);
                j++;
		    }
            check++;
        }

        System.out.println("\nAll trees :");
        for(int i=0;i<nodeArr.length;i++){
            AVL_Tree_3 tree = new AVL_Tree_3();
            System.err.println("\nTree "+(i+1));
            //System.err.println(nodeArr[i].key);
            tree.inOrder(nodeArr[i]);
        }
        sc.close();
	} 
}