import java.io.*;
import java.util.*;

class Solution
{
    public static class Heading {
        protected int weight;
        protected String text;

        public Heading(int weight, String text) {
            this.weight = weight;
            this.text = text;
        }
    }
    public static class Node {
        protected Heading heading;
        protected List<Node> children;

        public Node(Heading heading) {
            this.heading = heading;
            this.children = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Heading> headings = new ArrayList<>();
        for (String line = br.readLine(); !line.isEmpty(); line = br.readLine()) {
            headings.add(parse(line));
        }
        Node outline = toOutline(headings);
        String html = toHtml(outline);
        System.out.println(html);
    }
    /////////// BEGIN EDITABLE //////////////
    private static Node findParent(Node node, Node root) {
        Node tempNode = root;
        if(tempNode.heading.weight == node.heading.weight-1) {
            return tempNode;
        }
        else {
            for(Node t: tempNode.children) {
                tempNode = findParent(node, t);
            }
            return tempNode;
        }
    }

    private static Node findChild(Node node, Node root) {
        Node tempNode = root;
        if(tempNode.children.size() == 0) {
            return tempNode;
        }
        else {
            for(Node t: tempNode.children) {
                tempNode = findChild(node, t);
            }
            return tempNode;
        }
    }

    private static Node findSibling(Node node, Node root) {
        Node tempNode = root;
        for(Node t: tempNode.children) {
            if(node.heading.weight-1 == t.heading.weight) {
                if(t.children.size() > 1) {
                    return t;
                }
            }
        }
        return tempNode;
    }

    private static Node toOutline(List<Heading> headings) {
        // Implement this function. Sample code below builds an
        // outline of only the first heading.

        Node root = new Node(new Heading(0, ""));
        Node curNode = root;
        for(Heading heading: headings) {
            Node tempNode = new Node(heading);
            if(heading.weight <= curNode.heading.weight) {
                curNode = findParent(tempNode, root);
            }
            else {
                curNode = findChild(tempNode, root);
            }
            curNode.children.add(tempNode);
            curNode = tempNode;
        }
        return root;
    }
    /////////// END EDITABLE //////////////


    /** Parses a line of input.
     This implementation is correct for all predefined test cases. */
    private static Heading parse(String record) {
        String[] parts = record.split(" ", 2);
        int weight = Integer.parseInt(parts[0].substring(1));
        Heading heading = new Heading(weight, parts[1].trim());
        return heading;
    }

    /** Converts a node to HTML.
     This implementation is correct for all predefined test cases. */
    private static String toHtml(Node node) {
        StringBuilder buf = new StringBuilder();
        if (!node.heading.text.isEmpty()) {
            buf.append(node.heading.text);
            buf.append("\n");
        }
        Iterator<Node> iter = node.children.iterator();
        if (iter.hasNext()) {
            buf.append("<ul>");

            while (iter.hasNext()) {
                Node child = iter.next();
                buf.append("<li>");
                buf.append(toHtml(child));
                buf.append("</li>");
                if (iter.hasNext()) {
                    buf.append("\n");
                }
            }
            buf.append("</ul>");
        }
        return buf.toString();
    }
}