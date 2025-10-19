package repository; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Artista; 

@Repository 
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}