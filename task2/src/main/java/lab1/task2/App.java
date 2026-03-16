package lab1.task2;

import lab1.task2.datastructures.BinarySearchTree;

public class App {
  public static void main(String[] args) {
    System.out.println("Демонстрация работы бинарного дерева поиска:");
    System.out.println("============================================");
    
    BinarySearchTree tree = new BinarySearchTree();
    
    // Вставка элементов
    System.out.println("\nВставка элементов: 50, 30, 70, 20, 40, 60, 80");
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);
    tree.insert(60);
    tree.insert(80);
    
    System.out.println("\nСтруктура дерева:");
    tree.printTree();
    
    System.out.println("\nОбход in-order (возрастание):");
    tree.inOrder().forEach(node -> System.out.print(node.data + " "));
    
    System.out.println("\n\nОбход pre-order:");
    tree.preOrder().forEach(node -> System.out.print(node.data + " "));
    
    System.out.println("\n\nОбход post-order:");
    tree.postOrder().forEach(node -> System.out.print(node.data + " "));
    
    System.out.println("\n\nПоиск элемента 40: " + 
      (tree.search(40) != null ? "найден" : "не найден"));
    System.out.println("Поиск элемента 100: " + 
      (tree.search(100) != null ? "найден" : "не найден"));
    
    System.out.println("\nМинимальное значение: " + tree.findMin());
    System.out.println("Максимальное значение: " + tree.findMax());
    System.out.println("Высота дерева: " + tree.height());
    System.out.println("Количество узлов: " + tree.countNodes());
    System.out.println("Дерево является корректным BST: " + tree.isValidBST());
    
    System.out.println("\nУдаление элемента 20:");
    tree.delete(20);
    tree.printTree();
    
    System.out.println("\nУдаление элемента 30:");
    tree.delete(30);
    tree.printTree();
    
    System.out.println("\nУдаление элемента 50 (корень):");
    tree.delete(50);
    tree.printTree();
  }
}