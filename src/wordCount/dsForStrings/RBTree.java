package wordCount.dsForStrings;

import wordCount.dsForStrings.RBNode;
import wordCount.util.Logger;
import wordCount.visitors.Visitor;
import java.util.ArrayList;

public class RBTree {
    private RBNode root;

    public RBTree() {
        root = null;
    }

    public RBNode getRoot() {
        return root;
    }

    public void setRoot(RBNode node) {
        root = node;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void insert(String word) {
        RBNode inserted;

        if (root == null) {
            root = new RBNode(word, null, null);
            inserted = root;
        } else {
            inserted = insertHelper(word, root);
        }

        fixViolations(inserted);
    }

    public void traverse() {
        traverseHelper(root);
    }

    private RBNode insertHelper(String word, RBNode node) {
        if (word.compareTo(node.getWord()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new RBNode(word, node, true));
                return node.getLeft();
            } else {
                return insertHelper(word, node.getLeft());
            }
        } else if (word.compareTo(node.getWord()) == 0) { // equal
            node.setFreq(node.getFreq() + 1);
            return null;
        } else {
            if (node.getRight() == null) {
                node.setRight(new RBNode(word, node, false));
                return node.getRight();
            } else {
                return insertHelper(word, node.getRight());
            }
        }
    }

    private void fixViolations(RBNode node) {
        if (node == null) return;

        if (node.getParent() == null) {
            node.setRed(false);
        } else if (node.getParent().getRed()) {
            if (uncleIsRed(node)) {                                    // case 1
                Logger.writeMessage("Case 1", Logger.DebugLevel.TREE_STEPS);
                swapGrandpaParentUncleColors(node);
                fixViolations(node.getParent().getParent()); // if grandpa's the root, it should be black
            } else {
                if (node.getLeftChild() && !node.getParent().getLeftChild()) {        // case 2
                    Logger.writeMessage("Case 2 Right", Logger.DebugLevel.TREE_STEPS);
                    swapRight(node);
                    fixViolations(node.getRight()); // since it leads to case 3 violation
                } else if (!node.getLeftChild() && node.getParent().getLeftChild()) {  // case 2
                    Logger.writeMessage("Case 2 Left", Logger.DebugLevel.TREE_STEPS);
                    swapLeft(node);
                    fixViolations(node.getLeft()); // since it leads to case 3 violation
                } else if (node.getLeftChild() && node.getParent().getLeftChild()) {   // case 3
                    Logger.writeMessage("Case 3 Right", Logger.DebugLevel.TREE_STEPS);
                    rotateRight(node);
                } else {                                                 // case 3
                    Logger.writeMessage("Case 3 Left", Logger.DebugLevel.TREE_STEPS);
                    rotateLeft(node);
                }
            }
        }
    }

    private Boolean uncleIsRed(RBNode node) {
        RBNode g = node.getParent().getParent();
        return g != null &&
               g.getLeft() != null && g.getRight() != null &&
               g.getLeft().getRed() && g.getRight().getRed();
    }

    private void swapGrandpaParentUncleColors(RBNode node) {
        RBNode g = node.getParent().getParent();

        g.getLeft().setRed(false);
        g.getLeft().setRed(false);
        g.setRed(true);
    }

    private void swapRight(RBNode node) {
        node.getParent().getParent().setRight(node);
        node.getParent().setLeft(node.getRight());
        if (node.getRight() != null) {
            node.getRight().setParent(node.getParent());
            node.getRight().setLeftChild(true);
        }
        node.setRight(node.getParent());
        node.setParent(node.getRight().getParent());
        node.getRight().setParent(node);
        node.setLeftChild(false);
        node.getRight().setLeftChild(false);
    }

    private void swapLeft(RBNode node) {
        node.getParent().getParent().setLeft(node);
        node.getParent().setRight(node.getLeft());
        if (node.getLeft() != null) {
            node.getLeft().setParent(node.getParent());
            node.getLeft().setLeftChild(false);
        }
        node.setLeft(node.getParent());
        node.setParent(node.getLeft().getParent());
        node.getLeft().setParent(node);
        node.setLeftChild(true);
        node.getLeft().setLeftChild(true);
    }

    private void rotateRight(RBNode node) {
        RBNode x = node;
        RBNode p = node.getParent();
        RBNode b = node.getParent().getRight();
        RBNode g = node.getParent().getParent();
        RBNode gg = node.getParent().getParent().getParent();

        if (gg != null) {
            if (g.getLeftChild()) {
                gg.setLeft(p);
            } else {
                gg.setRight(p);
            }
        }

        g.setLeft(p.getRight());
        if (b != null) {
            b.setLeftChild(true);
            b.setParent(g);
        }
        p.setRight(g);
        p.setParent(gg);
        g.setParent(p);
        p.setLeftChild(g.getLeftChild());
        if (p.getLeftChild() == null) {
            root = p;
        }
        g.setLeftChild(false);

        p.setRed(false);
        g.setRed(true);
    }

    private void rotateLeft(RBNode node) {
        RBNode x = node;
        RBNode p = node.getParent();
        RBNode b = node.getParent().getLeft();
        RBNode g = node.getParent().getParent();
        RBNode gg = node.getParent().getParent().getParent();

        if (gg != null) {
            if (g.getLeftChild()) {
                gg.setLeft(p);
            } else {
                gg.setRight(p);
            }
        }

        g.setRight(p.getLeft());
        if (b != null) {
            b.setLeftChild(false);
            b.setParent(g);
        }
        p.setLeft(g);
        p.setParent(gg);
        g.setParent(p);
        p.setLeftChild(g.getLeftChild());
        if (p.getLeftChild() == null) {
            root = p;
        }
        g.setLeftChild(true);

        p.setRed(false);
        g.setRed(true);
    }

    private void traverseHelper(RBNode node) {
        if (node == null) return;

        traverseHelper(node.getLeft());
        System.out.println(node.getWord());
        traverseHelper(node.getRight());
    }
}
