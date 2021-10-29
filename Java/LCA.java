

public class LCA {
    static Node findLCA(Node root, Node a, Node b)
    {
        if (root == null || a == null || b == null) return null;
        if (root.data == a.data || root.data == b.data) return root;

        Node result = null;
        for (Node child : root.children)
        {
            Node current = findLCA(child, a, b);
            if (result == null && current != null) result = current;
            else if (current != null) result = root;
        }
        return result;
        //Node left = findLCA(root.left, a, b);
        //Node right = findLCA(root.right, a, b);

        //if (left != null && right != null) return root;
        //if (left == null) return right;
        //return left;
    }
}

class Node {
    public Node children[];
    public Object data;
    Node (Object data, Node nodes[])
    {
        this.children = nodes;
        this.data = data;
    }
}