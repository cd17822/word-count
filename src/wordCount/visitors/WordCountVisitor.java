package wordCount.visitors;

import wordCount.visitors.Visitor;
import wordCount.dsForStrings.RBTree;
import wordCount.dsForStrings.RBNode;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class WordCountVisitor implements Visitor {
    private String filename;

    public WordCountVisitor(String filenameIn) {
        filename = filenameIn;
    }

    public void visit(RBTree tree) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            BufferedWriter buffWriter = new BufferedWriter(fileWriter);

            buffWriter.write("Word count: " + getWordCount(tree) + ".\n");
            buffWriter.write("Distinct word count: " + getDistinctWordCount(tree) + ".\n");
            buffWriter.write("Character count: " + getCharacterCount(tree) + ".\n");

            buffWriter.close();
            fileWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private int getCharacterCount(RBTree tree) {
        return characterCountHelper(tree.getRoot(), 0);
    }

    private int getWordCount(RBTree tree) {
        return wordCountHelper(tree.getRoot(), 0);
    }

    private int getDistinctWordCount(RBTree tree) {
        return distinctWordCountHelper(tree.getRoot(), 0);
    }

    private int characterCountHelper(RBNode node, int count) {
        if (node == null) return count;

        count = characterCountHelper(node.getLeft(), count);
        count += (node.getFreq() * node.getWord().length());
        count = characterCountHelper(node.getRight(), count);

        return count;
    }

    private int wordCountHelper(RBNode node, int count) {
        if (node == null) return count;

        count = wordCountHelper(node.getLeft(), count);
        count += node.getFreq();
        count = wordCountHelper(node.getRight(), count);

        return count;
    }

    private int distinctWordCountHelper(RBNode node, int count) {
        if (node == null) return count;

        count = distinctWordCountHelper(node.getLeft(), count);
        count += 1;
        count = distinctWordCountHelper(node.getRight(), count);

        return count;
    }
}
