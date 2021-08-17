package com.dipinder.lookify.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dipinder.lookify.models.Song;


@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
	// this method retrieves all the songs from the database
	List<Song> findAll();
	List<Song> findByArtistContaining(String name);//camelcase
	List<Song> findTop10ByOrderByRatingDesc();
}
