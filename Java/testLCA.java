import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class testLCA {
    @Test
    void nullTest()
    {
        Node a = new Node(null, null, null);

        assertEquals(LCA.findLCA(null, null, null), null);
        assertEquals(LCA.findLCA(a, null, null), null);
        assertEquals(LCA.findLCA(null, a, null), null);
        assertEquals(LCA.findLCA(null, null, a), null);
        assertEquals(LCA.findLCA(a, a, a), a);
    }

    @Test
    void regularCase()
    {
        Node a = new Node(1, {null});
        Node b = new Node(2, {a, null});
        Node c = new Node(3, {null});
        Node d = new Node(4, {b, c});
        Node e = new Node(5, {null});
        Node f = new Node(6, {null});
        Node g = new Node(7, {e, f});
        Node h = new Node(8, {null});
        Node i = new Node(9, {g, h});
        Node j = new Node(10, {d, i});
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
        Node a = new Node(1, {null});
        Node b = new Node('b', {a});
        Node c = new Node("three", {null});
        Node d = new Node(4, {b, c});
        Node e = new Node(new Node(5, {null}), {null});
        Node f = new Node(null, {null});
        Node g = new Node(true, {e, f});
        Node h = new Node(false, {null});
        Node i = new Node("nine", {g, h});
        Node j = new Node(10.0, {d, i});
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
        Node e = new Node('e', {null});
        Node d = new Node('d', {e});
        Node b = new Node('b', {d});
        Node c = new Node('c', {d,e});
        Node a = new Node('a', {b, d, c, e});

        Node ans1 = LCA.findLCA(a, e, d);
        Node ans2 = LCA.findLCA(a, b, c);
        Node ans3 = LCA.findLCA(a, e, b);

        assertEquals(ans1.data, 'd');
        assertEquals(ans2.data, 'a');
        assertEquals(ans3.data, 'b');
    }
}
