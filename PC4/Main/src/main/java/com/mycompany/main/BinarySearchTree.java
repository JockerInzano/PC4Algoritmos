
package com.mycompany.main;

public class BinarySearchTree{
    private Node root;

    public BinarySearchTree(){
        root = null;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    public void deleteAsymmetric(int key) {
        root = deleteAsymmetricRec(root, key);
    }

    //Busqueda y eliminacion del Nodo
    private Node deleteAsymmetricRec(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.key) {
            root.left = deleteAsymmetricRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteAsymmetricRec(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            //Se debe buscar al sucessor mas pequeño y no al mas grande
            Node successor = findSuccessor(root.right);
            root.key = successor.key;
            root.right = deleteAsymmetricRec(root.right, successor.key);
            //Node sucessor = findSuccessor(root.left);
            //root.key = sucessor.key;
            //root.left = deleteAsymmetricRec(root.left, sucessor.key);
        }

        return root;
    }

    private Node findSuccessor(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    /*private Node findSuccessor(Node node){
        while(node.right!=null){
            node = node.right;
        }
        
        return node;
    }*/

    public void deleteSymmetric(int key) {
        root = deleteSymmetricRec(root, key);
    }
    
    //Busqueda y elliminacion del Nodo
    private Node deleteSymmetricRec(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.key) {
            root.left = deleteSymmetricRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteSymmetricRec(root.right, key);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            Node predecessor = findPredecessor(root.left);
            root.key = predecessor.key;
            root.left = deleteSymmetricRec(root.left, predecessor.key);
        }

        return root;
    }

    private Node findPredecessor(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public int getHeight() {
        return getHeightRec(root);
    }

    private int getHeightRec(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeightRec(root.left);
        int rightHeight = getHeightRec(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int getInternalPathLength() {
        return getInternalPathLengthRec(root, 0);
    }

    private int getInternalPathLengthRec(Node root, int depth) {
        if (root == null) {
            return 0;
        }

        int leftPathLength = getInternalPathLengthRec(root.left, depth + 1);
        int rightPathLength = getInternalPathLengthRec(root.right, depth + 1);

        return depth + leftPathLength + rightPathLength;
    }

}
