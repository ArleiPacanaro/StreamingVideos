package com.challenge.videos.controllers;

import com.challenge.videos.entities.VideoEntity;
import com.challenge.videos.enumeration.VideosCategorias;
import com.challenge.videos.external.model.VideoEstatisticasModel;
import com.challenge.videos.external.model.VideoModel;
import com.challenge.videos.external.repository.VideoRepository;
import com.challenge.videos.gateways.VideoGateway;
import com.challenge.videos.interfaces.IVideoGateway;
import com.challenge.videos.presenters.VideoPresenter;
import com.challenge.videos.records.VideoRecord;
import com.challenge.videos.usecase.registrarVideoUseCase;

import java.sql.SQLException;
import java.time.LocalDate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * API Rest para interface entre FRONT END e camada de CONTROLLER da Arquitetura Limpa .
 */

@Component
public class VideoController {

  /**
   * Método de registrar os videos.
   */
  public Mono<VideoModel> registrarVideo(VideoRecord videoRecord, VideoRepository databaseClient)
          {

    IVideoGateway videoGateway = new VideoGateway(databaseClient);
            VideoEntity videoEntity = null;
            try {
              videoEntity = registrarVideoUseCase.init(videoRecord);
            } catch (IllegalAccessException e) {
              throw new RuntimeException(e.getMessage());
            }
            return VideoPresenter.toRecord(videoGateway.registrarVideo(videoEntity));

  }

  /**
   * Método de atualizar os videos.
   */
  public Mono<VideoModel> atualizarVideo(VideoRecord videoRecord, VideoRepository databaseClient)
           {
    IVideoGateway videoGateway = new VideoGateway(databaseClient);
             VideoEntity videoEntity = null;
             try {
               videoEntity = registrarVideoUseCase.init(videoRecord);
             } catch (IllegalAccessException e) {
               throw new RuntimeException(e);
             }
             var videoEncontrado = videoGateway.buscarVideoPorId(videoRecord.id());
    if (videoEncontrado != null) {
      return VideoPresenter.toRecord(videoGateway.registrarVideo(videoEntity));
    } else {
      throw new RuntimeException("Não foi encontrado nenhum video com esse ID");
    }
  }
  /**
   * Método de deletar os videos.
   */

  public Mono<Void> deletarVideo(Integer id, VideoRepository databaseClient)
          {
    IVideoGateway videoGateway = new VideoGateway(databaseClient);
    var videoEncontrado = videoGateway.buscarVideoPorId(id);
    if (videoEncontrado != null) {
      return videoGateway.deletarVideoPorId(id);
    } else {
      throw new RuntimeException(
              "Não foi encontrado na base um Video cadastrado com esse ID");
    }
  }

  /**
   * Método de deletar os videos.
   */

  public Flux<VideoModel> listarVideos(int page, int size, VideoRepository databaseClient) {
    IVideoGateway videoGateway = new VideoGateway(databaseClient);

    PageRequest pageRequest =
            PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dataPublicacao"));

    return VideoPresenter.toList(videoGateway.listarVideos(pageRequest));
  }

  /**
   * Método de deletar os videos.
   */

  public  Flux<VideoModel> listarVideosPorTitulo(String titulo, VideoRepository databaseClient) {
    IVideoGateway videoGateway = new VideoGateway(databaseClient);


    return VideoPresenter.toList(videoGateway.listarVideosPorTitulo(titulo));
  }

  public  Flux<VideoModel> listarVideosPorData(LocalDate data, VideoRepository databaseClient) {
    IVideoGateway videoGateway = new VideoGateway(databaseClient);
    return VideoPresenter.toList(videoGateway.listarVideosPorDataPublicacao(data));
  }

  public  Flux<VideoModel> listarVideosPorCategoria(VideosCategorias categoria,
                                                    VideoRepository databaseClient) {
    IVideoGateway videoGateway = new VideoGateway(databaseClient);
    return VideoPresenter.toList(videoGateway.listarVideosPorCategoria(categoria));
  }

  /**
   * Método de listar os videos recomendados.
   */

  public Flux<VideoModel> listarVideosRecomendados(VideosCategorias categoria,
                                                   VideoRepository databaseClient) {

    IVideoGateway videoGateway = new VideoGateway(databaseClient);
    Integer qtdFavoritados = 1;
    return VideoPresenter.toList(videoGateway.listarVideosRecomendados(categoria, qtdFavoritados));
  }


  /**
   * Método de listar os videos recomendados.
   */

  public Mono<VideoEstatisticasModel> buscarEstatisticas(VideoRepository databaseClient) {

    IVideoGateway videoGateway = new VideoGateway(databaseClient);

    return VideoPresenter.toRecordEstatistica(videoGateway.listarEstatisticas());

  }


}
