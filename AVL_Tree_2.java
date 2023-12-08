import java.util.*;
public class AVL_Tree_2 {

    private AVLTree root;

    public AVL_Tree_2(int[] array) {
        for (int element : array) {
            insert(element);
        }
    }

    private void insert(int element) {
        root = root == null ? new AVLTree(element) : root.insert(element);
    }

    public List<Integer> suggestItems(int targetItem) {
        List<Integer> suggestions = new ArrayList<>();
        AVLTree currentNode = root;

        while (currentNode != null) {
            if (currentNode.value == targetItem) {
                suggestions.add(currentNode.left.value);
                suggestions.add(currentNode.right.value);
                break;
            } else if (currentNode.value < targetItem) {
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
        }

        return suggestions;
    }

    static Random random = new Random();
    public static void main(String[] args) {
        int[] array = generateRandomArray(100);

        AVL_Tree_2 suggestionSystem = new AVL_Tree_2(array);

        int targetItem = random.nextInt(100);
        List<Integer> suggestions = suggestionSystem.suggestItems(targetItem);

        System.out.println("Suggestions for item " + targetItem + ": " + suggestions);
    }

    private static int[] generateRandomArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(100);
        }

        return array;
    }

    private static class AVLTree {
        private int value;
        private AVLTree left;
        private AVLTree right;
        private int height;

        public AVLTree(int value) {
            this.value = value;
            this.height = 1;
        }

        public AVLTree insert(int element) {
            if (element < this.value) {
                this.left = this.left == null ? new AVLTree(element) : this.left.insert(element);
            } else {
                this.right = this.right == null ? new AVLTree(element) : this.right.insert(element);
            }

            this.updateHeight();

            return this.balance();
        }

        private void updateHeight() {
            this.height = 1 + Math.max(this.left.height, this.right.height);
        }

        private AVLTree balance() {
            int balanceFactor = this.balanceFactor();

            if (balanceFactor > 1) {
                if (this.right.balanceFactor() < 0) {
                    this.rightRotate();
                }

                this.leftRotate();
            } else if (balanceFactor < -1) {
                if (this.left.balanceFactor() > 0) {
                    this.leftRotate();
                }

                this.rightRotate();
            }

            return this;
        }

        private int balanceFactor() {
            return this.right.height - this.left.height;
        }

        private AVLTree leftRotate() {
            AVLTree newRoot = this.right;
            this.right = newRoot.left;
            newRoot.left = this;

            this.updateHeight();
            newRoot.updateHeight();

            return newRoot;
        }

        private AVLTree rightRotate() {
            AVLTree newRoot = this.left;
            this.left = newRoot.right;
            newRoot.right = this;

            this.updateHeight();
            newRoot.updateHeight();

            return newRoot;
        }
    }
}

