package entity; 

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import enumerable.GeneroMusical;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da música")
    private Long id;

    @NotBlank(message = "O título não pode ser vazio.")
    @Size(min = 2, max = 100, message = "O título deve ter entre 2 e 100 caracteres.")
    @Schema(description = "Título da música", example = "Bohemian Rhapsody")
    private String titulo;

    @NotNull(message = "Os minutos não podem ser nulos.")
    @Schema(description = "Duração da música em minutos", example = "5")
    private Integer minutos;

    @NotNull(message = "O gênero não pode ser nulo.")
    @Enumerated(EnumType.STRING) 
    @Schema(description = "Gênero musical", example = "ROCK")
    private GeneroMusical genero; 
    
    @ManyToMany
    @JoinTable(
        name = "musica_artista", 
        joinColumns = @JoinColumn(name = "musica_id"), 
        inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    @JsonManagedReference 
    @Schema(description = "Lista de artistas que participam da música")
    private Set<Artista> artistas;

    @ManyToMany(mappedBy = "musicas") 
    @JsonBackReference
    private Set<Playlist> playlists;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public GeneroMusical getGenero() {
        return genero;
    }

    public void setGenero(GeneroMusical genero) {
        this.genero = genero;
    }

    public Set<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(Set<Artista> artistas) {
        this.artistas = artistas;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }
}