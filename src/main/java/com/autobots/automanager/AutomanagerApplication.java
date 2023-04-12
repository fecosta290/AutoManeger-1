package com.autobots.automanager;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@SpringBootApplication
public class AutomanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomanagerApplication.class, args);
	}

	@Component
	public static class Runner implements ApplicationRunner {
		@Autowired
		public ClienteRepositorio repositorio;

		@Override
		public void run(ApplicationArguments args) throws Exception {
			Calendar calendario = Calendar.getInstance();
			calendario.set(2002, 05, 15);

			Cliente cliente = new Cliente();
			cliente.setNome("Andre Felipe da Costa");
			cliente.setDataCadastro(Calendar.getInstance().getTime());
			cliente.setDataNascimento(calendario.getTime());
			cliente.setNomeSocial("André");
			
			Telefone telefone = new Telefone();
			telefone.setDdd("12");
			telefone.setNumero("981070102");
			cliente.getTelefones().add(telefone);
			
			Endereco endereco = new Endereco();
			endereco.setEstado("São Paulo");
			endereco.setCidade("São José dos Campos");
			endereco.setBairro("Eugenio deMelo");
			endereco.setRua("Rua Dona Elizza Joanna Sattelmayer");
			endereco.setNumero("92");
			endereco.setCodigoPostal("12247090");
			endereco.setInformacoesAdicionais("");
			cliente.setEndereco(endereco);
			
			Documento rg = new Documento();
			rg.setTipo("RG");
			rg.setNumero("1500");
			
			Documento cpf = new Documento();
			cpf.setTipo("RG");
			cpf.setNumero("55842791889");
			
			cliente.getDocumentos().add(rg);
			cliente.getDocumentos().add(cpf);
			
			repositorio.save(cliente);
		}
	}

}