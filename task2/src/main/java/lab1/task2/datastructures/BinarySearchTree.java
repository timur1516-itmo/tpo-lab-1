package lab1.task2.datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация бинарного дерева поиска.
 * Бинарное дерево поиска - это структура данных, в которой каждый узел имеет
 * не более двух потомков, и для каждого узла выполняется свойство:
 * все значения в левом поддереве меньше значения узла,
 * все значения в правом поддереве больше значения узла.
 */
public class BinarySearchTree {

  public static class Node {
    public int data;
    Node left, right;

    Node(int data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }
  }

  private Node root;

  /**
   * Конструктор для создания пустого бинарного дерева поиска.
   */
  public BinarySearchTree() {
    this.root = null;
  }

  /**
   * Возвращает корневой узел
   *
   * @return корневой узел
   */
  public Node getRoot() {
    return root;
  }

  /**
   * Вставка узла с заданным ключом в дерево.
   *
   * @param key Ключ для вставки.
   */
  public void insert(int key) {
    root = insertRec(root, key);
  }

  private Node insertRec(Node root, int key) {
    // Если дерево пустое, создаем новый узел
    if (root == null) {
      root = new Node(key);
      return root;
    }

    // Рекурсивно вставляем в левое или правое поддерево
    if (key < root.data) {
      root.left = insertRec(root.left, key);
    } else if (key > root.data) {
      root.right = insertRec(root.right, key);
    }

    return root;
  }

  /**
   * Поиск узла с заданным ключом в дереве.
   *
   * @param key Ключ для поиска.
   * @return Узел с заданным ключом или null, если узел не найден.
   */
  public Node search(int key) {
    return searchRec(root, key);
  }

  private Node searchRec(Node root, int key) {
    // Базовый случай: корень null или ключ найден
    if (root == null || root.data == key) {
      return root;
    }

    // Ключ меньше корня - ищем в левом поддереве
    if (key < root.data) {
      return searchRec(root.left, key);
    }

    // Ключ больше корня - ищем в правом поддереве
    return searchRec(root.right, key);
  }

  /**
   * Удаление узла с заданным ключом из дерева.
   *
   * @param key Ключ узла для удаления.
   */
  public void delete(int key) {
    root = deleteRec(root, key);
  }

  private Node deleteRec(Node root, int key) {
    // Базовый случай: дерево пустое
    if (root == null) {
      return root;
    }

    // Рекурсивно ищем узел для удаления
    if (key < root.data) {
      root.left = deleteRec(root.left, key);
    } else if (key > root.data) {
      root.right = deleteRec(root.right, key);
    } else {
      // Узел найден - удаляем его

      // Случай 1: узел с одним ребенком или без детей
      if (root.left == null) {
        return root.right;
      } else if (root.right == null) {
        return root.left;
      }

      // Случай 2: узел с двумя детьми
      // Находим минимальный узел в правом поддереве (преемник)
      root.data = minValue(root.right);

      // Удаляем преемника
      root.right = deleteRec(root.right, root.data);
    }

    return root;
  }

  /**
   * Находит минимальное значение в дереве
   */
  private int minValue(Node root) {
    int minValue = root.data;
    while (root.left != null) {
      minValue = root.left.data;
      root = root.left;
    }
    return minValue;
  }

  /**
   * Выполнение обхода дерева в порядке возрастания (in-order).
   *
   * @return список узлов в отсортированном порядке
   */
  public List<Node> inOrder() {
    List<Node> nodes = new ArrayList<>();
    inOrderRec(root, nodes);
    return nodes;
  }

  private void inOrderRec(Node root, List<Node> nodes) {
    if (root != null) {
      inOrderRec(root.left, nodes);
      nodes.add(root);
      inOrderRec(root.right, nodes);
    }
  }

  /**
   * Выполнение обхода дерева в прямом порядке (pre-order).
   *
   * @return список узлов в порядке pre-order
   */
  public List<Node> preOrder() {
    List<Node> nodes = new ArrayList<>();
    preOrderRec(root, nodes);
    return nodes;
  }

  private void preOrderRec(Node root, List<Node> nodes) {
    if (root != null) {
      nodes.add(root);
      preOrderRec(root.left, nodes);
      preOrderRec(root.right, nodes);
    }
  }

  /**
   * Выполнение обхода дерева в обратном порядке (post-order).
   *
   * @return список узлов в порядке post-order
   */
  public List<Node> postOrder() {
    List<Node> nodes = new ArrayList<>();
    postOrderRec(root, nodes);
    return nodes;
  }

  private void postOrderRec(Node root, List<Node> nodes) {
    if (root != null) {
      postOrderRec(root.left, nodes);
      postOrderRec(root.right, nodes);
      nodes.add(root);
    }
  }

  /**
   * Подсчет общего количества узлов в дереве.
   *
   * @return Общее количество узлов.
   */
  public int countNodes() {
    return countNodesRec(root);
  }

  private int countNodesRec(Node root) {
    if (root == null) {
      return 0;
    }
    return 1 + countNodesRec(root.left) + countNodesRec(root.right);
  }

  /**
   * Вычисление высоты дерева.
   *
   * @return Высота дерева.
   */
  public int height() {
    return heightRec(root);
  }

  private int heightRec(Node root) {
    if (root == null) {
      return 0;
    }
    int leftHeight = heightRec(root.left);
    int rightHeight = heightRec(root.right);
    return Math.max(leftHeight, rightHeight) + 1;
  }

  /**
   * Проверка, является ли дерево корректным BST.
   *
   * @return true, если дерево корректно, иначе false.
   */
  public boolean isValidBST() {
    return isValidBSTRec(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private boolean isValidBSTRec(Node node, int min, int max) {
    if (node == null) {
      return true;
    }

    if (node.data <= min || node.data >= max) {
      return false;
    }

    return isValidBSTRec(node.left, min, node.data) &&
           isValidBSTRec(node.right, node.data, max);
  }

  /**
   * Печать дерева в удобочитаемом формате.
   */
  public void printTree() {
    printTreeHelper(root, "", true);
  }

  private void printTreeHelper(Node root, String indent, boolean last) {
    if (root != null) {
      System.out.print(indent);
      if (last) {
        System.out.print("R----");
        indent += "     ";
      } else {
        System.out.print("L----");
        indent += "|    ";
      }
      System.out.println(root.data);
      printTreeHelper(root.left, indent, false);
      printTreeHelper(root.right, indent, true);
    }
  }

  /**
   * Находит максимальное значение в дереве
   */
  public int findMax() {
    if (root == null) {
      throw new IllegalStateException("Tree is empty");
    }
    return findMaxRec(root);
  }

  private int findMaxRec(Node root) {
    if (root.right == null) {
      return root.data;
    }
    return findMaxRec(root.right);
  }

  /**
   * Находит минимальное значение в дереве
   */
  public int findMin() {
    if (root == null) {
      throw new IllegalStateException("Tree is empty");
    }
    return findMinRec(root);
  }

  private int findMinRec(Node root) {
    if (root.left == null) {
      return root.data;
    }
    return findMinRec(root.left);
  }
}