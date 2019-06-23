package servlet;

import java.io.*;

public interface Interactionable {
	
	public Object retrieveObject(ObjectInputStream in);
	public void sendRequest(ObjectOutputStream out, Object togo);
}
