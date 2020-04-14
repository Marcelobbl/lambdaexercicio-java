package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Funcionario;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o caminho do arquivo: ");
		String caminho = sc.next();
		System.out.println("Entre com o salario: ");
		Double valor = sc.nextDouble();
		
		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			
			List<Funcionario> list= new ArrayList<>();
			
			String linha = br.readLine();
			while (linha != null) {
				String[] campos = linha.split(",");
				list.add(new Funcionario(campos[0], campos[1], Double.parseDouble(campos[2])));
				linha = br.readLine();
			}
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			List<String> email = list.stream().filter(p -> p.getSalario() > valor).map(p -> p.getEmail())
					.sorted(comp).collect(Collectors.toList());
			
			double salario = list.stream().filter(p -> p.getNome().charAt(0) == 'M').map(p -> p.getSalario()).reduce(0.0, (x,y) -> x + y);
						
			System.out.println("Email dos funcionarios que ganham mais: " + String.format("%.2f", valor));
			email.forEach(System.out::println);
			System.out.println("Soma do salario dos funcionarios com a letra 'M': " + String.format("%.2f", salario));
		}
		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
			
				

	}

}
