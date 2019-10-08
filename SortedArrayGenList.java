package cs1302.lists;

public class SortedArrayGenList<T extends Comparable<T>> implements GenList<T>{
	

	private Box<T> [] boxes; //Boxes array declaration
	
	private int boxIndex=0; //index of prospective new element
	
	/**
	 * This is the constructor for an empty list; it starts out with 
	 * ten empty spaces in the boxes array yet contains no elements yet. 
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 */
	
	public SortedArrayGenList(){ //Box array constructor set to a length of 10	
		boxes = Box.<T>array(10);
	}
	
	/**
	 * The add method increases the length of the box array while adding an element to the end of the list.
	 * The end of the list refers to the next empty space in the box array which will be filled with this
	 * next element.
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 * @param elem 
	 */
	@Override
	public void add(T elem) throws NullPointerException {
		if (elem != null) {
			  if (isEmpty() == true) {                          //if it is a new list
				   Box<T> [] temp = Box.<T>array(boxes.length+10);  //adds 10 to array length 
				   for (int i = 0; i < boxes.length;i++ ) {
					   if (i == boxIndex){ 
							 boxes[i]  = new Box<T>(elem);       //enters element to this index
							 temp[i] = boxes[i];
						}
					   else {
						 temp[i] = boxes[i];
					 }
				   }
				   boxes = temp;                              //sets all contents in temp back to boxes array with increased length
				   boxIndex++;                                  //index for which the next element will live
			  }
			   else{                                           //if there is at least one element in the list
				   Box<T> [] temp = Box.<T>array(boxes.length+10);
				   for (int i = 0; i < boxes.length;i++ ) {
					   if (i == boxIndex) {
						   for (int j = boxIndex; j < size(); j++) {  
					   		     boxes[j+1]=boxes[j];                 //shifts all elements after index to the right to make room for new element
					   		}
							boxes[i]  = new Box<T>(elem);               //enters element in new empty spot
							temp[i] = boxes[i];
							break;
						}
					   else {
						 temp[i] = boxes[i];
					   }
				   }
				   boxes = temp;
				   boxIndex++;    
				   rearrange();                                  //sorts the list after an element is added to the end of the list
			   	}	
		}
		else if (elem == null){                                        //if there is no element 
			throw new NullPointerException();
		}
	}	


	/**
	 * This add method is more specific in that it takes in an index at which to 
	 * place the element and shifts the elements after it over by one to make space for this element.
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 * @param index index where element is wanted to be placed
	 * @param elem  the element to be added to the list
	 */
	@Override
	public void add(int index, T elem) throws NullPointerException, IndexOutOfBoundsException {
		if (elem != null) {
			  if ((isEmpty() == true)) {                                //can add element only if index is 0
				   Box<T> [] temp = Box.<T>array(boxes.length+10);
				   for (int i = 0; i < boxes.length;i++) {
					   if (i == 0){
						   boxes[i]  = new Box<T>(elem);
						   temp[i] = boxes[i];
					   }
					   else{
						 temp[i] = boxes[i];                 
					   }
				   }
				   boxes = temp;                                       //increased length boxes array
			    }
			   else{
				   Box<T> [] temp = Box.<T>array(boxes.length+10);
				   for (int j = 0; j < boxes.length-1; j++) {
			   		     temp[j]=boxes[j];                           //temp array inherits all values from box
			   		}  
				   Box<T> [] temp2 = Box.<T>array(boxes.length+10);  //new temp array made to avoid messing with original boxes array
				   for (int i = 0; i < size();i++ ) {
					   if (i == index) {
						   int thisLoopIndex = index;                 //index at which element will be entered
						   for (int j = thisLoopIndex; j < boxes.length-1; j++) {
							   temp2[j+1]=temp[j];             //new array is filled with values shifted the right from the temp array 
					   		}
						   temp[i]  = new Box<T>(elem);    //element is entered at correct spot and then set to that spot on the 2nd temp array
						   temp2[i] = temp[i];
						   boxes = temp2;                     //boxes inherits all new values from temp2 along with new length
						   break; 
						 	}
					   else {
						 temp2[i] = temp[i];       //will occur before i equals the index; doesnt change the values before the index because it does not need shifting
					 }
				   }    
				   rearrange();                      //sorts out the list after all the shifts are made
			   }
		}
		else if (elem == null) {                      //if element is null
			throw new NullPointerException(); 
		}		
	}	
	
	/**
	 * Sets the array length equal to 0 to empty out the list. Then sets it
	 * back to length 10 like in the constructor.
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 *
	 */
	@Override
	public void clear() {                      
		boxes = Box.<T>array(0);	           //empties list
		boxes = Box.<T>array(10);	           //still empty list but with room in box array for entries
	}
	
	/**
	 * Sorts the list as elements are entered in.
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 *
	 */
	
	public void rearrange() {                     //sorts the array at the instance it is used
		
		   Box<T> [] temp3 = Box.<T>array(boxes.length+10);
		   for (int j = 0; j < boxes.length-1; j++) {
	   		     temp3[j]=boxes[j];                          //temp3 inherits all boxes values
	   		}  
		   Box<T> [] temp4 = Box.<T>array(boxes.length+10); //empty array 	 
		   for (int m=0; m < size()-1; m++) { //outer
			   for(int j = m+1; j < size();j++) { //inner
		    		if (temp3[m].get().compareTo(temp3[j].get()) > 0 ) {
						    temp4[m] = temp3[j];
						    temp4[j] = temp3[m];        //uses temp array to store shifted elements to be copied back
							temp3[m]= temp4[m];
							temp3[j] = temp4[j];  
					   }	  
		    	}
		    }
		    boxes=temp3;               //sets boxes to be our new sorted array
	}
	
	/**
	 *This method compares a list to this list (our list that was made) to test for equality.
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 * @param list the list the user constructed initially
	 */
	@Override
	public boolean equals(Object list) {     //compares new list to this list for equality
		boolean isTrue = false;
		if (list instanceof GenList) {
			GenList l = (GenList) list;
			if (size() == l.size()) {        //must be same size
				for(int i = 0; i < size(); i++) {
					if(get(i).equals(l.get(i))){   //checks every value for equality
						isTrue = true;               //if passes than boolean changes to true
					}
				}
			}
		}
		return isTrue;
	}
		
		
	
	/**
	 * This get method takes in an index to know where in the array it should return 
	 * the element contained.
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 * @param index index at which the element is wished to be retrieved
	 */
	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		
		if((index < 0) || (index > size())){
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
		}
		else{
			return boxes[index].get();      //returns content inside box object
		}
	}

	/**
	 * This set method replaces any content inside the Box object at that index to the element
	 * given in the parameter
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 * @param index index at which the object contents are to be assigned to this element
	 * @param elem  element to set the contents inside Box object
	 */
	@Override
	public T set(int index, T elem) throws NullPointerException, IndexOutOfBoundsException {
		
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
		}
		else if(elem == null) {
			throw new NullPointerException("This value is null");
		}
		T oldValue = boxes[index].get();      //retrieves current content in box object
        boxes[index].set(elem);               //sets the new element inside box object at that index
        return oldValue;                      //returns the previous element;
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
		if (boxes.length >= Integer.MAX_VALUE) {         //checks to see if length exceeded int memory capacity
			return Integer.MAX_VALUE;
		}
		else {
		int counter = 0;
		for(Box<T> withElem : boxes){                     //checks every non-null index to count each element
				if(withElem != null){
					counter++;
				}
		}	
		return counter;
		}
	}	
	
	/**
	 * This method checks to see if the list is empty.
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 */
	@Override
	public boolean isEmpty() {
		boolean empty = false;
		if (size() == 0){
			empty = true;
		}
		return empty;
	}

	
	/**
	 * This method returns true is the element is in the list at least once in the list.
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 * @param elem element to be searched for
	 */
	@Override
	public boolean contains(T elem) throws NullPointerException {
		if(elem == null) {
			throw new NullPointerException("This value is null");
		}
		else{
			boolean verdict = false;
			for(int i = 0; i < size(); i++) {
				if(elem.equals(boxes[i].get())){         //checks every index to see if element looked for is in contents of a Box object
					verdict = true;
					break;
				}
			}
		return verdict;
		}	
	}
	
	/**
	 * This method removes the element from the list and decrements the list and also the array by one.
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 * @param elem element to be removed from the list.
	 */
	@Override
	public boolean remove(T elem) throws NullPointerException {
		if(elem == null) {
			throw new NullPointerException("This value is null");
		}
		else{
			boolean isInList;
			if(contains(elem)){                //performs removal only if element is found in list                   
				isInList = true;
				int indexofT = indexOf(elem);
				for (int i = indexofT; i < size(); i++){      
					boxes[i] = boxes[i+ 1];            //shifts elements to the left
	   		     }
				Box<T> [] temp = Box.<T>array(boxes.length-1);  //new empty array with decremented length
				for (int i = 0; i < size();  i++ ) {
				temp[i] = boxes[i];                       //elements are transferred to temp array in correct index positions
				 }
				boxes = temp;                           //sets instance variable box equal to temp array
			}
			else{
				isInList = false;                       //cannot perform
			}
			return isInList;
		}
	}
	
	
	/**
	 * This method returns the index of the element in the list.
	 *
	 * <p>
	 * {@inheritDoc}
	 *
	 * @param elem element's index to be searched for 
	 */
	@Override
	public int indexOf(T elem) throws NullPointerException {
		if (elem == null) {
			throw new NullPointerException("This value is null");
		}
		else {
			int index= -1;
			for(int i = 0; i < size(); i++) {
					if(elem.equals(boxes[i].get())){     //looks through list for index with element
						index = i;
						break;
					}
			}
			return index;                    //returns -1 if the element does not exist in list
			}
		}
	
}

		
	
	

