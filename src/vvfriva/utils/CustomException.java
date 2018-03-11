package vvfriva.utils;

public class CustomException  extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2850865643106927866L;
	public CustomException (StringBuilder message) {
		super(message.toString());
	}
}
