/*DATA STRUCT
NAME: RUBI, LARIE JANE      BSIT 2B                    DATE: DECEMBER 15, 2023*/

import java.util.Scanner;

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

public class BinaryTree {
    Node root;

    BinaryTree() {
        root = null;
    }

    Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.key) {
            root.left = insert(root.left, key);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
        }

        return root;
    }

    Node delete(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.key = minValue(root.right);
            root.right = delete(root.right, root.key);
        }

        return root;
    }

    int minValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }
    
        void printPreorder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            printPreorder(node.left);
            printPreorder(node.right);
        }
    }

    void printInorder(Node node) {
        if (node != null) {
            printInorder(node.left);
            System.out.print(node.key + " ");
            printInorder(node.right);
        }
    }

    
      void printPostorder(Node node) {
        if (node != null) {
            printPostorder(node.left);
            printPostorder(node.right);
            System.out.print(node.key + " ");
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);

        char choice;
        do {
            System.out.println("Choose an option:");
            System.out.println("A. Enter the number of Elements");
            System.out.println("B. Enter a number to Insert");
            System.out.println("C. Enter a number to Delete");
            System.out.println("D. Display");
            System.out.println("E. Exit");

            System.out.print("Your choice: ");
            choice = scanner.next().charAt(0);

            switch (Character.toUpperCase(choice)) {
                case 'A':
                    System.out.print("Enter the number of elements: ");
                    int n = scanner.nextInt();

                    System.out.println("Enter the elements:");
                    for (int i = 0; i < n; i++) {
                        int element = scanner.nextInt();
                        tree.root = tree.insert(tree.root, element);
                    }
                    break;

                case 'B':
                    System.out.print("Enter a number to insert: ");
                    int insertNum = scanner.nextInt();
                    tree.root = tree.insert(tree.root, insertNum);
                    break;

                case 'C':
                    System.out.print("Enter a number to delete: ");
                    int deleteNum = scanner.nextInt();
                    tree.root = tree.delete(tree.root, deleteNum);
                    break;

                case 'D':
                    System.out.println("\nPre-order traversal:");
                    tree.printPreorder(tree.root);

                    System.out.println("\nIn-order traversal:");
                    tree.printInorder(tree.root);

                    System.out.println("\nPost-order traversal:");
                    tree.printPostorder(tree.root);
                    break;

                case 'E':
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please choose again.");
            }

        } while (Character.toUpperCase(choice) != 'E');

        scanner.close();
    }
}
