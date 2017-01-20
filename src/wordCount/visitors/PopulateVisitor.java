package wordCount.visitors;

import wordCount.visitors.Visitor;
import wordCount.dsForStrings.RBTree;
import wordCount.dsForStrings.RBNode;
import wordCount.util.FileProcessor;

public class PopulateVisitor implements Visitor {
    private String filename;

    public PopulateVisitor(String filenameIn) {
        filename = filenameIn;
    }

    public void visit(RBTree tree) {
        FileProcessor fp = new FileProcessor(filename);

        String line;
        while((line = fp.readLine()) != null) {
            for (String word : line.split("\\s+")) {
                if (word.length() > 0) {
                    tree.insert(word/*.replaceAll("[^a-zA-Z]","")*/);
                }
            }
        }

        fp.finish();
    }
}
