/**
 * Lillian Mauger
 * 10/26/2022
 * LinkedList class to implement a linkedlist of generics 
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
public class LinkedList<E>{
    //Data members 
    private Node head, tail;
    int size;
    //inner class node
    /***
	 * Node Class
	 */
    private class Node{
        E value;
        Node next;
        /***
	    * NodeConstructor
	    * @param initialValue E
	    * Initializes value to initialValue
        *               next to null
	    */
        Node(E initialValue){
            value = initialValue;
            next = null;
        }
    }
    /***
	 * LinkedList Constructor
	 * @param none
	 * Initializes size to 0, and head and tail to null
	 */
    public LinkedList(){
        head = tail = null;
        size = 0;
    }

    /***
	 * addFirst method
	 * @param	item E
	 * Method to add to the beginning of linkedlist
	 */
    public boolean addFirst(E item){ //0(1)
        Node newNode = new Node(item);
        if(head == null){
            head = tail = newNode;
        }
        else{
            newNode.next = head;
            head = newNode;
        }
        size++;
        return true;
    }

    /***
	 * addLast method
	 * @param	item E
	 * Method to add to the end of linkedlist
	 */
    public boolean addLast(E item){
        Node newNode = new Node(item);
        if(head == null){
            head = tail = newNode;
        }
        else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    /***
	 * add method
	 * @param	item E
	 * Method to add to linkedlist
	 */
    public int add(E item){
        return add(size, item);
    }

    /***
	 * Getter for the first object 
	 * @param	none
	 * @return	head.value
	 */
    public E getFirst(){
        if(head == null){
            throw new NoSuchElementException();
        }
        return head.value;
    }

    /***
	 * Getter for the last object 
	 * @param	none
	 * @return	tail.value
	 */
    public E getLast(){
        if(head ==null){
            throw new NoSuchElementException();
        }
        return tail.value;
    }

    /***
	 * removeFirst method
	 * @param	none
	 * Method to remove an element from the front of linkedlist
	 */
    public boolean removeFirst(){
        if(head==null){
            throw new NoSuchElementException();
        }
        head = head.next;
        if(head ==null){
            tail =null;
        }
        size--;
        return true;
    }

    /***
	 * removeLast method
	 * @param	none
	 * Method to remove an element from the end of linkedlist
	 */
    public boolean removeLast(){
        if(head ==null){
            throw new NoSuchElementException();
        }
        if(size==1){
            return removeFirst();
        }
        Node current = head;
        Node previous = null;
        while(current.next != null){
            previous = current;
            current = current.next;
        }
        previous.next = null;
        tail = previous;
        size--;
        return true;
    }

     /***
	 * toString override
	 * @param	none
	 * returns arraylist formatted 
	 */
    public String toString(){
        String output = "[";
        Node node = head;
        while(node!=null){
            output+= node.value+ " ";
            node = node.next;
        }
        output += "]";
        return output;
    }

    /***
	 * clear method
	 * @param none
	 * Method to set size to 0, and head and tail to null
	 */
    public void clear(){
        head = tail=null;
        size = 0;
    }

    /***
	 * isEmpty method
	 * @param none
	 * Method to return true if size is 0 and list is empty, false for not that
	 */
    public boolean isEmpty(){
        return(size ==0);
    }

    /***
	 * size method
	 * @param none
	 * Method to get the length of ARRAYlist
	 */
    public int size(){
        return size;
    }

    /***
	 * iterator
	 * @param	none
	 * returns new LinkedListIterator
	 */
    public Iterator<E> iterator(){
        return new LinkedListIterator();
    }
    /***
	 * linkedlistiterator class to implement iterator
	 * contains hasnext and next for iterator
	 */
    private class LinkedListIterator implements Iterator<E>{
        //instance var
        private Node current =head;
        /***
	    * hasNext method
	    * @param	none
	    * returns boolean for if current < size-1 (something else is there to get)
	    */
        public boolean hasNext(){
            return (current != null);
        }

        /***
	    * next method
	    * @param	none
	    * returns object next in linkedlist
	    */
        public E next(){
            if(current ==null){
                throw new NoSuchElementException();
            }
            E value = current.value;
            current = current.next;
            return value;
        }
    }

    /***
	* Contains method
	* @param o Object
	* counts interations for checking if a value exists in an linkedList
	*/
    //O(n)
    public int contains(Object o){
        int iterations=0;
        E value = (E)o;
        Iterator<E> iter = iterator();
        while(iter.hasNext()){
            iterations++;
            if(iter.next().equals(value)){
                return iterations;
            }
        }
        return iterations;
    }

    /***
	* Remove method
	* @param item Object
	* counts interations for finding and removing and shifting the linkedlist for an item in linkedlist
	*/
    // O(n)
    public int remove(Object item){
        E value = (E) item;
        Node node = head;
        Node previous = null;
        int iterations =0;
        while(node!=null){
            iterations++;
            if(node.value.equals(value)){
                break;
            }
            previous = node;
            node = node.next;
        }
        if(node != null){
            if(node == head){
                removeFirst();
            }
            else{
                previous.next = node.next;
            }
        }
        return iterations;
    }

    /***
	* Add method
	* @param item E
    * @param index int
	* counts interations for adding an item to an linkedList
	*/
// O(n)
      public int add(int index, E item){
        int iterations =0;
        if(index>size || index <0)
            throw new ArrayIndexOutOfBoundsException();
        if(index==0){
            addFirst(item);
            return iterations;
        }
        if(index == size-1){
            addLast(item);
            return iterations;
        }
        Node node = head;
        Node previous = null;
        int i=0;
        while(i<index){
            iterations++;
            i++;
            previous = node;
            node = node.next;
            
        }
        Node newNode = new Node(item);
        previous.next = newNode;
        newNode.next = node;
        size++;
        return iterations;
    }

}