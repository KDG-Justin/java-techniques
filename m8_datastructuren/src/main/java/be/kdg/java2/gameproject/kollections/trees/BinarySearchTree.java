package be.kdg.java2.gameproject.kollections.trees;

import java.util.function.Consumer;

public class BinarySearchTree<V extends Comparable<V>> {
    private static class TreeNode<V extends Comparable<V>> {
        private V value;
        private TreeNode<V> left;
        private TreeNode<V> right;

        public TreeNode(V value) {
            this.value = value;
        }
    }

    private TreeNode<V> root;

    public void add(V value) {
        if (this.root == null) { // als het een lege boom is
            this.root = new TreeNode<V>(value);
        } else { // als er al een root is dan doet die recursief roep, bij die add gaat hij zien
            add(root, value);
        }
    }

    private void add(TreeNode<V> parent, V value) {
        if (value.compareTo(parent.value) < 0) { //links
            if (parent.left == null) { // is er plaats links
                parent.left = new TreeNode<>(value); //ja
            } else {
                add(parent.left, value); // recursief naar linkerhelft
            }
        } else { //rehcts
            if (parent.right == null) { //iser plaats rechts
                parent.right = new TreeNode<>(value);//ja
            } else {
                add(parent.right, value); //recursief naar rechterhelft
            }
        }
    }

    public void traverseInOrder(Consumer<V> consumer) {
        traverseInOrder(consumer, root);
    }

    private void traverseInOrder(Consumer<V> consumer, TreeNode<V> node) {
        if (node.left != null) {// zo ver mogelijk links
            traverseInOrder(consumer, node.left);
        }
        consumer.accept(node.value); //daarna pas afdrukken
        if (node.right != null) { // zo ver mogelijk rechts
            traverseInOrder(consumer, node.right); //daarna afdrukken
        }
    }

}
