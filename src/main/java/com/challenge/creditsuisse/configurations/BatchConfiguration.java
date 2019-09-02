
package com.challenge.creditsuisse.configurations;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.JsonLineMapper;
import org.springframework.batch.item.file.separator.JsonRecordSeparatorPolicy;
import org.springframework.batch.item.file.separator.RecordSeparatorPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.challenge.creditsuisse.batch.EventItemProcessor;
import com.challenge.creditsuisse.batch.EventItemWriter;
import com.challenge.creditsuisse.batch.JobCompletionListener;
import com.challenge.creditsuisse.batch.StepParameters;
import com.challenge.creditsuisse.canonical.Event;
import com.challenge.creditsuisse.entities.EventDetails;

/**
 * 
 * Batch configuration and EventItemReader implementation.
 * 
 * @author evelynvieira
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private StepParameters stepParameters;

	@Autowired
	private JobCompletionListener jobListener;

	@Autowired
	private JobRepository jobRepository;

	@Bean
	@Qualifier("eventJobLauncher")
	public JobLauncher eventJobLauncher() throws Exception {
		final SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
		simpleJobLauncher.setJobRepository(jobRepository);
		simpleJobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor("Event."));
		return simpleJobLauncher;
	}

	@Bean
	public Job job() throws Exception {
		return jobBuilderFactory.get("job")
				.incrementer(new RunIdIncrementer())
				.repository(jobRepository)
				.listener(jobListener)
				.flow(step())
				.end()
				.build();
	}

	@Bean
	public Step step() throws Exception {
		return stepBuilderFactory.get("step")
				.<Event, EventDetails>chunk(10)
				.reader(eventItemReader())
				.processor(processor())
				.writer(writer())
				.build();
	}

	@Bean
	@StepScope
	public FlatFileItemReader<Event> eventItemReader() throws Exception {
		final FlatFileItemReader<Event> reader = new FlatFileItemReader<Event>();
		reader.setResource(new FileSystemResource(stepParameters.getFilePath()));
		reader.setRecordSeparatorPolicy(eventRecordSeparatorPolicy());
		reader.setLineMapper(eventLineMapper());
		return reader;
	}

	@Bean
	public RecordSeparatorPolicy eventRecordSeparatorPolicy() {
		return new JsonRecordSeparatorPolicy();
	}

	@Bean
	public LineMapper<Event> eventLineMapper() {
		final WrappedJsonLineMapper lineMapper = new WrappedJsonLineMapper();
		lineMapper.setDelegate(targetEventsLineMapper());
		return lineMapper;
	}

	@Bean
	public JsonLineMapper targetEventsLineMapper() {
		return new JsonLineMapper();
	}

	@Bean
	public EventItemProcessor processor() {
		return new EventItemProcessor();
	}

	@Bean
	public EventItemWriter writer() {
		return new EventItemWriter();
	}
}
