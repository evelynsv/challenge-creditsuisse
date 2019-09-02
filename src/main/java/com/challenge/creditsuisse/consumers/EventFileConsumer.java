
package com.challenge.creditsuisse.consumers;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.challenge.creditsuisse.exceptions.JobExecutionErrorException;
import com.challenge.creditsuisse.helpers.EventJobParametersBuilderHelper;
/**
 * 
 * @author evelynvieira
 *
 */
@Component
public class EventFileConsumer implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventFileConsumer.class);

	@Autowired
	private JobLauncher eventJobLauncher;

	@Autowired
	private Job job;

	@Value("${logfile.path}")
	private String filePathBatch;

	@Value("${file-watcher.path}")
	private String filePath;

	@Override
	public void run(final String... args) throws Exception {

		final WatchService watchService = FileSystems.getDefault().newWatchService();
		final Path path = Paths.get(filePath);

		path.register(watchService,
				StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);

		WatchKey key;
		while ((key = watchService.take()) != null) {
			for (final WatchEvent<?> event : key.pollEvents()) {
				LOGGER.info("watchService(eventType={}, file={})", event.kind(), event.context());
				jobRun();
			}
			key.reset();
		}
	}

	public void jobRun() {
		final JobParameters jobParameters = EventJobParametersBuilderHelper.create(filePathBatch);

		try {
			eventJobLauncher.run(job, jobParameters);
		} catch (final Exception e) {
			throw new JobExecutionErrorException();
		}
	}

}
