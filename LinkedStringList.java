package cs1302.list;

import cs1302.listadt.StringList;
import cs1302.listadt.StringList.Node;
/**
 * 
 * This class implements the StringList interface to create a list constructed from linked-nodes.
 *
 */
public class LinkedStringList implements StringList{
   
    private  StringList.Node head; 

    public StringList theList; 
    
    /**
     * Our LinkStringList constructor starts the list with a null head.
     * {@inheritDoc}
     *
     */
    
    public LinkedStringList(){ 		
	head = null;
    }
	

    /**
     * This is our copy constructor creates a copy of the StringList inserted to not alter original
     * {@inheritDoc}
     *
     */

    public LinkedStringList(StringList other){
	
	StringList copy = new LinkedStringList(other);
	for (int i = 0; i < other.size();i++ ) {  
	    copy.add(other.get(i));    
	   
	}
    }


   

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean add(String l) throws NullPointerException,IllegalArgumentException {
		
	boolean isTrue = false;
	if(l == null) {
	    throw new NullPointerException("This value is null");
	}
	else if(l == " ") {
	    throw new IllegalArgumentException("This value is empty");
	   
	}
	else { //if no exceptions 

	    StringList.Node node = new StringList.Node(l);
	    if (head == null) { //start state
		head = node;
		isTrue = true;
	    }
	    else {
		StringList.Node someNode = head; //node is pushed back and not front now
		head.setNext(someNode);
		head = node;
		isTrue=true;
	    }	   
	    return isTrue;
	}	
    }

    
   /**
    * 
    * {@inheritDoc}
    *
    */
    @Override
    public boolean add(StringList l) throws NullPointerException{
      
	boolean isChanged = false;
	if(l == null) {
	    throw new NullPointerException("This value is null");
	}
	else {	    
	    StringList copy = new LinkedStringList(l);
	    for (int i = 0; i < copy.size(); i++ ) {  //loop to get all each string into a ndoe
		StringList.Node newNode = new StringList.Node(copy.get(i)); //makes node with string
	       		if (head == null) {
			    head = newNode; //this node is now the start
		}else {
		    StringList.Node someNode = head; //node is pushed back and not front now
     		    head.setNext(someNode);		    
		    head= newNode;
		}	  
	    }    
	    isChanged = true; 
	   }
	return isChanged;
    }
    

   /**
    * 
    * {@inheritDoc}
    *
    */
    @Override
    public boolean add(int index, String l) throws NullPointerException,IllegalArgumentException,
						   IndexOutOfBoundsException {
	boolean isTrue = false;
	if(l == null) {
	    throw new NullPointerException("This value is null");
	}
	else if(l == " ") {
	    throw new IllegalArgumentException("This value is empty");
	}
	else if((index < 0 || index > size())) {
	    throw new IndexOutOfBoundsException("This index is out of range.");
	}
	else {
	    StringList.Node node = new StringList.Node(l);
	    
		int counter = 1;
		StringList.Node old = head;
        
		while (counter < (size()-1)-(index)) {
		    old = old.getNext();
		 
		    counter++; 
		}
		StringList.Node current = old.getNext();
		node.setNext(current);
		old.setNext(node);		
		isTrue=true;

	}
	    return isTrue;	  

    }	



    

        /**
	 * 
	 * {@inheritDoc}
	 *
     */
    @Override
    public boolean add(int index, StringList l) throws NullPointerException,
						       IndexOutOfBoundsException {
	boolean isTrue = false;
	if(l == null) {
	    throw new NullPointerException("This value is null");
	}
	else if((index < 0 || index > size())) {
	    throw new IndexOutOfBoundsException("This index is out of range.");
	}
	else{

	    StringList copy = new LinkedStringList(l);
	    for(int i = 0; i < copy.size(); i++ ) {  //loop to get all each string into a ndoe
		StringList.Node newNode = new StringList.Node(copy.get(i)); //makes node with string
		if (head == null) {
		    head = newNode; //this node is now the start
		}else {
		    StringList.Node someNode = head; //node is pushed back and not front now
		    head.setNext(someNode);
		    head= newNode;
		}	  
		isTrue=true;
	    }	    
	    return isTrue;
	}
    }
	

   /**
    * 
    * {@inheritDoc}	 
    *
    */
    @Override	
    public void clear() {	      
	head = null;     
    }

    

    

    
   /**
    * 
    * {@inheritDoc}
    *
    */
    @Override
    public String get(int index) throws IndexOutOfBoundsException {        

	String theString="";
	if((index < 0) || (index > size())){
	    throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
	}
	else{		       	    
	    int counter = 0;		        	    
	    StringList.Node current;				
	    current = head;
	    while(current !=null){ //checks every non-null index to count each element		       
		counter ++;
		if (counter == (size() - (index+1))) {
		    theString = current.getStr();
		    break;
		}
		current = current.getNext();
	    }		    
	}
	return theString;
    }

    

    

    /**

	 * 

	 * {@inheritDoc}
	 *
	 */
    @Override
    public String set(int index, String s) throws NullPointerException, IllegalArgumentException,
						      IndexOutOfBoundsException {

	String oldString ="";
	if(s == null) {
	    throw new NullPointerException("This value is null");
	}
	else if(s == " ") {
	    throw new IllegalArgumentException("This value is empty");
	}
	else if((index < 0 || index > size())) {
	    throw new IndexOutOfBoundsException("This index is out of range.");
	}
	    else {
		StringList.Node theString;
		StringList.Node newNode = new StringList.Node(s);
	        StringList.Node current = head;

		int reverseIndex= (index+1)-size();   //back to front

		 int counter = 0;
		
		 current = head;
		 while(counter != reverseIndex-1){ 
		     counter++;
		     current = current.getNext();
		 }
		 if (counter == reverseIndex-1){
                     theString = current.getNext();
		     oldString = theString.getStr();
		     current.setNext(newNode);

		 }
	    
	    }		
   
	return oldString;
    }
	

	
	
     /**
	 * 
	 * {@inheritDoc}
	 *
     */
     @Override
     public int size() {

	 int counter = 0;
	 StringList.Node current;		
	 current = head;
	 while(current.getNext() !=null){  //checks every non-null
	     counter++;
	     current = current.getNext();
	    }
	 return counter;
	}
     

 
    
   /**
    * 
    * {@inheritDoc}
    *
    */
    @Override
    public boolean isEmpty() {          
	    
	boolean empty = false;
	if (head == null){             
	    empty = true;
	}
	return empty;
    }

    

	
   /**
    * 
    * {@inheritDoc}
    *
    */
    @Override
    public String makeString(String sep){				
	String newString="";    
	StringList.Node current;
	current = head;
	while(current!=null){          //checks every non-null index to count each element
	    if (current == head) {
		newString = current.getStr();
		current = current.getNext();
	    }
	    else {
		newString = newString + sep + current;
		current = current.getNext();
	    }
	}
	return newString;
    }
	
		
	
	
	
	/**
	 * 
	 * {@inheritDoc}
	 *
	 */
    @Override
    public boolean contains(String l) throws NullPointerException, IllegalArgumentException {
	if(l == null) {
	    throw new NullPointerException("This value is null");
	}
	if (l == " ") {
	    throw new IllegalArgumentException("This value is empty");        	
	}
	else{
	    boolean verdict = false;
	    StringList.Node current;
	    current = head;
	    while(current!=null){                
		if ((current.getStr().equals(l))) {			
		    verdict = true;
		}
		else {
		    current = current.getNext();
		}
	    }			
	    return verdict;
	}
    }	

    
	
	
   /**
    * 
    * {@inheritDoc}
    *
    */
    @Override
    public boolean containsIgnoreCase(String l) throws NullPointerException,
							   IllegalArgumentException {
	boolean verdict = false;
	if(l == null) {
	    throw new NullPointerException("This value is null");
	}
	if (l == " ") {
	    throw new IllegalArgumentException("This value is empty");
	}

	else{		
	    StringList.Node current;
	    current = head;
	    while(current!= null){                
		if ((current.getStr()).equalsIgnoreCase(l)) {
		    verdict = true;
		}
		else {
		    current = current.getNext();
		}
	    }                			                                			
	}
	return verdict;            	  
       }
    
    

    
	
	
   /**
    * 
    * {@inheritDoc}
    *
    */
    @Override
    public boolean containsSubstring(String l) throws NullPointerException,
							  IllegalArgumentException {

	boolean verdict = false;
	if(l == null) {
	    throw new NullPointerException("This value is null");
	}
	if (l == " ") {
	    throw new IllegalArgumentException("This value is empty");
	}
	else{
	    StringList.Node current= new StringList.Node();
	    current = head;
	    while(current!= null){
		String currentString = current.getStr();
		if (currentString.indexOf(l) != -1) {	    		
		    verdict = true;
		}
		else {
		    current = current.getNext();
		}
	    }
	}
	return verdict;
    }
	

	
		
			
	
	
	
   /**
    * 
    * {@inheritDoc}
    *
    */
    @Override
    public String remove(int index) throws IndexOutOfBoundsException {

	String OldString="";
	if((index < 0 || index > size())) {
	    throw new IndexOutOfBoundsException("This index is out of range.");
	}else{
	      
	    StringList.Node current = head;
	    StringList.Node someNode;
	    int reverseIndex= (index+1)-size();
	    int counter = 0;
	    current = head;
	    while(counter != reverseIndex-1){ 
		counter++;
		current = current.getNext();
	    }
   
	    if (counter == reverseIndex-1){	  
		someNode = current.getNext();
		OldString = someNode.getStr();
		while(current.getNext() != null){

		      current.setNext(current.getNext().getNext());
		      current = current.getNext();
		}
	    }
	    
	}		 

	return OldString;    
    }

    
	
	/**
	 * 
	 * {@inheritDoc}
	 *
	 */
	@Override
	public int indexOf(String l) throws NullPointerException, IllegalArgumentException {
		
	    int index = -1;
	    if(l == null) {
		throw new NullPointerException("This value is null");
	    }
	    else if(l == " ") {
		throw new IllegalArgumentException("This value is empty");	
	    }            
	    else {
	        StringList.Node current = head;		
		String [] basicList = new String[size()];		
		for (int i=size(); i == 0; i++) {        		
		    basicList[i] = current.getStr(); //retrieves string from node
		    current=current.getNext();
		}
		for (int i=0; i <  basicList.length; i++) {        		
		    if (basicList[i].equals(l)){
			index = i;
			break;
		    }	
		}
	    }
	    return index;
	}

    

    
	/**
	 * 
	 * {@inheritDoc}
	 *
	 */
	@Override

	public int indexOfIgnoreCase(String l) throws NullPointerException,
						      IllegalArgumentException {
	    int index = -1;
	    if(l == null) {
		throw new NullPointerException("This value is null");
	    }

	    else if(l == " ") {
		throw new IllegalArgumentException("This value is empty");
	    }
	    else {
	        StringList.Node current = head;		
		String [] basicList = new String[size()];  //copies linkedlist into array	
		for (int i=size(); i == 0; i++) {        		
		    basicList[i] = current.getStr();
		    current=current.getNext();   //to retrieve values
		}
		for (int i=0; i <  basicList.length; i++) { //uses this array to compare       		
		    if (basicList[i].equalsIgnoreCase(l)){
			index = i;
			break;
		    }	
		}	       
	    }
	    return index;                       
	}
    

    
	
	/**
	 * 
	 * {@inheritDoc}
	 *
	 */
    @Override
    public StringList distinct() {
		
	StringList.Node current = head;
	String [] basicList = new String[size()];
	for (int i=size(); i == 0; i++) {
	    basicList[i] = current.getStr();
	}
	int count = 0;
	StringList newDistinct = new LinkedStringList(); //new stringlist made of distinct strings
	String [] temp = new String[basicList.length];
	for (int k = 0; k < basicList.length; k++) {
	    temp[k] = basicList[k];    //transfers all values
	}
	for (int i = 0; i < size(); i++){        	
	    for (int j = 0; i < size(); j++) {
		if(temp[i] == temp[j]) {  //count as most should be 1
		    count++;
		}
		if (count > 1) {                     
		    for (int z = 0; z < size(); z++) {					   
			if (temp[z] == temp[i]) {
			    remove(z);
			    count--;                
			    break;         //goes through once and breaks out of for loop
			}
		    }					
		}						
	    }
	}			      
	for (int k = 0; k < temp.length; k++) {   //new values are added to new list order
	    newDistinct.add(temp[k]);	            
	}
	return newDistinct;        	                		
    }
    
	

    

    /**
     * 
     * {@inheritDoc}
     *
     */
    public StringList reverse() {
	       
	StringList.Node current = head;
	StringList reversed = new LinkedStringList();		
	for (int i=0; i < size(); i++) {			
	    reversed.add(current.getStr());
	    current = current.getNext();
	}	
	return reversed;
    }

    

    
	/**
	 * 
	 * {@inheritDoc}
	 *
	 */
    @Override
    public String [] toArray(){
	StringList.Node current = head;		
	String [] basicList = new String[size()]; //creates array size of list		
	for (int i=size(); i == 0; i++) {        		
	    basicList[i] = current.getStr();
	    current=current.getNext();  
	}		
	return basicList;				               
    }

    

       /**
	 * 
	 * {@inheritDoc}
	 *
	 */
    @Override
    public StringList splice(int fromIndex, int toIndex) throws IndexOutOfBoundsException {

	StringList newSpliced = new LinkedStringList();//new stringlist to be filled
	if((fromIndex < 0 || toIndex > size() || fromIndex > toIndex)) {			
	    throw new IndexOutOfBoundsException("This is an illegal endpoint value.");
	}
	else{
	    StringList.Node current = head;		
	    String [] basicList = new String[size()];		
	    for (int i=size(); i == 0; i++) {        		
		basicList[i] = current.getStr();
		current=current.getNext();
	    }
	   
	    for (int i=0 ; i == basicList.length; i++) {        		
	    newSpliced.add(basicList[i]);
	}

    }
           

    return newSpliced;

    
    }




}
