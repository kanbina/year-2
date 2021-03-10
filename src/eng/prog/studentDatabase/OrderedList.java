package eng.prog.studentDatabase;

public abstract class OrderedList extends List {

    protected abstract int compare(Object obj1, Object obj2);

    public ListNode getMid(ListNode first, ListNode last) {
        if(first == null || last == null)
            return null;
        ListNode behind = first;
        ListNode ahead = first.next;
        while (ahead != null && compare(ahead.data,last.data) != 0) {
            ahead = ahead.next;
            if (ahead != null && compare(ahead.data,last.data) != 0) {
                behind = behind.next;
                ahead = ahead.next;
            }
        }
        return behind;
    }

    public ListNode binaryFind(Object data) {
        if (firstNode == null)
            return null;
        ListNode first = firstNode;
        ListNode last = lastNode;
        while (last == null || last != first)
        {
            ListNode mid = getMid(first, last);
            if (compare(mid.data,data) == 0)
                return mid;
            else if (compare(mid.data,data) > 0)
                first = mid.next;
            else
                last = mid;
        }
        return null;
    }

    public boolean boolFind(Object data) {
        if (firstNode == null)
            return false;
        ListNode first = firstNode;
        ListNode last = lastNode;
        do
        {
            ListNode mid = getMid(first, last);
            if (compare(mid.data,data) == 0)
                return true;
            else if (compare(mid.data,data) > 0)
                first = mid.next;
            else
                last = mid;
        } while (last == null || last != first);
        return false;
    }

    public boolean find(Object data) {
        if (firstNode == null)
            return false;
        else {
            ListNode current = firstNode;
            while (current != null) {
                if (compare(current.data, data) == 0)
                    return true;
                current = current.next;
            }
        }
        return false;
    }

    public boolean insert(Object newData) {
//        ListNode newNode = new ListNode(newData, null);
        if (firstNode == null) // empty list, insert the new object as both first and last node
        {
            ListNode newNode = new ListNode(newData, null);
            firstNode = lastNode = newNode;
            System.out.println("First input into empty list");
            return true;
        } else if (binaryFind(newData)!=null) // found duplicate, return false and do nothing
        {
            System.out.println("Duplicate exists; no new node inputted.");
            return false;
        } else if (compare(newData, firstNode.data) < 0) // if it's smaller/lesser than first node, insert at front
        {
            ListNode newNode = new ListNode(newData, firstNode);
            firstNode = newNode;
            System.out.println("Inputted as new first node.");
            return true;
        } else {
                ListNode thisNode = firstNode;
                while (thisNode.next != null && compare(thisNode.next.data, newData) < 0)
                    thisNode = thisNode.next;
                if(compare(thisNode.data,lastNode.data) == 0)
                {
                    ListNode newNode = new ListNode(newData, null);
                    thisNode.next = newNode;
                    lastNode = newNode;
                    System.out.println("Inputted as new last node.");
                }
                else {
                    ListNode newNode = new ListNode(newData, thisNode.next);
                    thisNode.next = newNode;
                    System.out.println("Inputted in middle.");
                }
            return true;
        }
    }

    public ListNode remove(Object data) {
        ListNode foundNode = null;
        if (binaryFind(data)==null) // did not find data
        {
            System.out.println(data + ": Object not found.");
        } else if (compare(firstNode.data, data) == 0) {
            firstNode = firstNode.next;
        } else {
            ListNode thisNode = firstNode;
            while (thisNode.next != null && compare(thisNode.next.data, data) < 0)
                thisNode = thisNode.next;
            foundNode = thisNode.next;
            if (foundNode.next == null) {
                lastNode = thisNode;
            }
            thisNode.next = foundNode.next;
            System.out.println(data + ": Object was removed.");
        }
        return foundNode;
    }

    public int getSize() {
        ListNode thisNode = firstNode;
        int count = 0;
        if(thisNode == null)
            return 0;
        while (thisNode != null) {
            count++;
            if(thisNode.data == lastNode.data)
                return count*2-1;
            else if(thisNode.next.data == lastNode.data)
                return count*2;
            else {
                thisNode = thisNode.next.next;
            }
        }
        return 0;
    }


    @Override
    public void insertAtFront(Object newData) {
        System.out.println("This is not a valid function. Try insert().");
    }

    @Override
    public void insertAtBack(Object newData) {
        System.out.println("This is not a valid function. Try insert().");
    }

    @Override
    public Object removeFromFront() { // remove Object from front
        System.out.println("This is not a valid function. Try remove().");
        return null;
    }

    @Override
    public Object removeFromBack() { // remove object from back
        System.out.println("This is not a valid function. Try remove().");
        return null;
    }

    @Override
    public String toString() {
        int count = 1;
        String out = "";
        String label = "";
        ListNode thisNode = firstNode;
        while (thisNode != null) {
            String temp1 = " Node " + count;
            label += temp1;
            String temp2 = " [ " + thisNode.data + " ] ";
            if(thisNode.next != null)
                temp2 += "-->";
            int len = temp2.length()-temp1.length();
            out += temp2;
            label += " ".repeat(len);
            count++;
            thisNode = thisNode.next;
        }
        return label + "\n" + out;
    }
}

class IntegerOrderedList extends OrderedList {
    protected int compare(Object obj1, Object obj2) {
        return (Integer) obj1 - (Integer) obj2;
    }
}

class StringOrderedList extends OrderedList {
    protected int compare(Object obj1, Object obj2) {
        return ((String) obj1).compareTo((String) obj2);
    }
}