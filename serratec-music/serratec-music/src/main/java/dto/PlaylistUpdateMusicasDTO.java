package dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;


public class PlaylistUpdateMusicasDTO {

    @NotNull(message = "A lista de IDs não pode ser nula.")
    @Schema(description = "Lista completa de IDs das músicas que devem estar na playlist.", 
            example = "[1, 5, 8]")
    private List<Long> musicasIds; 

    public List<Long> getMusicasIds() {
        return musicasIds;
    }

    public void setMusicasIds(List<Long> musicasIds) {
        this.musicasIds = musicasIds;
    }
}