import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		boolean correto = true;
		String caracteres = "";
		do {
			try {
				Scanner scan = new Scanner(System.in);
				System.out.print("Digite a palavra a verficar (apenas ()[]{}<> são aceitos): ");
				caracteres = scan.next();
				correto = verificaString(caracteres);
			}
			catch (Exception e) {
				System.out.println(e.toString());
				correto = false;
			}
		} while (!correto);
		
		boolean valido = false;
		if (verificaPares(caracteres))
			valido = verificaParesIntercalados(caracteres);
		
		if (valido)
			System.out.println("Válido");
		else
			System.out.println("Inválido");
	}
	
	static boolean verificaString(String caracteres) throws Exception {
		boolean valido = true;
		List<String> caracteresValidos = new ArrayList<String>() {};
		caracteresValidos.add("<");
		caracteresValidos.add(">");
		caracteresValidos.add("(");
		caracteresValidos.add(")");
		caracteresValidos.add("{");
		caracteresValidos.add("}");
		caracteresValidos.add("[");
		caracteresValidos.add("]");
		
		for (int i = 0; i < caracteres.length(); i++) {
			if(!caracteresValidos.contains(String.valueOf(caracteres.charAt(i)))) {
				valido = false;
				throw new Exception("Palavra inválida");
			}
		}
		return valido;
	}
	
	static boolean verificaPares(String caracteres) {
		boolean valido = true;
		List<String> validacao = new ArrayList<String>();
		for (int i = 0; i < caracteres.length(); i++) {
			switch (String.valueOf(caracteres.charAt(i))) {
				case "<":
				case "{":
				case "(":
				case "[":
					validacao.add(String.valueOf(caracteres.charAt(i)));
					break;
				case ">":
					valido = validacao.remove("<");
					break;
				case "}":
					valido = validacao.remove("{");
					break;
				case ")":
					valido = validacao.remove("(");
					break;
				case "]":
					valido = validacao.remove("[");
					break;
			}
			if (!valido)
				return false;
		}
		if (!validacao.isEmpty())
			return false;
		return true;
	}
	
	static boolean verificaParesIntercalados(String caracteres){
		try {
			boolean valido = true;
			int ultimoIndex;
			List<String> validacao = new ArrayList<String>();
			for (int i = 0; i < caracteres.length(); i++) {
				String letra = String.valueOf(caracteres.charAt(i));
				switch (letra) {
					case "<":
					case "{":
					case "(":
					case "[":
						validacao.add(String.valueOf(caracteres.charAt(i)));
						break;
					case ">":
						ultimoIndex = validacao.lastIndexOf("<");
						for (int j = validacao.size() - 1; j >= ultimoIndex; j--) {
							validacao.remove(j);
						}
						break;
					case "}":
						ultimoIndex = validacao.lastIndexOf("{");
						for (int j = validacao.size() - 1; j >= ultimoIndex; j--) {
							validacao.remove(j);
						}
						break;
					case ")":
						ultimoIndex = validacao.lastIndexOf("(");
						for (int j = validacao.size() - 1; j >= ultimoIndex; j--) {
							validacao.remove(j);
						}
						break;
					case "]":
						ultimoIndex = validacao.lastIndexOf("[");
						for (int j = validacao.size() - 1; j >= ultimoIndex; j--) {
							validacao.remove(j);
						}
						break;
				}
			}
			if (!validacao.isEmpty())
				return false;
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
}
