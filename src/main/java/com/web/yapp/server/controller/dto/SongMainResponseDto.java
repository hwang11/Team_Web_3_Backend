package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.Song;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SongMainResponseDto {
    private String title;
    private String coverUrl;
    private String songUrl;

    public SongMainResponseDto(Song Entity){
        this.title = Entity.getTitle();
        this.coverUrl = Entity.getCoverUrl();
        this.songUrl = Entity.getSongUrl();
    }
}