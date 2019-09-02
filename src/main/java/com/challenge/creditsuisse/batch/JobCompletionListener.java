
package com.challenge.creditsuisse.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import com.challenge.creditsuisse.business.EventBusiness;
/**
 * 
 * Listens the job execution.
 * 
 * @author evelynvieira
 *
 */
@Component
public class JobCompletionListener extends JobExecutionListenerSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionListener.class);

	public JobCompletionListener() {}

	@Override
	public void beforeJob(final JobExecution jobExecution) {
		LOGGER.info("Batch started: {}", jobExecution);
		super.beforeJob(jobExecution);
	}

	@Override
	public void afterJob(final JobExecution jobExecution) {
		EventBusiness.eventMap.clear();

		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOGGER.info("Batch ended: {}", jobExecution);
		}
	}

}
