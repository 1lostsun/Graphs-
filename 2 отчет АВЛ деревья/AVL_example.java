import java.util.*;

class User {
    int id;
    String name;

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class AVLNode {
    User user;
    int height;
    AVLNode left, right;

    AVLNode(User user) {
        this.user = user;
        this.height = 1;
    }
}

class AVLTree {
    AVLNode root;

    int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    AVLNode insert(AVLNode node, User user) {
        if (node == null)
            return new AVLNode(user);

        if (user.id < node.user.id)
            node.left = insert(node.left, user);
        else if (user.id > node.user.id)
            node.right = insert(node.right, user);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && user.id < node.left.user.id)
            return rightRotate(node);

        if (balance < -1 && user.id > node.right.user.id)
            return leftRotate(node);

        if (balance > 1 && user.id > node.left.user.id) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && user.id < node.right.user.id) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    AVLNode deleteNode(AVLNode root, int id) {
        if (root == null)
            return root;

        if (id < root.user.id)
            root.left = deleteNode(root.left, id);
        else if (id > root.user.id)
            root.right = deleteNode(root.right, id);
        else {
            if ((root.left == null) || (root.right == null)) {
                AVLNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                AVLNode temp = minValueNode(root.right);
                root.user = temp.user;
                root.right = deleteNode(root.right, temp.user.id);
            }
        }

        if (root == null)
            return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    User search(AVLNode root, int id) {
        if (root == null || root.user.id == id)
            return root == null ? null : root.user;

        if (id < root.user.id)
            return search(root.left, id);

        return search(root.right, id);
    }

    void preOrder(AVLNode node) {
        if (node != null) {
            System.out.println("ID: " + node.user.id + ", Name: " + node.user.name);
            preOrder(node.left);
            preOrder(node.right);
        }
    }
}

public class UserManagementSystem {
    public static void main(String[] args) {
        AVLTree userTree = new AVLTree();

        User user1 = new User(10, "Alice");
        User user2 = new User(20, "Bob");
        User user3 = new User(30, "Charlie");
        User user4 = new User(40, "David");
        User user5 = new User(50, "Eve");
        User user6 = new User(25, "Frank");

        userTree.root = userTree.insert(userTree.root, user1);
        userTree.root = userTree.insert(userTree.root, user2);
        userTree.root = userTree.insert(userTree.root, user3);
        userTree.root = userTree.insert(userTree.root, user4);
        userTree.root = userTree.insert(userTree.root, user5);
        userTree.root = userTree.insert(userTree.root, user6);

        System.out.println("Порядок обхода дерева после вставок:");
        userTree.preOrder(userTree.root);

        userTree.root = userTree.deleteNode(userTree.root, 30);

        System.out.println("\nПорядок обхода дерева после удаления пользователя с ID 30:");
        userTree.preOrder(userTree.root);

        User foundUser = userTree.search(userTree.root, 40);
        if (foundUser != null) {
            System.out.println("\nНайден пользователь: ID: " + foundUser.id + ", Name: " + foundUser.name);
        } else {
            System.out.println("\nПользователь не найден");
        }
    }
}