package co.eeikee.cidadespersistapi.service;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class LocationURI{
	
	public static URI build(Long id) {
		return ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{id}").
				buildAndExpand(id).
				toUri();
	}
}
