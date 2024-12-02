import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @Test
    void testInsertion() {
        AVLTree tree = new AVLTree();

        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);

        assertEquals(3, tree.height(tree.root));
    }

    @Test
    void testDeletion() {
        AVLTree tree = new AVLTree();

        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);

        tree.root = tree.deleteNode(tree.root, 30);

        assertEquals(3, tree.height(tree.root));
    }

    @Test
    void testSearch() {
        AVLTree tree = new AVLTree();

        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);

        AVLNode result = tree.search(tree.root, 40);
        assertNotNull(result);
        assertEquals(40, result.key);
    }

    private AVLNode search(AVLNode root, int key) {
        if (root == null || root.key == key)
            return root;

        if (key < root.key)
            return search(root.left, key);

        return search(root.right, key);
    }
}