package com.web.yapp.server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_SONG")
@Entity
public class Song extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SONG_ID")
    private Long id;

    @ManyToOne // 노래(n) : 아티스트(1) 한명의 아티스트가 여러 곡 가질 수 있음
    @JoinColumn(name = "MUSICIAN_ID")
    private Musician musician;

    @Column(name = "SONG_TITLE") //노래제목
    private String title;

    @Column(name = "SONG_COVER_URL") //커버이미지 파일 경로
    private String coverUrl;

    @Column(name = "SONG_URL") //음원파일 경로
    private String songUrl;

    @Column(name = "SONG_RPRSN") //대표곡인지 아닌지 구분 0 : 일반 or 1 : 대표
    private int represent;

    @Builder
    public Song(String title, String coverUrl, String songUrl, int represent, Musician musician) {
        this.title = title;
        this.coverUrl = coverUrl;
        this.songUrl = songUrl;
        this.represent = represent;
        this.musician = musician;
    }
}

