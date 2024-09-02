export class TreeNode {

    public text: string;
    public treeNodeParent: TreeNode | null;
    public treeNodeLeft: TreeNode | null;
    public treeNodeRight: TreeNode | null;
    public isAnalyzable: boolean;

    constructor(text: string, treeNodeParent: TreeNode | null, isAnalyzable: boolean) {
        this.text = text;
        this.treeNodeParent = treeNodeParent;
        this.isAnalyzable = isAnalyzable;
        this.treeNodeLeft = null;
        this.treeNodeRight = null;
    }

}
