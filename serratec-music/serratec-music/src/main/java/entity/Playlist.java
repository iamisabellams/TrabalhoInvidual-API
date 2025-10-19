package entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da playlist")
    private Long id;

    @NotBlank(message = "O nome não pode ser vazio.")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres.")
    @Schema(description = "Nome da playlist", example = "As Melhores do Rock")
    private String nome;

    @Schema(description = "Descrição opcional da playlist", example = "Uma coletânea de clássicos do rock.")
    private String descricao;

    @ManyToOne 
    @JoinColumn(name = "usuario_id") 
    @JsonBackReference 
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
        name = "playlist_musica", 
        joinColumns = @JoinColumn(name = "playlist_id"), 
        inverseJoinColumns = @JoinColumn(name = "musica_id") 
    )
    @JsonManagedReference 
    @Schema(description = "Lista de músicas contidas na playlist")
    private Set<Musica> musicas;

    // --- GETTERS E SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(Set<Musica> musicas) {
        this.musicas = musicas;
    }
}