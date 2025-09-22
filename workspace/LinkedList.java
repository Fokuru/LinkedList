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

    // If the list is empty or the new value should be first
    if (head == null) {
        head = new ListNode(line, null);
        return head;
    }

    ListNode current = head;
    // Traverse to the node before where the new node should go

    if (head.getValue().compareTo(line) > 0) {
        ListNode newNode = new ListNode(line, head);
        head = newNode;
        return newNode;
    }

    while (current.getNext() != null && current.getNext().getValue().compareTo(line) < 0) {
        current = current.getNext();
    }
    // Insert new node after current
    ListNode newNode = new ListNode(line, current.getNext());
    current.setNext(newNode);
    return newNode;
  }

  //precondition: the list has been initialized
  //postcondition: the ListNode containing the appropriate value has been deleted and returned.
  //if the value is not in the list returns null
  public ListNode deleteAValue(String line)
  {
    ListNode current = head;
    while(current != null){
      if(current.getValue().equals(line)){
        current.setNext(current.getNext().getNext());
        return current;
      }
      current = current.getNext();
    }

    return current;
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
}
