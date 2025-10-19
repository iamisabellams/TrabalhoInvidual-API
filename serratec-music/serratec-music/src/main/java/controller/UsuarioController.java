package controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entity.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários e seus perfis")
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @Operation(summary = "Cria um novo usuário e seu perfil", 
               description = "Cadastra um novo usuário e seu perfil associado em uma única requisição")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário e perfil criados com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
    }
    
    @GetMapping
    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados")
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um usuário por ID", description = "Retorna um usuário específico (e seu perfil) baseado no ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um usuário existente", description = "Atualiza os dados de um usuário e/ou seu perfil baseado no ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuarioAtualizado) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        usuarioAtualizado.setId(id);
        
        if (usuarioAtualizado.getPerfil() != null) {
            Optional<Usuario> usuarioAntigo = usuarioRepository.findById(id);
            if (usuarioAntigo.isPresent() && usuarioAntigo.get().getPerfil() != null) {
                Long idPerfilAntigo = usuarioAntigo.get().getPerfil().getId();
                usuarioAtualizado.getPerfil().setId(idPerfilAntigo);
            }
            usuarioAtualizado.setPerfil(usuarioAtualizado.getPerfil());
        }

        Usuario usuarioSalvo = usuarioRepository.save(usuarioAtualizado);
        return ResponseEntity.ok(usuarioSalvo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um usuário", description = "Remove um usuário e seu perfil associado do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}