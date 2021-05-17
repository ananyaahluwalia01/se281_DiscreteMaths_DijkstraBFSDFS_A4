package nz.ac.auckland.softeng281.a4;

// *******************************
// YOU CANNOT MODIFY THIS CLASS
// *******************************

public class InvalidPositionException extends RuntimeException{
    
    public InvalidPositionException(){}

    public InvalidPositionException(String msg){
    	super(msg);
    }

}