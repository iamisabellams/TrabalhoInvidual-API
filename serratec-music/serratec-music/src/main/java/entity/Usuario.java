package entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do usuário")
    private Long id;

    @NotBlank(message = "O nome não pode ser vazio.")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
    @Schema(description = "Nome completo do usuário", example = "Ana da Silva")
    private String nome;

    @NotBlank(message = "O email não pode ser vazio.")
    @Email(message = "Formato de email inválido.")
    @Column(unique = true) 
    @Schema(description = "Email do usuário (deve ser único)", example = "ana.silva@email.com")
    private String email;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference 
    @Schema(description = "Perfil de dados pessoais do usuário")
    private Perfil perfil;

    @OneToMany(mappedBy = "usuario") 
    @JsonManagedReference 
    @Schema(description = "Lista de playlists criadas pelo usuário")
    private Set<Playlist> playlists;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
        if (perfil != null) {
            perfil.setUsuario(this);
        }
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }
}