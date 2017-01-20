package wordCount.driver;

import wordCount.util.Logger;
import wordCount.dsForStrings.RBTree;
import wordCount.visitors.Visitor;
import wordCount.visitors.PopulateVisitor;
import wordCount.visitors.WordCountVisitor;
import wordCount.visitors.CAOVisitor;
import wordCount.testing.Test;

public class Driver {
    public static void main(String[] args) {
        String input_filename = "input.txt";
        String output_filename = "output.txt";
        int num_iterations = 50;
        int debug_value = 0;

        // parse arguments
        if (args.length < 3) {
            System.err.println("Program must have at least 3 arguments");
            System.exit(1);
        }else{
            input_filename = args[0];
            output_filename = args[1];
            num_iterations = Integer.parseInt(args[2]);

            if (args.length > 3) {
                if (Integer.parseInt(args[3]) < 0 || Integer.parseInt(args[3]) > 2) {
                    System.err.println("Debug value must be 0, 1, or 2\n");
                }else{
                    debug_value = Integer.parseInt(args[3]);
                }
            }
        }
        Logger.setDebugValue(debug_value);

        long start_time = System.currentTimeMillis();
        for (int i = 0; i < num_iterations; ++i) {
            // create tree and get this thing rollin'
            RBTree tree = new RBTree();

            // populate visitor
            Visitor populateVisitor = new PopulateVisitor(input_filename);
            tree.accept(populateVisitor);

            // wordcount visitor
            Visitor wordCountVisitor = new WordCountVisitor(output_filename);
            tree.accept(wordCountVisitor);

            // clone-and-observe visitor
            RBTree backup = new RBTree();
            Visitor caoVisitor = new CAOVisitor(backup);
            tree.accept(caoVisitor);
        }

        // timing
        long finish_time = System.currentTimeMillis();
        double average_time = (finish_time - start_time) / num_iterations;
        Logger.writeMessage("Average Time: " + average_time + "ms.", Logger.DebugLevel.NONE);

        // proving backups work
        Test test = new Test(input_filename);
        test.generateTreeFiles();
    }
}
