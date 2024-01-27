package com.challenge.videos.presenters;

import com.challenge.videos.external.model.VideoEstatisticasModel;
import com.challenge.videos.external.model.VideoModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Adapter\Port para saida.
 */


public class VideoPresenter {

  private VideoPresenter() {
  }


  /**
   * Metodo para Adapter\Port de saida Mono.
   */
  public static Mono<VideoModel> toRecord(Mono<VideoModel> videoEntityMono) {

    return videoEntityMono;

  }

  /**
   * Metodo para Adapter\Port de saida Mono.
   */
  public static Mono<VideoEstatisticasModel> toRecordEstatistica(
      Mono<VideoEstatisticasModel> videoEntityMono) {

    return videoEntityMono;

  }

  public static Flux<VideoModel> toList(Flux<VideoModel> videoModelFlux) {

    return videoModelFlux;
  }

}
