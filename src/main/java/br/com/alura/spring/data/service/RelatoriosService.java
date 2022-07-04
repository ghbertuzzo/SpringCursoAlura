package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;


@Service
public class RelatoriosService {

	private Boolean system = true;
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual acao deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Buscar FUNCIONARIO por nome:");
			System.out.println("2 - Buscar FUNCIONARIO por nome, data contratacao e salario maior:");
			System.out.println("3 - Buscar FUNCIONARIO por data de contratacao:");
			System.out.println("4 - Buscar FUNCIONARIO por salario:");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			case 4:
				pesquisaFuncionarioSalario();
				break;
			default:
				system = false;
				break;
			}
			
		}
		
	}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar?");
		String nome = scanner.nextLine();
		nome = scanner.nextLine();
		List<Funcionario> list = funcionarioRepository.findByNomeLike("%"+nome+"%");
		System.out.print("Encontrado "+list.size()+" resultados.");
		list.forEach(funcionario -> System.out.print(funcionario.toString()));
		
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar?");
		String nome = scanner.nextLine();
		nome = scanner.nextLine();
		
		System.out.println("Digite a data de contratacao, Dia:");
        Integer diaDataContratacao = scanner.nextInt();
        System.out.println("Digite a data de contratacao, Mes:");
        Integer mesDataContratacao = scanner.nextInt();
        System.out.println("Digite a data de contratacao, Ano:");
        Integer anoDataContratacao = scanner.nextInt();
        LocalDate data = LocalDate.of(anoDataContratacao,mesDataContratacao,diaDataContratacao);
                
        System.out.println("Qual valor do salario?");
		Double salario = scanner.nextDouble();
		java.util.List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, data);
		System.out.print("Encontrado "+list.size()+" resultados.");
		list.forEach(funcionario -> System.out.print(funcionario.toString()));
		
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Digite a data de contratacao, Dia:");
        Integer diaDataContratacao = scanner.nextInt();
        System.out.println("Digite a data de contratacao, Mes:");
        Integer mesDataContratacao = scanner.nextInt();
        System.out.println("Digite a data de contratacao, Ano:");
        Integer anoDataContratacao = scanner.nextInt();
        LocalDate data = LocalDate.of(anoDataContratacao,mesDataContratacao,diaDataContratacao);
        List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(data);
        System.out.print("Encontrado "+list.size()+" resultados.");
        list.forEach(funcionario -> System.out.print(funcionario.toString()));
	}
	
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		System.out.print("Encontrado "+list.size()+" resultados.");
        list.forEach(funcionario -> System.out.print("\n\nFuncionario ID: " + funcionario.getId() + 
        		"\nFuncionario Nome: " + funcionario.getNome() +
        		"\nFuncionario Salario: R$ " + funcionario.getSalario()));
	}
}
