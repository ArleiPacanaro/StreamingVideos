package com.challenge.videos.entities;

import com.challenge.videos.enumeration.VideosCategorias;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


/**
 * Componente Entity Video.
 */

@Setter
@Getter
public class VideoEntity {


  private Integer id;
  private String titulo;
  private String descricao;
  private String urlVideo;
  private LocalDate dataPublicacao;
  private VideosCategorias categoria;
  private Integer favorito;
  private Integer visualizacoes;

  /**
   * Seguindo as convenções da Clean Architecture devemos validar os dados da entity nela mesmo.
   */

  public VideoEntity(Integer id, String titulo, String descricao,
                     String urlVideo, LocalDate dataPublicacao, VideosCategorias categoria,
                     Integer favorito, Integer visualizacoes)
            throws IllegalAccessException {

    if (id == null || id <= 0) {
      throw new IllegalAccessException("Campo Id deve ser maior que zero");
    }
    if (titulo == null || titulo.isBlank()) {
      throw new IllegalAccessException("Campo titulo não preenchido");
    }
    if (descricao == null || descricao.isBlank()) {
      throw new IllegalAccessException("Campo titulo não preenchido");
    }
    if (urlVideo == null || urlVideo.isBlank()) {
      throw new IllegalAccessException("Campo url não preenchido");
    }
    if (dataPublicacao == null) {
      throw new IllegalAccessException("Campo data de Publicação não preenchido");
    }
    if (categoria == null) {
      throw new IllegalAccessException("Campo categoria não preenchido");
    }
    if (favorito == null) {
      this.favorito = 0;
    }
    if (visualizacoes == null) {
      this.visualizacoes = 0;
    }

    this.id = id;
    this.titulo = titulo;
    this.descricao = descricao;
    this.urlVideo = urlVideo;
    this.dataPublicacao = dataPublicacao;
    this.categoria = categoria;
    this.favorito = favorito;
    this.visualizacoes = visualizacoes;

  }
}
