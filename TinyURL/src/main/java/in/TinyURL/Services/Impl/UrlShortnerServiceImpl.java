package in.TinyURL.Services.Impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.TinyURL.Entities.UrlMapping;
import in.TinyURL.Repositories.UrlMappingRepository;
import in.TinyURL.Services.UrlShortnerService;

@Service
public class UrlShortnerServiceImpl  implements UrlShortnerService{
	
	@Autowired
	private UrlMappingRepository urlMappingRepository;

	@Override
	public UrlMapping createShortUrl(String longUrl, String customAlias) {
		if(customAlias != null && !customAlias.isEmpty()) {
			String shortCode = generateShortCode();
			UrlMapping mapping = new UrlMapping();
			mapping.setLongUrl(longUrl);
			mapping.setShortCode(shortCode);
			return urlMappingRepository.save(mapping);
		}
		else {
			return null;
		}
		
	}

	@Override
	public Optional<UrlMapping> getOriginalUrl(String shortCode) {
		Optional<UrlMapping> mOptional = urlMappingRepository.findByShortCode(shortCode);
		if(mOptional.isPresent()) {
			UrlMapping mapping = mOptional.get();
			mapping.setAccessCount(mapping.getAccessCount()+1);
			urlMappingRepository.save(mapping);
		}
		return mOptional;
	}

	@Override
	public Optional<UrlMapping> getStats(String shortCode) {
		
		return urlMappingRepository.findByShortCode(shortCode);
	}
	
	private String generateShortCode() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code.append(chars.charAt(random.nextInt(chars.length())));
        }
        return code.toString();
    }

}
