package com.challenge.videos.external.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe par Mapper das Estatisticas Videos.
 */

@AllArgsConstructor
@Getter

public class VideoEstatisticasModel {

  private long qtdTotalVideosFavoritos;
  private float avgTotalVideosAssistidos;

}
