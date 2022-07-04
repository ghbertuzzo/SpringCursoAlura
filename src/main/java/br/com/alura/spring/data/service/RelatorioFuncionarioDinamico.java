package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial (Scanner scanner) {
		System.out.println("Digite o nome: ");
		String nome = scanner.nextLine();
		nome = scanner.nextLine();
		
		if(nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}

		System.out.println("Digite o CPF: ");
		String cpf = scanner.nextLine();
		
		if(cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.println("Digite o salario: ");
		Double salario = scanner.nextDouble();
		
		if(salario==0) {
			salario = null;
		}		

		System.out.println("Digite a data de contratacao, Dia:");
        Integer diaDataContratacao = scanner.nextInt();
        System.out.println("Digite a data de contratacao, Mes:");
        Integer mesDataContratacao = scanner.nextInt();
        System.out.println("Digite a data de contratacao, Ano:");
        Integer anoDataContratacao = scanner.nextInt();
        LocalDate data;
        
        if(diaDataContratacao==0 || mesDataContratacao==0 || anoDataContratacao==0) {
        	data = null;
        } else {
            data = LocalDate.of(anoDataContratacao,mesDataContratacao,diaDataContratacao);        	
        }
		
		List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification.where(
				SpecificationFuncionario.nome(nome))
				.or(SpecificationFuncionario.cpf(cpf)
						.or(SpecificationFuncionario.salario(salario)
								.or(SpecificationFuncionario.dataContratacao(data))))
				);
		
		System.out.print("Encontrado "+funcionarios.size()+" resultados.");
		funcionarios.forEach(System.out::println);
	}

}
