package wordCount.visitors;

import wordCount.visitors.Visitor;
import wordCount.dsForStrings.RBTree;
import wordCount.dsForStrings.RBNode;

public class DoubleFreqVisitor implements Visitor {

    public DoubleFreqVisitor() {}

    public void visit(RBTree tree) {
        visitHelper(tree.getRoot());
    }

    private void visitHelper(RBNode node) {
        if (node == null) return;

        visitHelper(node.getLeft());
        node.setFreq(node.getFreq() * 2);
        visitHelper(node.getRight());
    }
}
