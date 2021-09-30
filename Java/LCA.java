public class LCA {
    static Node findLCA(Node root, Node a, Node b)
    {
        if (root == null || a == null || b == null) return null;
        if (root.data == a.data || root.data == b.data) return root;

        Node left = findLCA(root.left, a, b);
        Node right = findLCA(root.right, a, b);

        if (left != null && right != null) return root;
        if (left == null) return right;
        return left;
    }
}

class Node {
    public Node left, right;
    public Object data;
    Node (Object data, Node left, Node right)
    {
        if (left != null) this.left = left;
        if (right != null) this.right = right;
        this.data = data;
    }
}
