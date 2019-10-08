package cs1302.list;

import cs1302.listadt.StringList;

/**
*
*
* The ArrayStringList class uses the StringList interface to create lists based on String arrays.
*/
       
public class ArrayStringList implements StringList{
	
    private String [] boxes; //Boxes array declaration

  /**
   * This is the constructor for an empty list; it starts out 
   * with ten empty spaces in the boxes array yet contains no elements yet. 
   *
   * <p>
   * {@inheritDoc}
   *
   */
    public ArrayStringList(){ //constructor		
	boxes = new String[10];
    }
	
	
   /**
    * This is the constructor for an empty list; it starts out 
    * with ten empty spaces in the boxes array yet contains no elements yet. 
    *
    * <p>
    * {@inheritDoc}
    *
    */	
    public ArrayStringList(StringList other){ 
		
	StringList copy = new ArrayStringList(other);	
	    for (int i = 0; i < other.size();i++ ) { 		  
		copy.add(other.get(i));    
	    }	
    }
       	
        
   /**
    *
    *
    * <p>
    * {@inheritDoc}
    *
    */	
    public boolean add(String l) throws NullPointerException {

       
	boolean isTrue = false;	
	if(l == null) {
		throw new NullPointerException("This value is null");		
	}else{  
	if (isEmpty() == true) {                               //if it is a new list
	    String [] temp = new String[boxes.length+10];   //adds 10 to array length 
	       		   
	    for (int i = 0; i < boxes.length; i++ ) {  
		if (i == 0){ 					                           
		    temp[i] = l;
	    }
		else {
		    temp[i] = boxes[i];         //rest of boxes array that is just empty
		}
	    }
	    for (int i = 0; i < boxes.length;i++ ) {  
		if(temp[i] != boxes[i]) {      //if the arrays are different		  
		    isTrue = true;
		}
	    }
	}
	else{  //if there is at least one element in the list
	    String [] temp = new String[boxes.length+10];	  
	    for (int i = 0; i < boxes.length;i++ ) {  
	      
		temp[i] = boxes[i];
	    }
	   
	    for (int i = 0; i < boxes.length;i++ ) {
	      
		if (temp[i] == null) { //first instance where temp is null, we insert string l
		  
		    temp[i] = l;
		    break;
		}	       
	    }

	    for (int i = 0; i < boxes.length;i++ ) {  
	      
		if(temp[i] != boxes[i]) {      //if the arrays are different
		    isTrue = true;
		}
	    }	  
	}
       	} //end of outer else statement
	return isTrue;
    }	

    /**
	 *
	 *
	 * <p>
	 * {@inheritDoc}
	 *   
     */    
    @Override
    public boolean add(StringList l) throws NullPointerException {	

	boolean isTrue = false;

	if(l == null) {
	    throw new NullPointerException("This value is null");		
	}else{       
	    if (isEmpty() == true) {        //if it is a new list	
		String [] temp = new String[boxes.length+10];    	 
		StringList newCopy = new ArrayStringList(l); 
		for (int i = 0; i < boxes.length; i++ ) {  
		    if (i< newCopy.size())   {  //newCopy.size is size of that list                      
			temp[i] = newCopy.get(i); //fills each array index

		    }else {
			temp[i]=boxes[i];	//fills remainder
		 }
	     }
		for (int i = 0; i < boxes.length;i++ ) {  
		    if(temp[i] != boxes[i]) {      //if the arrays are different
			isTrue = true;
		    }
		}

	    }

	    else{    //if there is at least one element in the list

	    String [] temp = new String[boxes.length+10];
	    StringList newCopy2 = new ArrayStringList(l);  //makes copy of string
	    String [] stringlistArray = new String[newCopy2.size()];	        	
	    for (int i = 0; i < newCopy2.size();i++ ) { 		  
		stringlistArray[i] = newCopy2.get(i);    
	    }	    
	     for (int i = 0; i < boxes.length; i++ ) {  
		 temp[i]=boxes[i];	
	     }	     
	     for (int i = size(), j = 0; j < size() + stringlistArray.length; i++, j++ ) { 	     
		 if (temp[i] == null || temp[i]== " " ) {	        
		     temp[i] = newCopy2.get(j);
		 }
	     }
     	     for (int i = 0; i < boxes.length;i++ ) {  
		 if(temp[i] != boxes[i]) {      //if the arrays are different
		     isTrue = true;
		 }       
	     }
	     boxes = temp;
	 }
	}
	return isTrue;
    }
    

    /**

	 *
	 *
	 * <p>
	 * {@inheritDoc}
	 *
         * 
         */  
	@Override

	public boolean add(int index, String l) throws NullPointerException,
						       IndexOutOfBoundsException { 

	    boolean isTrue = false;

	    if(l == null) {

		throw new NullPointerException("This value is null");		
	    }
	    else if(index < 0 || index > size()) {
		throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
	    }
	    else{
		if (isEmpty() == true && index == 0) {  
		    String [] temp = new String[boxes.length+10];   //adds 10 to array length 

		    for (int i = 0; i < boxes.length; i++ ) {  

			if (i == 0){ 
			    temp[i] = l;
			}
			else {
			    temp[i] = boxes[i];         //rest of boxes array that is just empty
			}
		    }
	      
		    for (int i = 0; i < boxes.length;i++ ) {  
			if(temp[i] != boxes[i]) {      //if the arrays are different
			    isTrue = true;
			}
		    }
		}
		else{
		    String [] temp = new String[boxes.length+10];
		    for (int j = 0; j < boxes.length; j++) {
		    temp[j]=boxes[j];                    //temp array inherits all values from box
		    }

		String [] temp2 = new String [boxes.length+10];      
		for (int i = 0; i < size();i++ ) {
		    if (i == index) { //shifts all value over before setting in new value to index
			int thisLoopIndex = index;        
			for (int j = thisLoopIndex; j < boxes.length-1; j++) { 
			    temp2[j+1]=temp[j];         
			}
			
			temp[i]  = l;                
			temp2[i] = temp[i];
			boxes = temp2;                               
			break; 
						 	}
		    else {

			temp2[i] = temp[i];   //transfers all values from boxes before index 

		    }

		}  	
		for (int i = 0; i < boxes.length;i++ ) {  
		    if(temp[i] != boxes[i]) {      //if the arrays are different
			isTrue = true;
		    }
		}


	    }

	    }
	    return isTrue;
	}

    /**
	 *
	 *
	 * <p>
	 * {@inheritDoc}
	 *
         */ 	

    @Override

    public boolean add(int index, StringList l) throws NullPointerException,
						       IndexOutOfBoundsException{


	boolean isTrue = false;

	if(index < 0 || index > size()) {
		throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
	    }
	else if(l == null) {
		throw new NullPointerException("This value is null");		

	}else{

	    if (isEmpty() == true) {        //if it is a new list

	    String [] temp = new String[boxes.length+10];   //adds 10 to array length 
		StringList newCopy = new ArrayStringList(l);
		for (int i = 0; i < boxes.length; i++ ) {
		    if (i< newCopy.size())   {               						       
			temp[i] = newCopy.get(i);					
		    }else{
			temp[i]=boxes[i];	//fills remainder boxes with blanks
		    }
	       	}
	        
		for (int i = 0; i < boxes.length;i++ ) {  
		    if(temp[i] != boxes[i]) {      //if the arrays are different
			isTrue = true;
		         }			    
		      }
	    }
	else {    //if there is at least one element in the list

		String [] temp = new String[boxes.length+10];

		StringList newCopy2 = new ArrayStringList(l);  //makes copy of string			   
		for (int i = 0; i < boxes.length; i++ ) { 						
		    temp[i]=boxes[i];  				
		}
		
		for (int j = 0; j < boxes.length; j++) {				    
		    if (j == index) {				   
			for (int m = index; m < temp.length-1; m++ ) {				   
			    temp[m+l.size()]= temp[m];			   
			 }
		     
		        for (int i = index, j1 = 0; j1 < newCopy2.size(); i++, j1++ ) {  							   
			    if (i >= index ) {
				temp[i] = newCopy2.get(j1);				 
			    }			    
			 }
			break;
		    }		    
      		}		

		for (int i = 0; i < boxes.length;i++ ) { 		   
	  	    if(temp[i] != boxes[i]) {      //if the arrays are different
			isTrue = true;
 		    }
		}		

	}
	}   
	return isTrue;	
	}
    
    


    /**
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 *
	 */

    @Override
    public void clear() {	
	boxes = new String[0];	     //empties list
    }

   

    /**
	 *
	 *
	 * <p>
	 * {@inheritDoc}
	 */

    @Override

    public String get(int index) throws IndexOutOfBoundsException {        

	if((index < 0) || (index > size())){
	    throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
	}
	else{
	    return boxes[index];           //returns content inside box object

	}

    }
    

    /**
	 * 
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 */
	@Override

	public String set(int index, String s) throws NullPointerException,
	                                              IndexOutOfBoundsException {
	   
	    if(index < 0 || index > size()) {
		throw new IndexOutOfBoundsException("IndexOutOfBoundsException");

	    }
	    else if(s == null) {
		throw new NullPointerException("This value is null");
	    }

		String oldValue = boxes[index];    //retrieves current content in box object
		boxes[index] = (s);           //sets the new elem inside box object at that index
		return oldValue;               //returns the previous element;
	}

    
	/**
	 * This method checks for the number of elements in the array which make up the list.
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 *
	 */

    @Override
    public int size() {

	if (boxes.length >= Integer.MAX_VALUE) {       
	    return Integer.MAX_VALUE;
	}
	else {
	    int counter = 0;
	    for(String withElem : boxes){               
		if(withElem != null){
		    counter++;
		}
	    }

	    return counter;
	}
	
    }	

    

    /**
        * 
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 */
    @Override

    public boolean isEmpty() {          	

	boolean empty = false;
	if (size() == 0){    //checks non-nulls
	    empty = true;

	}
	return empty;
    }

    


    /**
	 *
	 *
	 * <p>
	 * {@inheritDoc}
	 *
         * 
         */
    @Override
    public String makeString(String sep) {

	String newString="";	  
	for(int i = 0; i < size(); i++) {
	    if(i ==size()-1){
		newString = newString +boxes[i];
	    }else{
		newString = newString + boxes[i] + sep;		
	    }
	}
	return newString;		

    }

  
    

    /**
	 *
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 */
    @Override

    public boolean contains(String l) throws NullPointerException {
	if(l == null) {
	    throw new NullPointerException("This value is null");
	}
	else{
	    boolean verdict = false;
	    for(int i = 0; i < size(); i++) {        
		if(l.equals(boxes[i])){  //checks every index to look for string
		    verdict = true;
		    break;
		}
	    }
	    return verdict;
	}	
    }
	

    /**
	 *
	 *
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsIgnoreCase(String l) throws NullPointerException {

	    if(l == null) {
		throw new NullPointerException("This value is null");
		
	    }
	  
	    else{
		boolean verdict = false;	
		for(int i = 0; i < size(); i++) {        
		    if(l.equalsIgnoreCase(boxes[i])){       
			verdict = true;
			break;
		    }

		}
	        	

		return verdict;
		}
	  
	}
    

    

    /**
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 */
	@Override
	public boolean containsSubstring(String l) throws NullPointerException {
	    
	    if(l == null) {
		   
		throw new NullPointerException("This value is null");
		}
		else{
		    boolean verdict = false;
		    for(int i = 0; i < size(); i++) { 
			if(boxes[i].indexOf(l) != -1) {
			    verdict = true;
			    break;
			}
		    }
		    return verdict;
		}
	}	

    

    
	

    /**

	 * This method removes the element from the list and decrements the list.
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 * @param elem element to be removed from the list.
	 */

    @Override

    public String remove(int index) throws NullPointerException {

	String oldString;
	if(boxes[index] == null) {
	    throw new NullPointerException("This value is null");
	}

	else{
	    oldString = boxes[index];
	    System.out.println(boxes.length);
	    String [] temp = new String[boxes.length-1]; //new empty array with decremented length  


	    for (int i = index; i < size(); i++){
		boxes[i] = boxes[i+1];                    //shifts elements to the left	
	    }
	     
	    for (int i = 0; i < size();  i++ ) {
		temp[i] = boxes[i];                            
	    }

	  
	    boxes = temp;
	}

	return oldString;

    }

    

    

    /**
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 */
	@Override
	public int indexOf(String l) throws NullPointerException {
	    if (l == null) {
		throw new NullPointerException("This value is null");
	    }
	    else {
		int index= -1;
		for(int i = 0; i < size(); i++) { //looks through list for index with element
		    if(l.equals(boxes[i])){
			index = i;
			break;
		    }
		}
			return index; //returns -1 if the element does not exist in list
	    }

	}

    



  /**
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 */
    @Override
    public int indexOfIgnoreCase(String l) throws NullPointerException {

	if (l == null) {
	    throw new NullPointerException("This value is null");
	}
	else {
	   
	    int index= -1;
	    for(int i = 0; i < size(); i++) {          //looks through list for index with element
		if(l.equalsIgnoreCase(boxes[i])){
		    index = i;
		    break;
		}

	    }
	    return index;      //returns -1 if the element does not exist in list

	}

    }

    /**
	 *
	 *
	 * <p>
	 * {@inheritDoc}
	 */

    @Override
    public StringList distinct() {

	int count = 0;
	String [] temp = new String[boxes.length]; //creates new temp array with boxes length
	StringList newDistinct = new ArrayStringList(); //new stringlist made of distinct strings

	for (int k = 0; k < boxes.length; k++) {
	    temp[k] = boxes[k];		   //transfers all values to temp from boxes
	}
		
	for (int i = 0; i < size(); i++){
	    for (int j = 0; i < size(); j++) {
		if(temp[i] == temp[j]) {  //counts to see how many matches	       			
		    count++;
		}

		if (count > 1) {    //if matches more than once, go through array& remove one string	       			
		    for (int z = 0; z < size(); z++) {
			if (temp[z] == temp[i]) {
			    remove(z);
			    count--; //count goes down so does not enter loop repetitively 
			    break;         //goes through once and breaks out of for loop		      
			}	       	
		    }				    
		}
	    }		
	}

	for (int k = 0; k < temp.length; k++) { //new values are added to new list order
	    newDistinct.add(temp[k]);
	}

	return newDistinct;

    }


    

    /**
	 *
	 *
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public StringList reverse() {

	    StringList reversed = new ArrayStringList();
	    for (int i = reversed.size()-1; i== 0; i--) {
		reversed.add(get(i));      //starts from back and then adds
	    }
	    return reversed;
	}
    

    

    
     	/**
	 *
	 *
	 * <p>
	 * {@inheritDoc}
	 */
    @Override	

    public String [] toArray(){
	       
	String [] basicList = new String[size()];
	for (int i=0; i < size(); i++) {//limits to list size
	    basicList[i] = boxes[i];
	}

	return basicList;

    }
    

    

    /**
	 *
	 *
	 * <p>
	 * {@inheritDoc}
	 */
    @Override
    public StringList splice(int fromIndex, int toIndex) {

	StringList newSpliced = new ArrayStringList();
	for (int i=0; i < toIndex; i++) {

	    newSpliced.add(get(i)); //grabs string at index and then adds

	}

	return newSpliced;
	

    }

    
}






