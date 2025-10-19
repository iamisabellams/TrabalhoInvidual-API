package controller;

import java.util.HashSet;
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

import dto.PlaylistRequestDTO;
import dto.PlaylistUpdateMusicasDTO;
import entity.Musica;
import entity.Playlist;
import entity.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import repository.MusicaRepository;
import repository.PlaylistRepository;
import repository.UsuarioRepository;

@RestController
@RequestMapping("/playlists")
@Tag(name = "Playlists", description = "Endpoints para gerenciamento de playlists")
public class PlaylistController {  

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MusicaRepository musicaRepository; 

    @GetMapping
    @Operation(summary = "Lista todas as playlists", description = "Retorna uma lista de todas as playlists cadastradas.")
    public List<Playlist> listarPlaylists() {
        return playlistRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma playlist por ID", description = "Retorna uma playlist específica baseada no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Playlist encontrada"),
        @ApiResponse(responseCode = "404", description = "Playlist não encontrada")
    })
    public ResponseEntity<Playlist> buscarPorId(@PathVariable Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);
        if (playlist.isPresent()) {
            return ResponseEntity.ok(playlist.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma playlist", description = "Remove uma playlist do sistema baseado no seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Playlist deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Playlist não encontrada")
    })
    public ResponseEntity<Void> deletarPlaylist(@PathVariable Long id) {
        if (!playlistRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        playlistRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "Cria uma nova playlist", 
               description = "Cria uma nova playlist associada a um usuário existente, informando o 'usuarioId' no corpo do JSON")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Playlist criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado com o ID informado")
    })
    public ResponseEntity<Playlist> criarPlaylist(@Valid @RequestBody PlaylistRequestDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(dto.getUsuarioId());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }

        Playlist novaPlaylist = new Playlist();
        novaPlaylist.setNome(dto.getNome());
        novaPlaylist.setDescricao(dto.getDescricao());
        novaPlaylist.setUsuario(usuarioOpt.get()); 

        Playlist playlistSalva = playlistRepository.save(novaPlaylist);
        return new ResponseEntity<>(playlistSalva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/musicas")
    @Operation(summary = "Atualiza a lista de músicas de uma playlist", 
               description = "Substitui a lista de músicas atual de uma playlist por uma nova lista, informando os IDs das músicas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de músicas atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "JSON inválido"),
        @ApiResponse(responseCode = "404", description = "Playlist não encontrada com o ID informado")
    })
    public ResponseEntity<Playlist> atualizarMusicasDaPlaylist(
            @PathVariable Long id, 
            @Valid @RequestBody PlaylistUpdateMusicasDTO dto) {
        Optional<Playlist> playlistOpt = playlistRepository.findById(id);
        if (playlistOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Musica> musicas = musicaRepository.findAllById(dto.getMusicasIds());

        Playlist playlist = playlistOpt.get();
        playlist.setMusicas(new HashSet<>(musicas)); 

        Playlist playlistSalva = playlistRepository.save(playlist);
        return ResponseEntity.ok(playlistSalva);
    }
}