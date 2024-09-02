package wordhierarchyanalyzer.collections.tree;

public class Node {

    protected String text;
    protected Node nodeParent;
    protected Node nodeLeft;
    protected Node nodeRight;
    protected boolean isAnalyzable;

    public Node (String text, Node nodeParent, boolean isAnalyzable) {
        this.text = text;
        this.nodeParent = nodeParent;
        this.isAnalyzable = isAnalyzable;
    }

}
