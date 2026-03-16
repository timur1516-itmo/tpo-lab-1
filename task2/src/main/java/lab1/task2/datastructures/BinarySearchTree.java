package lab1.task2.datastructures;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.data) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }

        return root;
    }

    public Node search(int key) {
        return search(root, key);
    }

    private Node search(Node root, int key) {
        if (root == null || root.data == key) {
            return root;
        }

        if (key < root.data) {
            return search(root.left, key);
        }

        return search(root.right, key);
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    private Node delete(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.data) {
            root.left = delete(root.left, key);
        } else if (key > root.data) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = minValue(root.right);

            root.right = delete(root.right, root.data);
        }

        return root;
    }

    private int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    public List<Node> inOrder() {
        List<Node> nodes = new ArrayList<>();
        inOrder(root, nodes);
        return nodes;
    }

    private void inOrder(Node root, List<Node> nodes) {
        if (root != null) {
            inOrder(root.left, nodes);
            nodes.add(root);
            inOrder(root.right, nodes);
        }
    }

    public boolean isSatisfiesProperties() {
        return isSatisfiesProperties(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isSatisfiesProperties(Node node, long minInclusive, long maxExclusive) {
        if (node == null) {
            return true;
        }

        if (node.data < minInclusive || node.data >= maxExclusive) {
            return false;
        }

        return isSatisfiesProperties(node.left, minInclusive, node.data)
                && isSatisfiesProperties(node.right, node.data, maxExclusive);
    }

    public static class Node {
        public int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}
