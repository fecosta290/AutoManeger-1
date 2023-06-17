package com.autobots.automanager.componentes;
import org.springframework.stereotype.Component;
import java.util.List;
import com.autobots.automanager.entitades.Servico;



@Component
public class ServicoSelecionador implements Selecionador<Servico, Long> {
	  
		@Override
		public Servico selecionar(List<Servico> servicos, Long id) {
			Servico selecionado = null;
			for(Servico selecionador: servicos) {
				if(selecionador.getId().longValue() == id.longValue()) {
					selecionado = selecionador;
					break;
				}
			}
			return selecionado;
		}
}
