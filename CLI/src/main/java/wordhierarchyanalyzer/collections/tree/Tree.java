package wordhierarchyanalyzer.collections.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tree {

    private final Node root;

    public Tree() {
        this.root = new Node("", null, false);
    }

    private Node findNode(Node node, String text) {
        Node left = null;
        Node right = null;

        if (node.text.equalsIgnoreCase(text)) {
            return node;
        }

        if (node.nodeLeft != null) {
            left = findNode(node.nodeLeft, text);
        }

        if (left == null) {
            if (node.nodeRight != null) {
                right = findNode(node.nodeRight, text);
            }

            return right;
        }

        return left;
    }

    public void addText(String text, String textParent, boolean isAnalyzable) {
        Node nodeParent = null;
        Node nodeChild = null;

        nodeParent = this.findNode(this.root, textParent);

        if (nodeParent == null) {
            nodeParent = this.root;
        }

        nodeChild = new Node(text, nodeParent, isAnalyzable);

        if (nodeParent.nodeLeft == null) {
            nodeParent.nodeLeft = nodeChild;
        } else {
            Node node = nodeParent.nodeLeft;

            while (node.nodeRight != null) {
                node = node.nodeRight;
            }

            node.nodeRight = nodeChild;
        }
    }

    public Optional<String> depthLogic(String text, int depth) {
        List<String> texts = new ArrayList<>();
        Node node = this.findNode(this.root, text);

        if (node == null || !node.isAnalyzable) {
            return Optional.empty();
        }

        while (node.nodeParent != null) {
            texts.addFirst(node.text);

            node = node.nodeParent;
        }

        return Optional.of(texts.get(depth - 1));
    }

}
