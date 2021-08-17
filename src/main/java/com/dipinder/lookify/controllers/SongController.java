package com.dipinder.lookify.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dipinder.lookify.models.Song;
import com.dipinder.lookify.services.SongService;

@Controller
public class SongController {
	
	private final SongService songService;
	
	public SongController(SongService songService) {
		this.songService = songService;
	}
	
	
	@GetMapping
	public String home() {
		return "index.jsp";
	}
	
	@RequestMapping("/dashboard")
	public String index(Model model) {
		List<Song> song = songService.allSongs();
		model.addAttribute("songs", song);
		
		return "dashboard.jsp";
	}
	
	@RequestMapping("/song/new")
	public String newSong(@ModelAttribute Song song) {
		return "new.jsp";
	}
	
	@RequestMapping(value="/song", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute Song song, BindingResult result) {
		if (result.hasErrors()) {
			return "new.jsp";
		} else {
			songService.createSong(song);
			return "redirect:/dashboard";
		}
	}
	
	@RequestMapping("/song/{id}")
	public String  show(Model model,
			@PathVariable("id") Long id) {
		Song song = (songService.findSong(id));
		model.addAttribute("song",song);
		return "show.jsp";
	}
	
	
	@RequestMapping("/song/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Song song = songService.findSong(id);
		model.addAttribute("song", song);
		return "edit.jsp";
	}
	
	@RequestMapping(value="/song/{id}", method=RequestMethod.PUT)
	public String update(@Valid @ModelAttribute("song") Song song, BindingResult result) {
		if (result.hasErrors()) {
			return "edit.jsp";
		} else {
			songService.updateSong(song);
			return "redirect:/song";
		}
	}
	
	@RequestMapping(value="/song/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Long id) {
		songService.deleteSong(id);
		return "redirect:/dashboard";
	}
	
	@PostMapping("/search")
	public String search(@RequestParam("searchArtist") String searchArtist) {
		return "redirect:/search/"+searchArtist;
	}
	
	@GetMapping("/search/{searchArtist}")
	public String find(@PathVariable("searchArtist") String searchArtist,
			Model model) {
		model.addAttribute("songs",songService.findByArtistName(searchArtist));
		return "result.jsp";
	}
	
	@GetMapping("/topten")
	public String topten(Model model) {
		model.addAttribute("songs",songService.findTopTen());
		return "result.jsp";
	}
	
}