// By: Raley Wilkin
// Date: 9/25/2025
/*
Problem:  Write a program that keeps and manipulates a linked list of
	    String data. The data will be provided by the user one item at a time.
      The user should be able to do the following operations:
                     -add "String"
                                adds an item to your list (maintaining alphabetical order)
                     -remove "String"
                                if the item exists removes the first instance of it
                     -show
                                should display all items in the linked list
                     -clear
                               should clear the list
	Input:  commands listed above
	Output:  the results to the screen of each menu
	    choice, and error messages where appropriate.
*/

public class LinkedList{

  //instance varialbes go here (think about what you need to keep track of!)
  ListNode head;

  //constructors go here
  public LinkedList(){
    head = null;
  }


  //precondition: the list has been initialized
  //postcondition: the ListNode containing the appropriate value has been added and returned
  public ListNode addAValue(String line)
  {

    // If the list is empty, put new line there
    if (head == null) {
        head = new ListNode(line, null);
        return head;
    }

    ListNode current = head;
    
    // If the new line comes before the head in alphabetical order, put new line at the front
    if (head.getValue().compareTo(line) > 0) {
        ListNode newNode = new ListNode(line, head);
        head = newNode;
        return newNode;
    }

    // Traverse the list to find the correct insertion point by alphabetical order, put new line there
    while (current.getNext() != null && current.getNext().getValue().compareTo(line) < 0) {
        current = current.getNext();
    }

    // Insert new node at end if all previous nodes are alphabetically before it
    ListNode newNode = new ListNode(line, current.getNext());
    current.setNext(newNode);
    return newNode;
  }

  //precondition: the list has been initialized
  //postcondition: the ListNode containing the appropriate value has been deleted and returned.
  //if the value is not in the list returns null
  public ListNode deleteAValue(String line)
  {
    // Special case: head needs to be removed
    if (head == null) return null;
    if (head.getValue().equals(line)) {
        ListNode removed = head;
        head = head.getNext();
        removed.setNext(null); // unlink
        return head;
    }

    ListNode prev = head;
    ListNode current = head.getNext();
    while (current != null) {
        if (current.getValue().equals(line)) {
            prev.setNext(current.getNext());
            current.setNext(null); // unlink
            return prev;
        }
        prev = current;
        current = current.getNext();
    }
    return null;
  }

  //precondition: the list has been initialized
  //postconditions: returns a string containing all values appended together with spaces between.
  public String showValues()
  {
    String placeholdStringer = "";
     ListNode current = head;
     while(current != null){
       placeholdStringer += current.getValue() + " ";
       current = current.getNext();
     }
    return placeholdStringer;
  }

  //precondition: the list has been initialized
  //postconditions: clears the list.
  public void clear()
  {
    head = null;
  }

  // precondition: the list has been initialized
  // postconditions: reverses the list
  public void reverse() {
    // Edge case: if the list is empty or has only one node, no need to reverse
    if (head == null || head.getNext() == null) return; 

    // Placeholders for previous, current, and next nodes
    ListNode prev = null;
    ListNode current = head;
    ListNode next = current.getNext();

    // Traverse the list and reverse the links
    while (next != null) {
        current.setNext(prev);
        prev = current;
        current = next;
        next = next.getNext();
    }

    // Final adjustment for the last node
    current.setNext(prev);
    head = current;

    // Print the reversed list
    showValues();
  }

  // precondition: the list has been initialized
  // postconditions: reverses the list in groups of n
  public void nReverse(int n) {
    // Edge case: if n is less than or equal to 1, no need to reverse
    if (n <= 1 || head == null) return;

    // Place holders for dummy node and previous group end
    ListNode dummy = new ListNode("", head);
    ListNode groupPrev = dummy;

    // Traverse the list in groups of n and reverse each group
    while (true) {
      // Check if there are at least n nodes left to reverse
        ListNode kth = groupPrev;
        for (int i = 0; i < n && kth != null; i++) {
            kth = kth.getNext();
        }
        if (kth == null) break;

        // Reverse the group
        ListNode groupNext = kth.getNext();
        ListNode prev = groupNext;
        ListNode current = groupPrev.getNext();

        // Reverse the nodes in the current group
        while (current != groupNext) {
            ListNode temp = current.getNext();
            current.setNext(prev);
            prev = current;
            current = temp;
        }

        // Connect the previous group to the reversed group
        ListNode temp = groupPrev.getNext();
        groupPrev.setNext(kth);
        groupPrev = temp;
    }

    // Update head to the new front of the list
    head = dummy.getNext();
    
    // Print the n-reversed list
    showValues();
  }
}
