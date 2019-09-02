
package com.challenge.creditsuisse.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.challenge.creditsuisse.entities.EventDetails;
import com.challenge.creditsuisse.repositories.EventDetailsRepository;

/**
 * @author evelynvieira
 */
@Component
public class EventItemWriter implements ItemWriter<EventDetails> {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventItemWriter.class);

	@Autowired
	private EventDetailsRepository eventRepository;

	@Override
	public void write(final List<? extends EventDetails> items) throws Exception {
		LOGGER.info("write(eventDetails={})", items);

		eventRepository.saveAll(items);

	}

}
