package com.swayam.practice.algos.tree.balanced;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * <h1>Right Rotation</h2>
 * 
 * <pre>
 *                      D                                       B
 *                   /     \                                 /     \
 *                  B       E        --->                   A       D
 *               /     \                                         /     \
 *              A       C                                       C       E
 * 
 * </pre>
 * <p>
 * 
 * For data sets, refer to: <a href=
 * "https://www.geeksforgeeks.org/convert-normal-bst-balanced-bst/">https://www.geeksforgeeks.org/convert-normal-bst-balanced-bst/</a>
 * 
 * @author paawak
 *
 */
public class BalancedBinaryTree implements BinaryTree<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalancedBinaryTree.class);

    private Node root;

    @Override
    public void add(Integer element) {

        if (root == null) {
            root = new Node(element);
            return;
        }

        add(root, element);

        balanceTree();
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    @Override
    public int getBreadth() {
        int height = getHeight();

        if (height == 0) {
            return 0;
        }

        int maxBreadth = 0;

        for (int depth = 1; depth <= height; depth++) {
            int siblingCount = getSiblingCount(root, depth, 0);
            maxBreadth = Math.max(maxBreadth, siblingCount);
        }

        return maxBreadth;

    }

    @Override
    public void breadthFirstWalker(BreadthFirstTreeWalker<Integer> breadthFirstTreeWalker) {
        int height = getHeight();

        for (int depth = 1; depth <= height; depth++) {
            breadthFirstTreeWalker.depthChange(depth);
            breadthFirstTreeWalk(root, depth, breadthFirstTreeWalker);
        }

    }

    @Override
    public void preOrderTreeWalker(PreOrderTreeWalker<Integer> preOrderTreeWalker) {
        preOrderTreeWalker(root, NodeType.ROOT, preOrderTreeWalker);
    }

    private void balanceTree() {
        Optional<NodeRotationInfo> nodeRotationInfoOpt = findUnBalancedNode(null, NodeType.ROOT, root);

        if (!nodeRotationInfoOpt.isPresent()) {
            LOGGER.info("This tree is balanced");
            return;
        }

        NodeRotationInfo nodeRotationInfo = nodeRotationInfoOpt.get();

        if (nodeRotationInfo.nodeRotation == NodeRotation.RIGHT) {
            rotateRight(nodeRotationInfo.parent, nodeRotationInfo.nodeType, nodeRotationInfo.node);
        } else if (nodeRotationInfo.nodeRotation == NodeRotation.LEFT) {
            rotateLeft(nodeRotationInfo.parent, nodeRotationInfo.nodeType, nodeRotationInfo.node);
        }

        balanceTree();

    }

    private Optional<NodeRotationInfo> findUnBalancedNode(Node parent, NodeType nodeType, Node node) {
        if (node == null) {
            return Optional.empty();
        }

        int heightDiffInSubTrees = getHeight(node.getLeft()) - getHeight(node.getRight());

        if (Math.abs(heightDiffInSubTrees) > 1) {
            return Optional.of(new NodeRotationInfo(parent, nodeType, node, heightDiffInSubTrees > 0 ? NodeRotation.RIGHT : NodeRotation.LEFT));
        }

        Optional<NodeRotationInfo> leftSubtreeStatus = findUnBalancedNode(node, NodeType.LEFT_CHILD, node.getLeft());

        if (leftSubtreeStatus.isPresent()) {
            return leftSubtreeStatus;
        }

        return findUnBalancedNode(node, NodeType.RIGHT_CHILD, node.getRight());
    }

    private void rotateRight(Node parent, NodeType nodeType, Node node) {

        Node leftChild = node.getLeft();
        Node lowerRight = leftChild.getRight();
        node.setLeft(lowerRight);
        leftChild.setRight(node);

        setParentAfterRotation(parent, nodeType, leftChild);

    }

    private void rotateLeft(Node parent, NodeType nodeType, Node node) {

        Node rightChild = node.getRight();
        Node lowerLeft = rightChild.getLeft();
        node.setRight(lowerLeft);
        rightChild.setLeft(node);

        setParentAfterRotation(parent, nodeType, rightChild);

    }

    private void setParentAfterRotation(Node parent, NodeType nodeType, Node node) {
        if (parent == null) {
            root = node;
        } else {
            if (nodeType == NodeType.LEFT_CHILD) {
                parent.setLeft(node);
            } else if (nodeType == NodeType.RIGHT_CHILD) {
                parent.setRight(node);
            }
        }
    }

    private void preOrderTreeWalker(Node node, NodeType nodeType, PreOrderTreeWalker<Integer> preOrderTreeWalker) {

        if (node == null) {
            return;
        }

        preOrderTreeWalker.treeNode(node.getValue(), nodeType, node.getLeft() != null, node.getRight() != null,
                node.getLeft() == null ? null : node.getLeft().getValue(), node.getRight() == null ? null : node.getRight().getValue());

        preOrderTreeWalker(node.getLeft(), NodeType.LEFT_CHILD, preOrderTreeWalker);

        preOrderTreeWalker(node.getRight(), NodeType.RIGHT_CHILD, preOrderTreeWalker);

    }

    private void breadthFirstTreeWalk(Node node, int depth, BreadthFirstTreeWalker<Integer> breadthFirstTreeWalker) {

        if (node == null) {
            return;
        }

        if (depth == 1) {
            breadthFirstTreeWalker.newElement(node.getValue());
            return;
        }

        if (node.getLeft() != null) {
            breadthFirstTreeWalk(node.getLeft(), depth - 1, breadthFirstTreeWalker);
        }

        if (node.getRight() != null) {
            breadthFirstTreeWalk(node.getRight(), depth - 1, breadthFirstTreeWalker);
        }

    }

    private int getHeight(Node node) {

        if (node == null) {
            return 0;
        }

        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        }

        return Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;

    }

    private int getSiblingCount(Node node, int depth, int siblingCount) {

        if (node == null) {
            return 0;
        }

        if (depth == 1) {
            return siblingCount + 1;
        }

        int leftSiblingCount = 0;
        int rightSiblingCount = 0;

        if (node.getLeft() != null) {
            leftSiblingCount = getSiblingCount(node.getLeft(), depth - 1, siblingCount);
        }

        if (node.getRight() != null) {
            rightSiblingCount = getSiblingCount(node.getRight(), depth - 1, siblingCount);
        }

        return siblingCount + leftSiblingCount + rightSiblingCount;

    }

    private void add(Node node, Integer element) {

        if (element == node.getValue()) {
            LOGGER.warn("the value {} already exists, ignoring!", element);
            return;
        }

        if (element < node.getValue()) {
            if (node.getLeft() == null) {
                node.setLeft(new Node(element));
            } else {
                add(node.getLeft(), element);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new Node(element));
            } else {
                add(node.getRight(), element);
            }
        }

    }

    private static class NodeRotationInfo {
        private final Node parent;
        private final NodeType nodeType;
        private final Node node;
        private final NodeRotation nodeRotation;

        public NodeRotationInfo(Node parent, NodeType nodeType, Node node, NodeRotation nodeRotation) {
            this.parent = parent;
            this.nodeType = nodeType;
            this.node = node;
            this.nodeRotation = nodeRotation;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("NodeRotationInfo [parent=");
            builder.append(parent);
            builder.append(", nodeType=");
            builder.append(nodeType);
            builder.append(", node=");
            builder.append(node);
            builder.append(", nodeRotation=");
            builder.append(nodeRotation);
            builder.append("]");
            return builder.toString();
        }

    }

    private static enum NodeRotation {
        RIGHT, LEFT;
    }

    private static class Node {
        private final int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((left == null) ? 0 : left.hashCode());
            result = prime * result + ((right == null) ? 0 : right.hashCode());
            result = prime * result + value;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (left == null) {
                if (other.left != null)
                    return false;
            } else if (!left.equals(other.left))
                return false;
            if (right == null) {
                if (other.right != null)
                    return false;
            } else if (!right.equals(other.right))
                return false;
            if (value != other.value)
                return false;
            return true;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Node [value=");
            builder.append(value);
            builder.append(", left=");
            builder.append(left);
            builder.append(", right=");
            builder.append(right);
            builder.append("]");
            return builder.toString();
        }
    }

}
