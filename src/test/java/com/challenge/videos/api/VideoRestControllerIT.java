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


public class VideoRestControllerIT {


    @Test
    void deveRegistrarVideo() {
        fail("Teste não implementado!");
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
