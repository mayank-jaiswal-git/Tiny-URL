package in.TinyURL.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.TinyURL.Entities.UrlMapping;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Integer> {

	Optional<UrlMapping> findByShortCode(String shortCode);
}

