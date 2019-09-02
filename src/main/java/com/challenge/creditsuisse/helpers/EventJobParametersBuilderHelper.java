
package com.challenge.creditsuisse.helpers;

import java.util.Date;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
/**
 * 
 * Helps to create a JobParametersBuilder.
 * 
 * @author evelynvieira
 *
 */
public class EventJobParametersBuilderHelper {

	public static final String FILE_PATH = "filePath";
	public static final String DATE = "date";

	public static JobParameters create(final String filePath) {

		return new JobParametersBuilder().addString(FILE_PATH, filePath).addDate(DATE, new Date()).toJobParameters();
	}

}
