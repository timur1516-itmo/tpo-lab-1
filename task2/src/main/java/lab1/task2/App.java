package lab1.task2;

import lab1.task2.datastructures.BinarySearchTree;

public class App {
    public static void main(String[] args) {
        System.out.println("Демонстрация работы бинарного дерева поиска:");
        System.out.println("============================================");

        BinarySearchTree tree = new BinarySearchTree();

        System.out.println("\nВставка элементов: 50, 30, 70, 20, 40, 60, 80");
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        System.out.println("\nОбход in-order (возрастание):");
        tree.inOrder().forEach(node -> System.out.print(node.data + " "));

        System.out.println("\n\nПоиск элемента 40: " +
                (tree.search(40) != null ? "найден" : "не найден"));
        System.out.println("Поиск элемента 100: " +
                (tree.search(100) != null ? "найден" : "не найден"));

        System.out.println("\nКорень дерева: " +
                (tree.getRoot() != null ? tree.getRoot().data : "отсутствует"));

        System.out.println("\nУдаление элемента 20 и обход in-order:");
        tree.delete(20);
        tree.inOrder().forEach(node -> System.out.print(node.data + " "));

        System.out.println("\n\nУдаление элемента 30 и обход in-order:");
        tree.delete(30);
        tree.inOrder().forEach(node -> System.out.print(node.data + " "));

        System.out.println("\n\nУдаление элемента 50 (корень) и обход in-order:");
        tree.delete(50);
        tree.inOrder().forEach(node -> System.out.print(node.data + " "));
        System.out.println("\n");
    }
}
