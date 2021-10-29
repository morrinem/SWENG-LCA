import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class testLCA {
    @Test
    void nullTest()
    {
        Node a = new Node(null, new Node[]{null});

        assertEquals(LCA.findLCA(null, null, null), null);
        assertEquals(LCA.findLCA(a, null, null), null);
        assertEquals(LCA.findLCA(null, a, null), null);
        assertEquals(LCA.findLCA(null, null, a), null);
        assertEquals(LCA.findLCA(a, a, a), a);
    }

    @Test
    void regularCase()
    {
        Node a = new Node(1, new Node[]{null});
        Node b = new Node(2, new Node[]{a, null});
        Node c = new Node(3, new Node[]{null});
        Node d = new Node(4, new Node[]{b, c});
        Node e = new Node(5, new Node[]{null});
        Node f = new Node(6, new Node[]{null});
        Node g = new Node(7, new Node[]{e, f});
        Node h = new Node(8, new Node[]{null});
        Node i = new Node(9, new Node[]{g, h});
        Node j = new Node(10, new Node[]{d, i});
        //j is the root node
        /*
        10
        |       |
        4       9
        |   |   |   |
        2   3   7   8
        |       | |
        1       5 6
        */
        Node ans1 = LCA.findLCA(j, a, e);
        Node ans2 = LCA.findLCA(j, a, b);
        Node ans3 = LCA.findLCA(j, e, f);
        Node ans4 = LCA.findLCA(j, f, h);
        assertEquals(ans1.data, 10);
        assertEquals(ans2.data, 2);
        assertEquals(ans3.data, 7);
        assertEquals(ans4.data, 9);
        //answer data = 10, 2, 7, 9 correctly
    }

    @Test
    void mixedTypes()
    {
        Node a = new Node(1, new Node[]{null});
        Node b = new Node('b', new Node[]{a});
        Node c = new Node("three", new Node[]{null});
        Node d = new Node(4, new Node[]{b, c});
        Node e = new Node(new Node(5, new Node[]{null}), new Node[]{null});
        Node f = new Node(null, new Node[]{null});
        Node g = new Node(true, new Node[]{e, f});
        Node h = new Node(false, new Node[]{null});
        Node i = new Node("nine", new Node[]{g, h});
        Node j = new Node(10.0, new Node[]{d, i});
        //same tree as before, with new 'names' or data
        Node ans1 = LCA.findLCA(j, a, e);
        Node ans2 = LCA.findLCA(j, a, b);
        Node ans3 = LCA.findLCA(j, e, f);
        Node ans4 = LCA.findLCA(j, f, h);

        assertEquals(ans1.data, 10.0);
        assertEquals(ans2.data, 'b');
        assertEquals(ans3.data, true);
        assertEquals(ans4.data, "nine");
    }

    @Test
    void dagTests()
    {
        Node e = new Node('e', new Node[]{null});
        Node d = new Node('d', new Node[]{e});
        Node b = new Node('b', new Node[]{d});
        Node c = new Node('c', new Node[]{d,e});
        Node a = new Node('a', new Node[]{b, d, c, e});

        Node ans1 = LCA.findLCA(a, e, d);
        Node ans2 = LCA.findLCA(a, b, c);
        Node ans3 = LCA.findLCA(a, e, b);

        assertEquals(ans1.data, 'd');
        assertEquals(ans2.data, 'a');
        assertEquals(ans3.data, 'b');
    }
}
