package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Musica;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {
}