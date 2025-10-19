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

import entity.Artista;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import repository.ArtistaRepository;

@RestController
@RequestMapping("/artistas")
@Tag(name = "Artistas", description = "Endpoints para gerenciamento de artistas") 
public class ArtistaController {

    @Autowired
    private ArtistaRepository artistaRepository;

    @PostMapping
    @Operation(summary = "Cria um novo artista", description = "Cadastra um novo artista no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Artista criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos (ex: nome em branco)")
    })
    public ResponseEntity<Artista> criarArtista(@Valid @RequestBody Artista artista) { 
        Artista artistaSalvo = artistaRepository.save(artista);
        return new ResponseEntity<>(artistaSalvo, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Lista todos os artistas", description = "Retorna uma lista de todos os artistas cadastrados.")
    public List<Artista> listarArtistas() { 
        return artistaRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um artista por ID", description = "Retorna um artista específico baseado no seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Artista encontrado"),
        @ApiResponse(responseCode = "404", description = "Artista não encontrado")
    })
    public ResponseEntity<Artista> buscarPorId(@PathVariable Long id) {
        Optional<Artista> artista = artistaRepository.findById(id);
        if (artista.isPresent()) {
            return ResponseEntity.ok(artista.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um artista existente", description = "Atualiza os dados de um artista baseado no seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Artista atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "Artista não encontrado")
    })
    public ResponseEntity<Artista> atualizarArtista(@PathVariable Long id, @Valid @RequestBody Artista artistaAtualizado) { 
        if (!artistaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        artistaAtualizado.setId(id);
        Artista artistaSalvo = artistaRepository.save(artistaAtualizado);
        return ResponseEntity.ok(artistaSalvo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um artista", description = "Remove um artista do sistema baseado no seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Artista deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Artista não encontrado")
    })
    public ResponseEntity<Void> deletarArtista(@PathVariable Long id) {
        if (!artistaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        artistaRepository.deleteById(id);
        return ResponseEntity.noContent().build(); 
    }
}