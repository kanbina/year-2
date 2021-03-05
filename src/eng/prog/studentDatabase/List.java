package eng.prog.studentDatabase;

public class List {
    protected ListNode firstNode; // the first node
    protected ListNode lastNode; // the last node
    protected String name; // a string name

    // constructors
    public List(String listName) {
        firstNode = lastNode = null;
        name = listName;
    }

    public List() {
        this(new String("List"));
    }

    public void insertAtFront(Object newData) { // insert Object at front
        if (firstNode == null) // empty list
            firstNode = lastNode = new ListNode(newData, null);
        else {
            ListNode newFirstNode = new ListNode(newData, firstNode);
            firstNode = newFirstNode;
        }
    }

    public void insertAtBack(Object newData) { // insert Object at back
        if (firstNode == null) // empty list
            firstNode = lastNode = new ListNode(newData, null);
        else {
            ListNode newLastNode = new ListNode(newData, null);
            lastNode.next = newLastNode;
            lastNode = newLastNode;
        }
    }

    public Object removeFromFront() { // remove Object from front
        if (firstNode == null) // empty list
            return null;
        Object removedData = firstNode.data;
        if (firstNode == lastNode) // only one list node
            firstNode = lastNode = null;
        else
            firstNode = firstNode.next;
        return removedData;
    }

    public Object removeFromBack() { // remove object from back
        if (firstNode == null) // empty list
            return null;
        Object removedData = lastNode.data;
        if (firstNode == lastNode) // only one node in the list
            firstNode = lastNode = null;
        else {
            ListNode current = firstNode;
            while (current.next != lastNode)
                current = current.next;
            lastNode = current;
            current.next = null;
        }
        return removedData;
    }

    public ListNode getFirst() {
        return firstNode;
    }

    @Override
    public String toString() { // print list content to string
        String output = new String();
        ListNode current = firstNode;
        output = name + ":";
        while (current != null) {
// we are implicitly calling the data object toString method
            output += " " + current.data;
            current = current.next;
        }
        return output;
    }
} // end class List