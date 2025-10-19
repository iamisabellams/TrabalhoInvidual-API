package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class PlaylistRequestDTO {

    @NotBlank(message = "O nome não pode ser vazio.")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres.")
    @Schema(description = "Nome da playlist", example = "As Melhores do Rock")
    private String nome;

    @Schema(description = "Descrição opcional da playlist", example = "Uma coletânea de clássicos do rock.")
    private String descricao;

    @NotNull(message = "O ID do usuário é obrigatório.")
    @Schema(description = "ID do usuário que está criando a playlist", example = "1")
    private Long usuarioId; 

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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}