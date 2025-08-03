package in.TinyURL.Services;

import java.util.Optional;

import in.TinyURL.Entities.UrlMapping;

public interface UrlShortnerService {
   
	public UrlMapping createShortUrl(String longUrl, String customAlias);
	
	public Optional<UrlMapping> getOriginalUrl(String shortCode);
	
	public Optional<UrlMapping> getStats(String shortCode);
	
    
}
