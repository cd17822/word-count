package wordCount.visitors;

import wordCount.visitors.Visitor;
import wordCount.dsForStrings.RBTree;
import wordCount.dsForStrings.RBNode;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class TreePrinterVisitor implements Visitor {
    private String filename;

    public TreePrinterVisitor(String filenameIn) {
        filename = filenameIn;
    }

    public void visit(RBTree tree) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            BufferedWriter buffWriter = new BufferedWriter(fileWriter);

            visitHelper(tree.getRoot(), buffWriter);

            buffWriter.close();
            fileWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void visitHelper(RBNode node, BufferedWriter b) {
        if (node == null) return;

        visitHelper(node.getLeft(), b);

        try {
            b.write(node.getFreq() + "\n");
        } catch(IOException e) {
            e.printStackTrace();
        } finally {

        }

        visitHelper(node.getRight(), b);
    }
}
