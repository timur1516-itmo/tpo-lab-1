package lab1.task2.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

    private BinarySearchTree tree;

    @BeforeEach
    public void setUp() {
        tree = new BinarySearchTree();
    }

    @Test
    @DisplayName("Пустое дерево после создания")
    public void testTreeIsEmptyAfterInit() {
        assertNull(tree.getRoot());
        assertTrue(tree.inOrder().isEmpty());
        assertTrue(tree.isSatisfiesProperties());
    }

    @Test
    @DisplayName("Вставка узлов и in-order обход")
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
        assertTrue(tree.isSatisfiesProperties());
    }

    @Test
    @DisplayName("Дубликаты добавляются")
    public void testInsertDuplicates() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(50);
        tree.insert(30);

        List<BinarySearchTree.Node> nodes = tree.inOrder();
        assertEquals(4, nodes.size());
        assertEquals(30, nodes.get(0).data);
        assertEquals(30, nodes.get(1).data);
        assertEquals(50, nodes.get(2).data);
        assertEquals(50, nodes.get(3).data);
        assertTrue(tree.isSatisfiesProperties());
    }

    @Test
    @DisplayName("Успешный и неуспешный поиск")
    public void testSearch() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);

        BinarySearchTree.Node foundNode = tree.search(30);
        assertNotNull(foundNode);
        assertEquals(30, foundNode.data);

        assertNull(tree.search(100));
        assertTrue(tree.isSatisfiesProperties());
    }

    @Test
    @DisplayName("Поиск в пустом дереве возвращает null")
    public void testSearchInEmptyTree() {
        assertNull(tree.search(1));
        assertTrue(tree.isSatisfiesProperties());
    }

    @Test
    @DisplayName("Удаление листового узла")
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
        assertTrue(tree.isSatisfiesProperties());
    }

    @Test
    @DisplayName("Удаление узла с одним ребенком")
    public void testDeleteNodeWithOneChild() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(70);

        tree.delete(30);
        assertNull(tree.search(30));

        List<BinarySearchTree.Node> nodes = tree.inOrder();
        assertEquals(3, nodes.size());
        assertEquals(20, nodes.get(0).data);
        assertEquals(50, nodes.get(1).data);
        assertEquals(70, nodes.get(2).data);
        assertTrue(tree.isSatisfiesProperties());
    }

    @Test
    @DisplayName("Удаление узла с двумя детьми")
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
        assertTrue(tree.isSatisfiesProperties());
    }

    @Test
    @DisplayName("Удаление корня в дереве из одного узла")
    public void testDeleteRootNodeSingle() {
        tree.insert(50);
        tree.delete(50);

        assertNull(tree.getRoot());
        assertTrue(tree.inOrder().isEmpty());
        assertTrue(tree.isSatisfiesProperties());
    }

    @Test
    @DisplayName("Удаление корня в дереве из нескольких узлов")
    public void testDeleteRootNode() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.delete(10);

        assertEquals(20, tree.getRoot().data);
        List<BinarySearchTree.Node> nodes = tree.inOrder();
        assertEquals(2, nodes.size());
        assertEquals(20, nodes.get(0).data);
        assertEquals(30, nodes.get(1).data);
        assertTrue(tree.isSatisfiesProperties());
    }

    @Test
    @DisplayName("Удаление несуществующего узла не меняет дерево")
    public void testDeleteNonExistentNode() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);

        tree.delete(100);

        List<BinarySearchTree.Node> nodes = tree.inOrder();
        assertEquals(3, nodes.size());
        assertEquals(30, nodes.get(0).data);
        assertEquals(50, nodes.get(1).data);
        assertEquals(70, nodes.get(2).data);
        assertTrue(tree.isSatisfiesProperties());
    }

    @Test
    @DisplayName("Удаление из пустого дерева безопасно")
    public void testDeleteFromEmptyTree() {
        tree.delete(42);
        assertNull(tree.getRoot());
        assertTrue(tree.inOrder().isEmpty());
        assertTrue(tree.isSatisfiesProperties());
    }

    @Test
    @DisplayName("Последовательность вставок и удалений сохраняет порядок in-order")
    public void testSequentialOperations() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        tree.delete(20);
        tree.delete(30);
        tree.delete(70);
        tree.delete(50);

        List<BinarySearchTree.Node> nodes = tree.inOrder();
        assertEquals(3, nodes.size());
        assertEquals(40, nodes.get(0).data);
        assertEquals(60, nodes.get(1).data);
        assertEquals(80, nodes.get(2).data);
        assertTrue(tree.isSatisfiesProperties());
    }
}
