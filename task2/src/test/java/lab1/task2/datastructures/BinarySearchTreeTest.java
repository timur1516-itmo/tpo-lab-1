package lab1.task2.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

  private BinarySearchTree tree;

  @BeforeEach
  public void setUp() {
    tree = new BinarySearchTree();
  }

  @Test
  @DisplayName("Проверка вставки и обхода дерева в порядке возрастания")
  public void testInsertAndInOrder() {
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);
    tree.insert(60);
    tree.insert(80);

    List<BinarySearchTree.Node> nodes = tree.inOrder();
    assertEquals(7, nodes.size());
    assertEquals(20, nodes.get(0).data);
    assertEquals(30, nodes.get(1).data);
    assertEquals(40, nodes.get(2).data);
    assertEquals(50, nodes.get(3).data);
    assertEquals(60, nodes.get(4).data);
    assertEquals(70, nodes.get(5).data);
    assertEquals(80, nodes.get(6).data);
  }

  @Test
  @DisplayName("Проверка успешного и неуспешного поиска узлов")
  public void testSearch() {
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);

    BinarySearchTree.Node foundNode = tree.search(30);
    assertNotNull(foundNode);
    assertEquals(30, foundNode.data);

    BinarySearchTree.Node notFoundNode = tree.search(100);
    assertNull(notFoundNode);
  }

  @Test
  @DisplayName("Проверка удаления листового узла")
  public void testDeleteLeafNode() {
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);

    tree.delete(20);
    assertNull(tree.search(20));

    List<BinarySearchTree.Node> nodes = tree.inOrder();
    assertEquals(3, nodes.size());
    assertEquals(30, nodes.get(0).data);
    assertEquals(50, nodes.get(1).data);
    assertEquals(70, nodes.get(2).data);
  }

  @Test
  @DisplayName("Проверка удаления узла с одним ребенком")
  public void testDeleteNodeWithOneChild() {
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);

    tree.delete(30);
    assertNull(tree.search(30));

    List<BinarySearchTree.Node> nodes = tree.inOrder();
    assertEquals(3, nodes.size());
    assertEquals(20, nodes.get(0).data);
    assertEquals(50, nodes.get(1).data);
    assertEquals(70, nodes.get(2).data);
  }

  @Test
  @DisplayName("Проверка удаления узла с двумя детьми")
  public void testDeleteNodeWithTwoChildren() {
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);
    tree.insert(60);
    tree.insert(80);

    tree.delete(50);
    assertNull(tree.search(50));

    List<BinarySearchTree.Node> nodes = tree.inOrder();
    assertEquals(6, nodes.size());
    assertEquals(20, nodes.get(0).data);
    assertEquals(30, nodes.get(1).data);
    assertEquals(40, nodes.get(2).data);
    assertEquals(60, nodes.get(3).data);
    assertEquals(70, nodes.get(4).data);
    assertEquals(80, nodes.get(5).data);
  }

  @Test
  @DisplayName("Проверка удаления корневого узла")
  public void testDeleteRootNode() {
    tree.insert(50);
    tree.delete(50);
    assertNull(tree.search(50));
    assertNull(tree.getRoot());
  }

  @Test
  @DisplayName("Проверка подсчета узлов в дереве")
  public void testCountNodes() {
    assertEquals(0, tree.countNodes());

    tree.insert(50);
    assertEquals(1, tree.countNodes());

    tree.insert(30);
    tree.insert(70);
    assertEquals(3, tree.countNodes());

    tree.insert(20);
    tree.insert(40);
    assertEquals(5, tree.countNodes());
  }

  @Test
  @DisplayName("Проверка высоты дерева")
  public void testHeight() {
    assertEquals(0, tree.height());

    tree.insert(50);
    assertEquals(1, tree.height());

    tree.insert(30);
    tree.insert(70);
    assertEquals(2, tree.height());

    tree.insert(20);
    assertEquals(3, tree.height());
  }

  @Test
  @DisplayName("Проверка корректности BST")
  public void testIsValidBST() {
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);

    assertTrue(tree.isValidBST());
  }

  @Test
  @DisplayName("Проверка обхода pre-order")
  public void testPreOrder() {
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);

    List<BinarySearchTree.Node> nodes = tree.preOrder();
    assertEquals(5, nodes.size());
    assertEquals(50, nodes.get(0).data);
    assertEquals(30, nodes.get(1).data);
    assertEquals(20, nodes.get(2).data);
    assertEquals(40, nodes.get(3).data);
    assertEquals(70, nodes.get(4).data);
  }

  @Test
  @DisplayName("Проверка обхода post-order")
  public void testPostOrder() {
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);

    List<BinarySearchTree.Node> nodes = tree.postOrder();
    assertEquals(5, nodes.size());
    assertEquals(20, nodes.get(0).data);
    assertEquals(40, nodes.get(1).data);
    assertEquals(30, nodes.get(2).data);
    assertEquals(70, nodes.get(3).data);
    assertEquals(50, nodes.get(4).data);
  }

  @Test
  @DisplayName("Проверка поиска минимального значения")
  public void testFindMin() {
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);

    assertEquals(20, tree.findMin());
  }

  @Test
  @DisplayName("Проверка поиска максимального значения")
  public void testFindMax() {
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(80);

    assertEquals(80, tree.findMax());
  }

  @Test
  @DisplayName("Проверка исключения при поиске min в пустом дереве")
  public void testFindMinEmptyTree() {
    assertThrows(IllegalStateException.class, () -> tree.findMin());
  }

  @Test
  @DisplayName("Проверка исключения при поиске max в пустом дереве")
  public void testFindMaxEmptyTree() {
    assertThrows(IllegalStateException.class, () -> tree.findMax());
  }

  @Test
  @DisplayName("Проверка вставки дубликатов")
  public void testInsertDuplicates() {
    tree.insert(50);
    tree.insert(30);
    tree.insert(50); // Дубликат

    assertEquals(2, tree.countNodes()); // Дубликат не должен быть добавлен
  }

  @Test
  @DisplayName("Проверка удаления из пустого дерева")
  public void testDeleteFromEmptyTree() {
    tree.delete(50); // Не должно вызывать ошибку
    assertNull(tree.getRoot());
  }

  @Test
  @DisplayName("Проверка удаления несуществующего узла")
  public void testDeleteNonExistentNode() {
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);

    tree.delete(100); // Узел не существует
    assertEquals(3, tree.countNodes()); // Количество узлов не изменилось
  }

  @Test
  @DisplayName("Проверка последовательности операций вставки и удаления")
  public void testSequentialOperations() {
    // Вставка узлов
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);
    tree.insert(60);
    tree.insert(80);

    assertTrue(tree.isValidBST());
    assertEquals(7, tree.countNodes());

    // Удаление узлов
    tree.delete(20);
    assertTrue(tree.isValidBST());
    assertEquals(6, tree.countNodes());

    tree.delete(30);
    assertTrue(tree.isValidBST());
    assertEquals(5, tree.countNodes());

    tree.delete(50);
    assertTrue(tree.isValidBST());
    assertEquals(4, tree.countNodes());
  }

  @Test
  @DisplayName("Проверка построения вырожденного дерева (список)")
  public void testDegenerateTree() {
    // Вставка в порядке возрастания создает вырожденное дерево
    tree.insert(10);
    tree.insert(20);
    tree.insert(30);
    tree.insert(40);
    tree.insert(50);

    assertEquals(5, tree.countNodes());
    assertEquals(5, tree.height()); // Высота равна количеству узлов
    assertTrue(tree.isValidBST());
  }

  @Test
  @DisplayName("Проверка сбалансированного дерева")
  public void testBalancedTree() {
    tree.insert(50);
    tree.insert(25);
    tree.insert(75);
    tree.insert(12);
    tree.insert(37);
    tree.insert(62);
    tree.insert(87);

    assertEquals(7, tree.countNodes());
    assertEquals(3, tree.height());
    assertTrue(tree.isValidBST());
  }

  @Test
  @DisplayName("Проверка метода printTree")
  public void testPrintTree() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);

    tree.printTree();

    String output = outContent.toString();
    assertTrue(output.contains("50"));
    assertTrue(output.contains("30"));
    assertTrue(output.contains("70"));
    assertTrue(output.contains("20"));

    System.setOut(System.out);
  }

  @Test
  @DisplayName("Проверка удаления всех узлов")
  public void testDeleteAllNodes() {
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);

    tree.delete(50);
    tree.delete(30);
    tree.delete(70);
    tree.delete(20);
    tree.delete(40);

    assertEquals(0, tree.countNodes());
    assertNull(tree.getRoot());
  }

  @Test
  @DisplayName("Тестирование характерных точек алгоритма вставки")
  public void testInsertAlgorithmPath() {
    // Тест 1: Вставка в пустое дерево
    tree.insert(50);
    assertNotNull(tree.getRoot());
    assertEquals(50, tree.getRoot().data);

    // Тест 2: Вставка в левое поддерево
    tree.insert(30);
    assertNotNull(tree.getRoot().left);
    assertEquals(30, tree.getRoot().left.data);

    // Тест 3: Вставка в правое поддерево
    tree.insert(70);
    assertNotNull(tree.getRoot().right);
    assertEquals(70, tree.getRoot().right.data);

    // Тест 4: Вставка в левое поддерево левого узла
    tree.insert(20);
    assertNotNull(tree.getRoot().left.left);
    assertEquals(20, tree.getRoot().left.left.data);

    // Тест 5: Вставка в правое поддерево левого узла
    tree.insert(40);
    assertNotNull(tree.getRoot().left.right);
    assertEquals(40, tree.getRoot().left.right.data);
  }

  @Test
  @DisplayName("Тестирование характерных точек алгоритма поиска")
  public void testSearchAlgorithmPath() {
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);

    // Тест 1: Поиск корня
    BinarySearchTree.Node node = tree.search(50);
    assertNotNull(node);
    assertEquals(50, node.data);

    // Тест 2: Поиск в левом поддереве
    node = tree.search(30);
    assertNotNull(node);
    assertEquals(30, node.data);

    // Тест 3: Поиск в правом поддереве
    node = tree.search(70);
    assertNotNull(node);
    assertEquals(70, node.data);

    // Тест 4: Поиск листового узла
    node = tree.search(20);
    assertNotNull(node);
    assertEquals(20, node.data);

    // Тест 5: Поиск несуществующего узла
    node = tree.search(100);
    assertNull(node);
  }

  @Test
  @DisplayName("Тестирование характерных точек алгоритма удаления")
  public void testDeleteAlgorithmPath() {
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);
    tree.insert(60);
    tree.insert(80);

    // Тест 1: Удаление листового узла
    tree.delete(20);
    assertNull(tree.search(20));
    assertTrue(tree.isValidBST());

    // Тест 2: Удаление узла с одним ребенком (правым)
    tree.insert(25);
    tree.delete(30); // У 30 теперь только правый ребенок (40)
    assertNull(tree.search(30));
    assertTrue(tree.isValidBST());

    // Тест 3: Удаление узла с двумя детьми
    tree.delete(70); // У 70 два ребенка: 60 и 80
    assertNull(tree.search(70));
    assertTrue(tree.isValidBST());

    // Тест 4: Удаление корня
    tree.delete(50);
    assertNull(tree.search(50));
    assertTrue(tree.isValidBST());
  }

  @Test
  @DisplayName("Проверка последовательности попадания в характерные точки при вставке")
  public void testInsertSequence() {
    // Последовательность: [50, 30, 70, 20, 40, 60, 80]
    // Ожидаемая структура:
    //       50
    //      /  \
    //    30    70
    //   / \   / \
    //  20 40 60 80

    tree.insert(50);
    assertEquals(50, tree.getRoot().data);

    tree.insert(30);
    assertEquals(30, tree.getRoot().left.data);

    tree.insert(70);
    assertEquals(70, tree.getRoot().right.data);

    tree.insert(20);
    assertEquals(20, tree.getRoot().left.left.data);

    tree.insert(40);
    assertEquals(40, tree.getRoot().left.right.data);

    tree.insert(60);
    assertEquals(60, tree.getRoot().right.left.data);

    tree.insert(80);
    assertEquals(80, tree.getRoot().right.right.data);

    // Проверка обхода in-order
    List<BinarySearchTree.Node> nodes = tree.inOrder();
    int[] expected = {20, 30, 40, 50, 60, 70, 80};
    for (int i = 0; i < expected.length; i++) {
      assertEquals(expected[i], nodes.get(i).data);
    }
  }
}