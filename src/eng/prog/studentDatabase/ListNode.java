package eng.prog.studentDatabase;

public class ListNode {
    // public instance variables as there is nothing to protect
    public Object data; // the contained data object
    public ListNode next; // the self-reference
    // constructors
    public ListNode (Object newData, ListNode newNext) {
        data = newData;
        next = newNext;
    }
    public ListNode () {
        data = null;
        next = null;
    }
} // end class ListNode
