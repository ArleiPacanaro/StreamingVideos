package com.challenge.videos.gateways;

import com.challenge.videos.entities.VideoEntity;
import com.challenge.videos.enumeration.VideosCategorias;
import com.challenge.videos.external.model.VideoEstatisticasModel;
import com.challenge.videos.external.model.VideoModel;
import com.challenge.videos.external.repository.VideoRepository;
import com.challenge.videos.interfaces.IVideoGateway;
import java.time.LocalDate;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Gateway do padrão da arquitetura limpa.
 */


public class VideoGateway implements IVideoGateway {

  private final VideoRepository databaseClient;

  public VideoGateway(VideoRepository databaseClient) {

    this.databaseClient = databaseClient;
  }

  @Override
  public Mono<VideoModel> registrarVideo(VideoEntity videoEntity) {
    VideoModel videoModel = new VideoModel(
                videoEntity.getId(),
                videoEntity.getTitulo(),
                videoEntity.getDescricao(),
                videoEntity.getUrlVideo(),
                videoEntity.getDataPublicacao(),
                videoEntity.getCategoria(),
                videoEntity.getFavorito(),
                videoEntity.getVisualizacoes()

        );

    return this.databaseClient.save(videoModel);
  }


  @Override
  public Mono<VideoModel> buscarVideoPorId(Integer id) {
    return this.databaseClient.findById(id);
  }

  @Override
  public Mono<Void> deletarVideoPorId(Integer id) {

    return this.databaseClient.deleteById(id);
  }

  @Override
  public Flux<VideoModel> listarVideos(Pageable pageable) {
    return this.databaseClient.findAllBy(pageable);
  }

  @Override
  public Flux<VideoModel> listarVideosPorTitulo(String titulo) {
    return this.databaseClient.findByTituloContainingIgnoreCase(titulo);
  }

  @Override
  public Flux<VideoModel> listarVideosPorDataPublicacao(LocalDate dataPublicacao) {
    return this.databaseClient.findByDataPublicacao(dataPublicacao);
  }

  @Override
  public Flux<VideoModel> listarVideosPorCategoria(VideosCategorias categorias) {
    return this.databaseClient.findByCategoria(categorias);
  }

  @Override
  public Flux<VideoModel> listarVideosRecomendados(VideosCategorias categoria,
                                                   Integer qtdFavoritados) {
    return this.databaseClient.ListarVideosRecomendados(categoria, qtdFavoritados);
  }

  @Override
  public Mono<VideoEstatisticasModel> listarEstatisticas() {
    return  this.databaseClient.listarEstatisticas();
  }

}
