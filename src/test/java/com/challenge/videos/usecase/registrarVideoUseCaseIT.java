package com.challenge.videos.usecase;

import com.challenge.videos.entities.VideoEntity;
import com.challenge.videos.enumeration.VideosCategorias;
import com.challenge.videos.records.VideoRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class registrarVideoUseCaseIT {

    @Test
    void deveRegistrarVideo() throws IllegalAccessException {

        // Buscar do banco de dados

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

        VideoEntity videoEntity = registrarVideoUseCase.init(videoRecord);

        assertThat(videoRecord.id()).isEqualTo(videoEntity.getId());


    }
}
