
package com.challenge.creditsuisse.batch;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author evelynvieira
 */
@Component
@StepScope
public class StepParameters {

	@Value("#{jobParameters['filePath']}")
	private String filePath;

	public String getFilePath() {
		return filePath;
	}

}
