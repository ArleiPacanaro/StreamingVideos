package com.challenge.videos.usecase;

import com.challenge.videos.entities.VideoEntity;
import com.challenge.videos.records.VideoRecord;


/**
 * Camada de UseCase da arquitetura Limpa que faz as ações sobre as Entities,
 * ideal separar bem para fazer apenças uma ação.
 */

public class registrarVideoUseCase {

  private registrarVideoUseCase() {
  }

  /**
   * Metodo criar a Entidade e poderia ter mais alguma ação caso fosse necessário pelo negócio.
   */
  public static VideoEntity init(VideoRecord videoRecord) throws IllegalAccessException {

    VideoEntity videoEntity = new VideoEntity(
                videoRecord.id(),
                videoRecord.titulo(),
                videoRecord.descricao(),
                videoRecord.urlVideo(),
                videoRecord.dataPublicacao(),
                videoRecord.categoria(),
                videoRecord.favorito(),
                videoRecord.visualizacoes()
        );

    return videoEntity;

  }


}
