package com.caronic.jwisdom.core.exercise.algorithm;

import java.util.*;

/**
 * Created by caronic on 2016/10/23.
 */
public class BinarySearchTree {

    private TreeNode rootNode;
    public BinarySearchTree(int value) {
        this.rootNode = new TreeNode(value);
    }

    public void insert(int value) {
        if (this.rootNode == null)
            return;

        TreeNode newNode = new TreeNode(value);
        insertNode(rootNode, newNode);
    }

    private void insertNode(TreeNode parentNode, TreeNode node) {
        // search from root
        // put the smaller node to the left of current node
        if (parentNode.getValue() >= node.getValue()) {
            if (parentNode.getLeft() == null) {
                parentNode.setLeft(node);
                return;
            } else {
                insertNode(parentNode.getLeft(), node);
            }
        } else {
            if (parentNode.getRight() == null) {
                parentNode.setRight(node);
                return;
            } else {
                insertNode(parentNode.getRight(), node);
            }
        }
    }

    public void printTree() {
        if (this.rootNode == null) {
            System.out.println("Empty Tree");
        }
        TreeNode currNode = this.rootNode;
        while (currNode != null) {
            // print root
            // print left
            // print right
            int leftShift = getLeftShift(currNode);
            printNodeValue(currNode.getValue(), leftShift);
            currNode = currNode.getLeft();
        }
    }

    private int getLeftShift(TreeNode node) {
        int count = 0;
        TreeNode currNode = node;
        while (currNode.getLeft() != null) {
            count++;
            currNode = currNode.getLeft();
        }
        return count;
    }

    private void printNode(TreeNode currNode, int offset) {
        if (currNode == null) {
            return;
        }

        if (currNode.getLeft() != null) {
            offset+=1;
            printNode(currNode.getLeft(), offset);
        }

        if (currNode.getRight() != null) {
            printNode(currNode.getRight(), offset + 2);
        }

        StringBuffer sb = new StringBuffer();
        for (int i=0; i<offset; i++) {
            sb.append(" ");
        }
        sb.append(currNode.getValue());
        System.out.println(sb.toString());

    }

    private void preOrder(TreeNode currNode) {
        if (currNode != null) {
            System.out.println(currNode.getValue());
            preOrder(currNode.getLeft());
            preOrder(currNode.getRight());
        }
    }

    private void postOrder(TreeNode currNode) {
        if (currNode != null) {
            preOrder(currNode.getLeft());
            preOrder(currNode.getRight());
            System.out.println(currNode.getValue());
        }
    }

    public int getMaxDepth() {
       TreeNode currNode = this.rootNode;
       return getDepth(currNode);
    }

    private int getDepth(TreeNode currNode) {
        if (currNode == null) return Integer.MIN_VALUE;
        if (currNode.getLeft() == null && currNode.getRight() == null)
            return 1;
        int leftDepth = getDepth(currNode.getLeft());
        int rightDepth = getDepth(currNode.getRight());
        return 1+Math.max(leftDepth, rightDepth);
    }

    public int getMaxNode() {
        TreeNode currNode = rootNode;
        while (currNode.getRight() != null) {
            currNode = currNode.getRight();
        }
        return currNode.getValue();
    }

    public int getMixNode() {
        TreeNode currNode = rootNode;
        while (currNode.getLeft() != null) {
            currNode = currNode.getLeft();
        }
        return currNode.getValue();
    }

    // Breadth First Search
    public List<List<Integer>> getLevelOrder() {
        List<List<Integer>> levels = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this.rootNode);

        while (!queue.isEmpty()) {
            List<Integer> xLevel = new ArrayList<>();
            Queue<TreeNode> nextQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode currNode = queue.poll();
                xLevel.add(currNode.getValue());
                if (currNode.getLeft() != null)
                    nextQueue.add(currNode.getLeft());
                if (currNode.getRight() != null)
                    nextQueue.add(currNode.getRight());
            }
            queue = nextQueue;
            levels.add(xLevel);
        }
        return levels;
    }

    private int getRightShift(TreeNode node) {
        int count = 0;
        TreeNode currNode = node;
        while (currNode.getRight() != null) {
            count++;
            currNode = currNode.getRight();
        }
        return count;
    }

    private void printNodeValue(int value, int leftShift) {
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<leftShift; i++) {
            sb.append(" ");
        }
        sb.append(value);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree(10);
        bst.insert(9);
        bst.insert(11);
        bst.insert(10);
        bst.insert(2);
        bst.insert(23);
        bst.insert(17);
        bst.insert(18);
        bst.insert(45);
        bst.insert(5);
        bst.insert(36);
//        bst.printTree();

//        bst.preOrder(bst.rootNode);
//        bst.printNode(bst.rootNode, 0);
        bst.postOrder(bst.rootNode);
        System.out.println(bst.getMaxDepth());
        System.out.println(bst.getMaxNode());
        System.out.println(bst.getMixNode());
        System.out.println(bst.getLevelOrder());
    }
}
