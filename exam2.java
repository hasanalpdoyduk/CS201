//Write a non-recursive method
//int sumOfPath(String path)
//in Tree class, which sums the keys on the path, where the path is
//defined by the parameter path as follows: (i) Path consists of 0’s and
//1’s such as 10011. (ii) A 1 represents to go right, a 0 represents to go
//left. If the path is 1011, you start from root, you go first right, then
//left, then right, then right. If the path is 001, you start from root,
//you go first left, then left, then right. You will use charAt function in
//strings.

}
class TreeNode {
    int key;
    TreeNode left, right;

    public TreeNode(int item) {
        key = item;
        left = right = null;
    }
}

class Tree {
    TreeNode root;

    public Tree() {
        root = null;
    }

    public int sumOfPath(String path) {
        TreeNode current = root;
        int sum = 0;

        for (int i = 0; i < path.length(); i++) {
            char direction = path.charAt(i);
            if (direction == '0' && current.left != null) {
                current = current.left;
            } else if (direction == '1' && current.right != null) {
                current = current.right;
            } else {
                break; // Invalid path, exit loop
            }
            sum += current.key;
        }

        return sum;
    }
}




//Write the recursive method
//int averages()
//in TreeNode class which returns the number of nodes in the tree that
//satisfy the following property: The node’s key is the average of its
//children (left and right children).

class TreeNode {
    int key;
    TreeNode left, right;

    public TreeNode(int item) {
        key = item;
        left = right = null;
    }

    public int averages() {
        if (left == null && right == null) {
            return 1; // Leaf node, satisfies property
        }

        int count = 0;
        if (left != null && right != null && (key == (left.key + right.key) / 2)) {
            count = 1; // Node satisfies property
        }

        // Recursively count nodes in left and right subtrees
        if (left != null) {
            count += left.averages();
        }
        if (right != null) {
            count += right.averages();
        }

        return count;
    }
}
