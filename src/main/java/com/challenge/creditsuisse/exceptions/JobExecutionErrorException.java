
package com.challenge.creditsuisse.exceptions;

/**
 * @author evelynvieira
 */
public class JobExecutionErrorException extends RuntimeException {

	private static final long serialVersionUID = -9144193888369921370L;

	public JobExecutionErrorException() {
		super(String.format("Error initiating job execution."));
	}

}
