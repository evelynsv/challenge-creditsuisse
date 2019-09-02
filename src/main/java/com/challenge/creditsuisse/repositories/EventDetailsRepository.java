
package com.challenge.creditsuisse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.creditsuisse.entities.EventDetails;

/**
 * @author evelynvieira
 */
@Repository
public interface EventDetailsRepository extends JpaRepository<EventDetails, String> {

}
