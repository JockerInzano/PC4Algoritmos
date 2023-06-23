
package com.mycompany.main;

//import static java.lang.Math.random;
import java.math.BigInteger;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] treeHeights = {500, 1000, 1500, 2000};
        int numIterations = 8;
        
        Random random = new Random();
        //Random random2 = new Random();

        for (int height : treeHeights) {
            System.out.println("Tree Height: " + height);
            System.out.println("----------------------------------------");
            
            BinarySearchTree tree = generateRandomTree(height);
            for (int i = 0; i < numIterations; i++) {
                //Random random1 = new Random(1);
                //Random random2 = new Random(2);

                //BinarySearchTree tree = generateRandomTree(height, random1);
                boolean condicion = random.nextBoolean();

                if (condicion) {
                    System.out.println("Iteration " + (i + 1) + ": Insertion (Asymmetric Deletion)");
                    performAlternatingOperations(tree,true);
                } else {
                    System.out.println("Iteration " + (i + 1) + ": Insertion (Symmetric Deletion)");
                    performAlternatingOperations(tree, false);
                }

                BigInteger expectedIPL = calculateExpectedIPL(height);
                //int exceptedIPL = calculateExpectedIPL(height);
                System.out.println("Expected IPL: " + expectedIPL);
                System.out.println("Final IPL: " + tree.getInternalPathLength());
                System.out.println("----------------------------------------");
            }
        }
    }

    private static BinarySearchTree generateRandomTree(int height) {
        Random random = new Random();
        BinarySearchTree tree = new BinarySearchTree();
        
        // Iteracion a traves de los nodos de un arbol binario completo de altura height
        for (int i = 0; i < (1 << height) - 1; i++) {
            tree.insert(random.nextInt(1000000));//inserta un numero aleatorio en el rango de [0;999.999]
        }

        return tree;
    }
    
    // Calculo del IPL
    /*private static int calculateExpectedIPL(int height) {
        return ((1 << (height + 1)) - 1) * (height + 1) / 2; para height = 500: (2^501-1)*501/2
    }*/
    
    private static BigInteger calculateExpectedIPL(int height){
        BigInteger powerOf2 = BigInteger.valueOf(2).pow(height + 1);
        BigInteger subtracted = powerOf2.subtract(BigInteger.ONE);
        BigInteger multiplied = subtracted.multiply(BigInteger.valueOf(height + 1));
        BigInteger result = multiplied.divide(BigInteger.valueOf(2));

        return result;
    }

    private static void performAlternatingOperations(BinarySearchTree tree,boolean asymmetricDeletion) {
        Random aleatorio = new Random();
        int numOperations = (1 << tree.getHeight()) - 1;

        for (int i = 0; i < numOperations; i++) {
            boolean isInsertion = aleatorio.nextBoolean();
            int key = aleatorio.nextInt(1000000);

            if (isInsertion) {
                tree.insert(key);
            } else {
                if (asymmetricDeletion) {
                    tree.deleteAsymmetric(key);
                } else {
                    tree.deleteSymmetric(key);
                }
            }
        }
    }
}
