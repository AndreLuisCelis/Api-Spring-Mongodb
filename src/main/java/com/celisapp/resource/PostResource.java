package com.celisapp.resource;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.celisapp.domain.Post;
import com.celisapp.resource.util.URL;
import com.celisapp.service.PostService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value ="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	

	@ApiOperation(value = "Busca um post pelo ID")
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById (@PathVariable String id){
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post) ;	
	}	
	@ApiOperation(value = "Busca um post pelo Titulo")
	@RequestMapping(value ="/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle (@RequestParam(value = "text",defaultValue = "" ) String text){
		text = URL.decodeParans(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list) ;	
	}
	
	@ApiOperation(value = "Busca um post por palavra chave e data")
	@RequestMapping(value ="/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullsearch (
			@RequestParam(value = "text",defaultValue = "" ) String text,
			@RequestParam(value = "minDate",defaultValue = "" ) String minDate,
			@RequestParam(value = "maxDate",defaultValue = "" ) String maxDate){
		text = URL.decodeParans(text);
		
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = service.fullSearch(text , min, max);
		return ResponseEntity.ok().body(list) ;	
	}
	@ApiOperation(value = "Adiciona um novo post")
	@RequestMapping(method = RequestMethod.POST )
	public void addPost( @RequestBody Post post) {
		service.addPost(post);
	}
	
	@ApiOperation(value = "Lista todos os Posts")
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<List<Post>> getAllPosts(){
		List<Post> list = service.getAllPosts();
		return ResponseEntity.ok().body(list) ;	
	}
}
