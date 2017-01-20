package wordCount.dsForStrings;

import wordCount.visitors.Visitor;
import java.util.ArrayList;

public class RBNode implements Cloneable {
    private RBNode parent;
    private RBNode left;
    private RBNode right;
    private String word;
    private int freq;
    private Boolean red;
    private Boolean leftChild;

    private ArrayList<RBNode> observers;

    public RBNode(String wordIn, RBNode parentIn, Boolean leftChildIn) {
        parent = parentIn;
        left = null;
        right = null;
        word = wordIn;
        freq = 1;
        red = true;
        leftChild = leftChildIn;
        observers = new ArrayList<>();
    }

    public Object clone() {
        RBNode clone = null;

        try{
            clone = (RBNode) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } finally {
            return clone;
        }
    }

    public String toString() {
        String l = (left == null) ? null : Integer.toString(left.hashCode());//word;
        String r = (right == null) ? null : Integer.toString(right.hashCode());//word;
        String p = (parent == null) ? null : Integer.toString(parent.hashCode());//word;
        String c = (leftChild == null) ? null : Boolean.toString(leftChild);
        String string = "NODE(" + hashCode() + ")" +
                        "\nword: " + word +
                        "\nfreq: " + freq +
                        "\nparent: " + p +
                        "\nred: " + red +
                        "\nleft: " + l +
                        "\nright: " + r +
                        "\nleftChild: " + c + "\n";
        return string;
    }

    public String getWord() {
        return word;
    }

    public void setRight(RBNode node) {
        right = node;
    }

    public void setLeft(RBNode node) {
        left = node;
    }

    public RBNode getLeft() {
        return left;
    }

    public RBNode getRight() {
        return right;
    }

    public void setFreq(int freqIn) {
        freq = freqIn;

        for (RBNode n : observers) {
            n.freq = freqIn;
        }
    }

    public int getFreq() {
        return freq;
    }

    public void setParent(RBNode node) {
        parent = node;
    }

    public RBNode getParent() {
        return parent;
    }

    public Boolean getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Boolean isLeftChild) {
        leftChild = isLeftChild;
    }

    public Boolean getRed() {
        return red;
    }

    public void setRed(Boolean isRed) {
        red = isRed;
    }

    public void addObserver(RBNode node) {
        observers.add(node);
    }

    public ArrayList<RBNode> getObservers() {
        return observers;
    }
}
