import { TreeNode } from './node';

export class Tree {
    
    private root: TreeNode;
    private text: string;

    constructor() {
        this.root = new TreeNode("", null, false);
        this.text = "";
        this.getTree = this.getTree.bind(this);
    }

    private findTreeNode(treeNode: TreeNode, text: string): TreeNode | null {
        let left: TreeNode | null = null;
        let right: TreeNode | null = null;

        if (treeNode.text.toLowerCase() === text.toLowerCase()) {
            return treeNode;
        }

        if (treeNode.treeNodeLeft !== null) {
            left = this.findTreeNode(treeNode.treeNodeLeft, text);
        }

        if (left === null) {
            if (treeNode.treeNodeRight !== null) {
                right = this.findTreeNode(treeNode.treeNodeRight, text);
            }

            return right;
        }

        return left;
    }

    public addText(text: string, textParent: string, isAnalyzable: boolean): void {
        let treeNodeParent: TreeNode | null = this.findTreeNode(this.root, textParent) || this.root;
        let treeNodeChild: TreeNode = new TreeNode(text, treeNodeParent, isAnalyzable);

        if (treeNodeParent.treeNodeLeft === null) {
            treeNodeParent.treeNodeLeft = treeNodeChild;
        } else {
            let treeNode: TreeNode = treeNodeParent.treeNodeLeft;

            while (treeNode.treeNodeRight !== null) {
                treeNode = treeNode.treeNodeRight;
            }
            
            treeNode.treeNodeRight = treeNodeChild;
        }
    }

    public depthLogic(text: string, depth: number): string | null {
        let texts: string[] = [];
        let treeNode: TreeNode | null = this.findTreeNode(this.root, text);

        if (treeNode === null || !treeNode.isAnalyzable) {
            return null;
        }

        while (treeNode.treeNodeParent !== null) {
            texts.unshift(treeNode.text);
            treeNode = treeNode.treeNodeParent;
        }

        return texts[depth - 1] || null;
    }

    private getTreeAux(treeNode: TreeNode, tab: number): void {
        for (let i: number = 0; i < tab; i++) {
            this.text += "\t";
        }

        this.text += treeNode.text + "\n";

        if (treeNode.treeNodeLeft !== null) {
            this.getTreeAux(treeNode.treeNodeLeft, tab + 1);
        }

        if (treeNode.treeNodeRight !== null) {
            this.getTreeAux(treeNode.treeNodeRight, tab);
        }
    }

    public getTree(): string {
        this.text = "";
        this.getTreeAux(this.root, 0);

        return this.text;
    }

}
