package wordCount.testing;

import wordCount.dsForStrings.RBTree;
import wordCount.visitors.Visitor;
import wordCount.visitors.DoubleFreqVisitor;
import wordCount.visitors.TreePrinterVisitor;
import wordCount.visitors.PopulateVisitor;
import wordCount.visitors.CAOVisitor;

public class Test {
    private String filename;

    public Test(String filenameIn) {
        filename = filenameIn;
    }

    public void generateTreeFiles() {
        RBTree tree = new RBTree();

        Visitor populater = new PopulateVisitor(filename);
        tree.accept(populater);

        RBTree backup = new RBTree();
        Visitor copier = new CAOVisitor(backup);
        tree.accept(copier);

        Visitor modifier = new DoubleFreqVisitor();
        tree.accept(modifier);

        Visitor original_printer = new TreePrinterVisitor("original_tree.txt");
        tree.accept(original_printer);

        Visitor backup_printer   = new TreePrinterVisitor("backup_tree.txt"  );
        backup.accept(backup_printer);

        // calling a diff on these^^ files should result in no output ( => same file )
    }
}