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

import entity.Musica;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import repository.MusicaRepository;

@RestController
@RequestMapping("/musicas")
@Tag(name = "Músicas", description = "Endpoints para gerenciamento de músicas")
public class MusicaController {

    @Autowired
    private MusicaRepository musicaRepository; 

    @PostMapping
    @Operation(summary = "Cria uma nova música", description = "Cadastra uma nova música no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Música criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos (ex: título em branco)")
    })
    public ResponseEntity<Musica> criarMusica(@Valid @RequestBody Musica musica) { 
        Musica musicaSalva = musicaRepository.save(musica); 
        return new ResponseEntity<>(musicaSalva, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Lista todas as músicas", description = "Retorna uma lista de todas as músicas cadastradas.")
    public List<Musica> listarMusicas() {
        return musicaRepository.findAll(); 
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma música por ID", description = "Retorna uma música específica baseada no seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Música encontrada"),
        @ApiResponse(responseCode = "404", description = "Música não encontrada")
    })
    public ResponseEntity<Musica> buscarPorId(@PathVariable Long id) {
        Optional<Musica> musica = musicaRepository.findById(id); 
        if (musica.isPresent()) {
            return ResponseEntity.ok(musica.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma música existente", description = "Atualiza os dados de uma música baseada no seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Música atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "Música não encontrada")
    })
    public ResponseEntity<Musica> atualizarMusica(@PathVariable Long id, @Valid @RequestBody Musica musicaAtualizado) { 
        if (!musicaRepository.existsById(id)) { 
            return ResponseEntity.notFound().build();
        }
        musicaAtualizado.setId(id);
        Musica musicaSalva = musicaRepository.save(musicaAtualizado); 
        return ResponseEntity.ok(musicaSalva);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma música", description = "Remove uma música do sistema baseada no seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Música deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Música não encontrada")
    })
    public ResponseEntity<Void> deletarMusica(@PathVariable Long id) {
        if (!musicaRepository.existsById(id)) { 
            return ResponseEntity.notFound().build();
        }
        musicaRepository.deleteById(id); 
        return ResponseEntity.noContent().build(); 
    }
}