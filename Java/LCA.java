

public class LCA {
    static Node findLCA(Node root, Node a, Node b)
    {
        if (root == null || a == null || b == null) return null;
        if (root.data == a.data || root.data == b.data) return root;

        for (Node child : root.children)
        {
            Node current = findLCA(child, a, b);
            if (current != null) return current;
        }
        return null;
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