package wordCount.visitors;

import wordCount.dsForStrings.RBTree;
import wordCount.dsForStrings.RBNode;

public interface Visitor {
    public void visit(RBTree tree);
}