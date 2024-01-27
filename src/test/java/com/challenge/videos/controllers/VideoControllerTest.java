package com.challenge.videos.controllers;

import com.challenge.videos.entities.VideoEntity;
import com.challenge.videos.enumeration.VideosCategorias;
import com.challenge.videos.external.model.VideoModel;
import com.challenge.videos.external.repository.VideoRepository;
import com.challenge.videos.gateways.VideoGateway;
import com.challenge.videos.interfaces.IVideoGateway;
import com.challenge.videos.presenters.VideoPresenter;
import com.challenge.videos.records.VideoRecord;
import com.challenge.videos.usecase.registrarVideoUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VideoControllerTest {


    // Trabalhar com Fake - Mockito.
    AutoCloseable mock;

    private VideoController videoController;

    @Mock
    private VideoRepository videoRepository;

    @BeforeEach  // antes de cada teste
    void setup(){

       mock = MockitoAnnotations.openMocks(this);
       videoController = new VideoController();
    }

    // Limpar o mock da memoria.
    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }


    @Test
    public void deveRegistrarVideo() throws IllegalAccessException {

        // Assert
        Integer id = 1;
        String titulo = "Rambo1";
        String descricao = "Filme de Guerra";
        String urlVideo = "http://www.filmes.com.br/rambo1";
        LocalDate dataPublicacao = LocalDate.of(1985,10,01);
        VideosCategorias categoria = VideosCategorias.GUERRA;
        Integer favorito = 0;
        Integer visualizacoes = 100;

        VideoRecord videoRecord = new VideoRecord(
                id, titulo,  descricao, urlVideo, dataPublicacao, categoria, favorito, visualizacoes
        );

        VideoModel videoModel = new VideoModel(
                id, titulo,  descricao, urlVideo, dataPublicacao, categoria, favorito, visualizacoes);

        when(videoRepository.save(any(VideoModel.class)))
                .thenReturn(Mono.just(videoModel));

        var monoVideo = videoController.registrarVideo(videoRecord,videoRepository);

        // Assert
        monoVideo.subscribe(mono -> {
            assertThat(mono.getId()).isNotNull().isEqualTo(videoModel.getId());
        });

        verify(videoRepository, times(1)).save(any(VideoModel.class));


    }

    @Test
    public void GerarExcecao_NoDeveAtualizarVideo_RegistroNaoExiste() {

        // Assert
        Integer id = 1;
        String titulo = "Rambo1";
        String descricao = "Filme de Guerra";
        String urlVideo = "http://www.filmes.com.br/rambo1";
        LocalDate dataPublicacao = LocalDate.of(1985,10,01);
        VideosCategorias categoria = VideosCategorias.GUERRA;
        Integer favorito = 0;
        Integer visualizacoes = 100;

        VideoRecord videoRecord = new VideoRecord(
                id, titulo,  descricao, urlVideo, dataPublicacao, categoria, favorito, visualizacoes
        );


        // Act
        //var monoVideo = videoController.atualizarVideo(videoRecord,videoRepository);

        // Assert
        assertThatThrownBy(() -> videoController.atualizarVideo(videoRecord,videoRepository))
                .isInstanceOf(RuntimeException.class);


        verify(videoRepository, times(1)).findById(any(Integer.class));


    }

    @Test
    public void DeveAtualizarVideo()  {

        // Assert
        Integer id = 1;
        String titulo = "Rambo1";
        String descricao = "Filme de Guerra";
        String urlVideo = "http://www.filmes.com.br/rambo1";
        LocalDate dataPublicacao = LocalDate.of(1985,10,01);
        VideosCategorias categoria = VideosCategorias.GUERRA;
        Integer favorito = 1500; // Ajuste
        Integer visualizacoes = 100;

        VideoRecord videoRecord = new VideoRecord(
                id, titulo,  descricao, urlVideo, dataPublicacao, categoria, favorito, visualizacoes
        );


        VideoModel videoModel = new VideoModel(
                id, titulo,  descricao, urlVideo, dataPublicacao, categoria, favorito, visualizacoes);

        when(videoRepository.findById(any(Integer.class)))
                .thenReturn(Mono.just(videoModel));

        var videoAtualizado = videoController.atualizarVideo(videoRecord,videoRepository);


        // Assert

        verify(videoRepository, times(1)).findById(any(Integer.class));
        verify(videoRepository, times(1)).save(any(VideoModel.class));


    }


    @Test
    public void DeveDeletarVideo() {

        Integer id = 1;
        assertThatThrownBy(() -> videoController.deletarVideo(id,videoRepository))
                .isInstanceOf(RuntimeException.class);


        // Assert
        verify(videoRepository, times(1)).findById(any(Integer.class));
    }


    @Test
    public void develistarVideos() {
        fail("Teste não implementado!");
    }


    @Test
    public  void  develistarVideosPorTitulo() {
        fail("Teste não implementado!");

    }

    @Test
    public  void develistarVideosPorData() {
        fail("Teste não implementado!");

    }

    @Test
    public  void develistarVideosPorCategoria() {
        fail("Teste não implementado!");

    }

    @Test
    public void develistarVideosRecomendados() {
        fail("Teste não implementado!");
    }


    @Test
    public void deveBuscarEstatisticas() {
        fail("Teste não implementado!");
    }

}
