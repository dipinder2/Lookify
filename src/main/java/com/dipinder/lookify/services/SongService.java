package com.dipinder.lookify.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dipinder.lookify.models.Song;
import com.dipinder.lookify.repos.SongRepository;



@Service
public class SongService {
	// adding the song repository as a dependency
	private final SongRepository songRepository;
	
	public SongService(SongRepository songRepository) {
		this.songRepository = songRepository;
	}
	// returns all the songs
	public List<Song> allSongs() {
		
		return songRepository.findAll();
	}
	// creates a song
	public Song createSong(Song b) {
		return songRepository.save(b);
	}
	// retrieves a song
	public Song findSong(Long id) {
		Optional<Song> optionalSong = songRepository.findById(id);
		if(optionalSong.isPresent()) {
			return optionalSong.get();
		} else {
			return null;
		}
	}
	public Song updateSong(Song b) {
		return songRepository.save(b);
	}
	public void deleteSong(Long id) {
		songRepository.deleteById(id);
	}
	public List<Song> findByArtistName(String name){
		List<Song> songs = songRepository.findByArtistContaining(name);
		return songs;
	}
	public List<Song> findTopTen(){
		List<Song> songs = songRepository.findTop10ByOrderByRatingDesc();
		return songs;
	}
}