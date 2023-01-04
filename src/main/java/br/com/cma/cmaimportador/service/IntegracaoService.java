package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AtivosEntity;
import br.com.cma.cmaimportador.domain.SerieHistorica;
import br.com.cma.cmaimportador.mapper.MapStructMapper;
import br.com.cma.cmaimportador.service.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class IntegracaoService {

   private final WebClient webClient;
   @Autowired
   private AtivosService ativosService;
   @Autowired
   private AcoesService acoesService;
   @Autowired
   private OpcoesService opcoesService;
   @Autowired
   private FuturosService futurosService;
   @Autowired
   private LoginService loginService;
   @Autowired
   private MapStructMapper mapStructMapper;
   public void executaIntegracao(String sessionId) {
      try {
         String timeRef = DataUtils.getHoraMinutoSegundo();

         if (sessionId == null || sessionId == "") {
            return;
         }

         List<AtivosEntity> listaAssets = ativosService.getAssets();
         List<AtivosEntity> listaAssetsAcoeOpceos = listaAssets.stream().filter( x -> x.getType().trim().equals("AC")).collect(Collectors.toList());

         log.info("INICIO INTEGRAÇÃO ACOES: " + LocalDateTime.now());
         listaAssetsAcoeOpceos.forEach(x -> {
            SerieHistorica baseSerieHistorica = mapStructMapper.baseSerieHistorica(new SerieHistorica(), x, timeRef);
            acoesService.executar(sessionId, x, baseSerieHistorica, "12");
         });
         log.info("FIM INTEGRAÇÃO ACOES: " +  LocalDateTime.now());

         log.info("INICIO INTEGRAÇÃO OPÇÕES: " + LocalDateTime.now());
         listaAssetsAcoeOpceos.forEach(x -> {
//            if(x.getAsset().equals("PETR4") || x.getAsset().equals("ABEV3")) {
               SerieHistorica baseSerieHistorica = mapStructMapper.baseSerieHistorica(new SerieHistorica(), x, timeRef);
               opcoesService.executar(sessionId, x, baseSerieHistorica);
//            }
         });
         log.info("FIM INTEGRAÇÃO OPÇÕES: " + LocalDateTime.now());

         List<AtivosEntity> listaAssetsFuturos = listaAssets.stream().filter( x -> x.getType().trim().equals("CF")).collect(Collectors.toList());
         log.info("INICIO INTEGRAÇÃO CONTRATOS FUTUROS: " + LocalDateTime.now());
         listaAssetsFuturos.forEach(x -> {
            SerieHistorica baseSerieHistorica = mapStructMapper.baseSerieHistorica(new SerieHistorica(), x, timeRef);
            futurosService.executar(sessionId, x, baseSerieHistorica);
         });
         log.info("FIM INTEGRAÇÃO CONTRATOS FUTUROS: " + LocalDateTime.now());

      } catch (Exception e) {
         loginService.executarLogout(sessionId);
         log.error(e.getMessage());
         log.info("ERRO INTEGRAÇÃO");
      }
   }
}
