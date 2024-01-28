package com.challenge.videos.api;

import com.challenge.videos.controllers.VideoController;
import com.challenge.videos.enumeration.VideosCategorias;
import com.challenge.videos.external.model.VideoModel;
import com.challenge.videos.external.repository.VideoRepository;
import com.challenge.videos.records.VideoRecord;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Testcontainers
@AutoConfigureWebTestClient
public class VideoRestControllerIT {
    @Container
    public static MongoDBContainer mongoDBContainer =
            new MongoDBContainer(DockerImageName.parse("mongo:latest"));

    @Autowired
    private WebTestClient webTestClient;

    @BeforeAll
    static void setup(@Autowired VideoRepository videoRepository) {
        mongoDBContainer.start();
        VideoModel video1 = new VideoModel(1, "Rambo", "Filme de Guerra Rambo1","http://filmes.com.br/rambo", LocalDate.of(1985,10,01), VideosCategorias.GUERRA, 1 ,2);
        VideoModel video2 = new VideoModel(2, "Uma linda Mulher", "Filme Romance", "http://filmes.com.br/linda-mulher", LocalDate.of(1999,12,01),VideosCategorias.ROMANCE, 2, 3);
        VideoModel video3 = new VideoModel(3, "Tropa de Elite", "Filme policial brasileiro","http://filmes.com.br/tropa-elite", LocalDate.of(2007,10,05),VideosCategorias.ACAO, 3,4);

        var registro1 = videoRepository.save(video1);
        var registro2 = videoRepository.save(video2);
        var registro3 =videoRepository.save(video3);

        assertThat(registro1.block()).isEqualTo(video1);
        assertThat(registro2.block()).isEqualTo(video2);
        assertThat(registro3.block()).isEqualTo(video3);
    }

    @AfterAll
    static void tearDown(){
        mongoDBContainer.close();
    }


    @Test
    void deveRegistrarVideo() {
        VideoRecord novoVideo = new VideoRecord(4, "Megatubarão 2", "Filme de tubarão","http://filmes.com.br/Megatubarao-2", LocalDate.of(2023,8,03),VideosCategorias.ACAO, 0,0);

        webTestClient.post()
                .uri("/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(novoVideo)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(VideoModel.class)
                .value(videoModel -> {

                    assertThat(videoModel.getId()).isNotNull();
                    assertThat(videoModel.getId()).isEqualTo(novoVideo.id());
                });
    }

    @Test
    void deveAtualizarVideo() {
        fail("Teste não implementado!");
    }

    @Test
    void deveDeletarVideo() {
        fail("Teste não implementado!");
    }

    @Test
    void develistarVideos() {
        fail("Teste não implementado!");

    }

    @Test
    void develistarVideosPorTitulo() {
        fail("Teste não implementado!");
    }

    @Test
    void  develistarVideosPorData() {
        fail("Teste não implementado!");
    }

    @Test
    void develistarVideosPorCategoria() {
        fail("Teste não implementado!");
    }

    @Test
    void develistarVideosRecomendados() {
        fail("Teste não implementado!");
    }

    @Test
    void deveBuscarEstatistica() {
        fail("Teste não implementado!");
    }
}
