package in.TinyURL.Controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.TinyURL.DTOs.ShortUrlRequest;
import in.TinyURL.Entities.UrlMapping;
import in.TinyURL.Services.UrlShortnerService;

@RestController
@RequestMapping("/api")
public class UrlShortnerController {
   
	@Autowired
	private UrlShortnerService urlShortnerService;
	
	@PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody ShortUrlRequest request) {
        String longUrl = request.getLongUrl();
        String customAlias = request.getCustomAlias(); 
        UrlMapping mapping = urlShortnerService.createShortUrl(longUrl, customAlias);
        if(mapping != null) {
        	return new ResponseEntity<String>("shortUrl : "+"http://localhost:8083/" + mapping.getShortCode(),HttpStatus.CREATED);
        }
        else {
        	return new ResponseEntity<String>("customAlias is required",HttpStatus.BAD_REQUEST);
       } 
    }
	
	@GetMapping("/{shortCode}")
	public ResponseEntity<?> redirect(@PathVariable String shortCode) {
	    Optional<UrlMapping> mapping = urlShortnerService.getOriginalUrl(shortCode);
	    if (mapping.isEmpty()) {
	        return new ResponseEntity<String>("Short Code is Invalid",HttpStatus.NOT_FOUND);
	    }

	    return ResponseEntity.ok(Map.of("redirectTo", mapping.get().getLongUrl()));
	}
	
	@GetMapping("/stats/{shortCode}")
    public ResponseEntity<?> getStats(@PathVariable String shortCode) {
        Optional<UrlMapping> mapping = urlShortnerService.getStats(shortCode);
        if(mapping != null) {
        	return new ResponseEntity<>(mapping,HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<>("Short URL Not Found",HttpStatus.NOT_FOUND);
       } 
    }
}
