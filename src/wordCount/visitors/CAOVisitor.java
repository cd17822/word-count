package wordCount.visitors;

import wordCount.visitors.Visitor;
import wordCount.dsForStrings.RBNode;
import wordCount.dsForStrings.RBTree;

public class CAOVisitor implements Visitor {
    private RBTree backup;

    public CAOVisitor(RBTree backupIn) {
        backup = backupIn;
    }

    public void visit(RBTree tree) {
        if (tree.getRoot() == null) return;

        RBNode root_copy = (RBNode) tree.getRoot().clone();
        tree.getRoot().addObserver(root_copy);
        backup.setRoot(root_copy);
        visitHelper(root_copy);
    }

    private void visitHelper(RBNode node) {
        RBNode original_left = node.getLeft();
        if (original_left != null) {
            RBNode left_copy = (RBNode) original_left.clone();
            original_left.addObserver(left_copy);
            node.setLeft(left_copy);
            left_copy.setParent(node);

            visitHelper(left_copy);
        }

        RBNode original_right = node.getRight();
        if (original_right != null) {
            RBNode right_copy = (RBNode) original_right.clone();
            original_right.addObserver(right_copy);
            node.setRight(right_copy);
            right_copy.setParent(node);

            visitHelper(right_copy);
        }
    }
}
