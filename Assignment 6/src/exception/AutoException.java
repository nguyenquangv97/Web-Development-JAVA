package exception;
import java.io.*;
import java.util.Date;
import java.sql.Timestamp;

public class AutoException extends Exception implements FixInterface {
	private int errorNumber;
	private String errorMessage; 
	private final String EXCEPTION_LOG = "ExceptionLog.txt";

	public int getErrorNumber() {
		return this.errorNumber;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public AutoException(int errorNumber, String errorMessage) {
		this.errorNumber = errorNumber;
		this.errorMessage = errorMessage;
		
		// ----- Log to file ----- //
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		StringBuffer buff = new StringBuffer();
		boolean fixed = false; 
		while(fixed == false)
		try {
			FileWriter writer = new FileWriter(this.EXCEPTION_LOG, true);
			buff.append(ts).append(" ");
			buff.append("Error Code: ").append(this.errorNumber).append(" ");
			buff.append("Error Message: ").append(this.errorMessage).append('\n');
			writer.write(buff.toString());
			fixed = true; 
			writer.close();
		} catch(IOException ex) {
			fixed = false;
			this.fixError();
		}
	}

	public String fixError() {
		Fix1to100 fix1to100 = new Fix1to100();
		Fix101to200 fix2 = new Fix101to200();
		switch (this.errorNumber) {
		case 1: return fix1to100.fix1();
		case 2: return fix1to100.fix2();
		case 101: return fix2.fix101();
		case 102: return fix2.fix102();
		case 103: return fix2.fix103();
		case 200:break;
		}
		return null;
	}
	
}
