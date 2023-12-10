import java.util.*;

class Node { 
    int key, height; 
	Node left, right; 
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

	Node search(Node root, int key) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.key == key)
            return root;
 
        // Key is greater than root's key
        if (root.key < key)
            return search(root.right, key);
 
        // Key is smaller than root's key
        return search(root.left, key);
    }

	ArrayList<Integer> recommedArr = new ArrayList<Integer>();
	Node getChild(Node root, int key) {
		//recommedArr.clear();
        // Base Cases: root is null or key is present at root
        if (root == null || root.key == key)
        {
			if(root.left != null){
				recommedArr.add(root.left.key);
			}
			if (root.right != null){
				recommedArr.add(root.right.key);
			}
			System.out.println(recommedArr);
			return root;
		}
 
        // Key is greater than root's key
        if (root.key < key)
            return getChild(root.right, key);
 
        // Key is smaller than root's key
        return getChild(root.left, key);
    }
	
	ArrayList<Integer> treeArr = new ArrayList<Integer>();
	ArrayList<Integer> getArray(Node node){
		if(node != null){
			getArray(node.left);
			treeArr.add(node.key);
			getArray(node.right);
		}
		return treeArr;
	} 

	float permatch(Node root1, Node root2, ArrayList<Integer> lenArrli, int p1, int p2){
		AVL_Tree_3 tempObj = new AVL_Tree_3();
		ArrayList<Integer> tree1Arr = new ArrayList<Integer>();
		ArrayList<Integer> ansArr = new ArrayList<Integer>();
		tree1Arr = tempObj.getArray(root2);
		for(int i : tree1Arr){
			if(tempObj.search(root1, i) != null){
				ansArr.add(i);
			}
		}
		System.out.println(ansArr);
		Object[] lenArr = lenArrli.toArray();
		int len = (int)lenArr[p1] + (int)lenArr[p2] - ansArr.size();
		float perMatch = (ansArr.size()*100)/len;
		return perMatch;
	}

	ArrayList<Integer> valmatch(Node root1, Node root2, ArrayList<Integer> lenArrli, int p1, int p2){
		AVL_Tree_3 tempObj = new AVL_Tree_3();
		ArrayList<Integer> tree1Arr = new ArrayList<Integer>();
		ArrayList<Integer> ansArr = new ArrayList<Integer>();
		tree1Arr = tempObj.getArray(root2);
		for(int i : tree1Arr){
			if(tempObj.search(root1, i) != null){
				ansArr.add(i);
			}
		}
		return ansArr;
	}

	ArrayList<Integer> recommed(Node root1, Node root2, ArrayList<Integer> lenArrli, int p1, int p2){
		AVL_Tree_3 tempObj = new AVL_Tree_3();
		ArrayList<Integer> ansArr = tempObj.valmatch(root1, root2, lenArrli, p1, p2);
		ArrayList<Integer> finalArr = new ArrayList<Integer>();
		for(int i: ansArr){
			//recommedArr.clear();
			tempObj.getChild(root2, i);
			for(int j:recommedArr){
				finalArr.add(j);
			}
		}
		return finalArr;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        System.out.println("How many trees do you want to create :");
        int numTrees = sc.nextInt();
	    Node[] nodeArr = new Node[numTrees];
		int cnt,j=0,check=0;
		ArrayList<Integer> numArr = new ArrayList<Integer>();
		while(check<numTrees){
            System.err.println("\n\nTree"+(check+1));
            cnt = 1;
		    AVL_Tree_3 tree = new AVL_Tree_3();
		    System.out.println("No. of inputs in the array:");
		    int ranArrNum = sc.nextInt();
			numArr.add(ranArrNum);
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

		AVL_Tree_3 tree1 = new AVL_Tree_3();
		char choiceMatch;
		do{
			tree1.treeArr.clear();
			System.out.println("\n\nCheck percentage match between two users:\nFirst User:");
			int perU1 = sc.nextInt();
			System.out.println("Second User:");
			int perU2 = sc.nextInt();
			//System.out.println("\n"+tree1.getArray(nodeArr[perU1-1]));
			tree1.inOrder(nodeArr[perU1-1]);
			System.out.println("");
			tree1.inOrder(nodeArr[perU2-1]);
			System.out.println("");
			System.out.println("\nPercentage match in two users : "+tree1.permatch(nodeArr[perU1-1], nodeArr[perU2-1], numArr, perU1-1, perU2-1)+"%\n");
			System.out.println("Do you want to check again ? : <Y|N>");
			choiceMatch = sc.next().charAt(0);
		}while(choiceMatch == 'y' || choiceMatch == 'Y');

		char choiceRec;
		do{
			System.out.println("Recommend items between two Users:\nRecommend from First User to Second User\nFirst User :");
			int recU1 = sc.nextInt();
			System.out.println("Second User :");
			int recU2 = sc.nextInt();
			System.out.println(tree1.recommed(nodeArr[recU1-1], nodeArr[recU2-1], numArr, recU1-1, recU2-1));
			System.out.println("Do you want to check again ? : <Y|N>");
		choiceRec = sc.next().charAt(0);
		}while (choiceRec == 'y' || choiceRec == 'Y');

        System.out.println("\nAll trees :");
        for(int i=0;i<nodeArr.length;i++){
            AVL_Tree_3 tree = new AVL_Tree_3();
            System.err.println("\nTree "+(i+1));
            //System.err.println(nodeArr[i].key);
            tree.inOrder(nodeArr[i]);
			System.err.println("");
			tree.preOrder(nodeArr[i]);
        }
        sc.close();
	} 
}

