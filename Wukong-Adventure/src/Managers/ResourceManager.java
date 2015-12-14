package Managers;

//This class manages the amount of resources we have
public abstract class ResourceManager {

	protected int count = 1;
	
	//this lets us know how many times we have used the manager
	public void addReference(){
		count++;
	}
	
	//this is to delete managers
	public boolean removeReference(){
		count--;
		return count == 0;
	}
	
}
