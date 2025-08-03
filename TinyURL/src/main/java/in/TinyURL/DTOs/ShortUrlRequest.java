package in.TinyURL.DTOs;

import lombok.Data;

@Data
public class ShortUrlRequest {
  
	private String longUrl;
	private String customAlias;
}
