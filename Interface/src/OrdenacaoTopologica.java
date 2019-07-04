import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OrdenacaoTopologica {
	private class Elo {
		/* Identificação do elemento. */
		public int chave;

		/* Número de predecessores. */
		public int contador;

		/* Aponta para o próximo elo da lista. */
		public Elo prox;

		/* Aponta para o primeiro elemento da lista de sucessores. */
		public EloSuc listaSuc;

		public Elo(int chave) {
			prox = null;
			contador = 0;
			listaSuc = null;
			this.chave = chave;
		}

		public Elo(int chave, int contador, Elo prox, EloSuc listaSuc) {
			this.chave = chave;
			this.contador = contador;
			this.prox = prox;
			this.listaSuc = listaSuc;
		}

	}

	private class EloSuc {
		/* Aponta para o elo que é sucessor. */
		public Elo id;

		/* Aponta para o próximo elemento. */
		public EloSuc prox;

		public EloSuc() {
			id = null;
			prox = null;
		}

		public EloSuc(Elo id, EloSuc prox) {
			this.id = id;
			this.prox = prox;
		}
	}

	/* Ponteiro (referência) para primeiro elemento da lista. */
	private Elo prim;

	/* Número de elementos na lista. */
	private int n;

	public OrdenacaoTopologica() {
		prim = null;
		n = 0;
	}

	/* Método responsável pela leitura do arquivo de entrada. */
	public void realizaLeitura(String nomeEntrada) throws IOException {
		BufferedReader lerArq = null;

		try {
			// Pega o arquivo com nome que foi dado como parametro
			FileReader entrada = new FileReader(nomeEntrada);
			lerArq = new BufferedReader(entrada);
			String linha = null;

			try {
				// Roda o arquivo até não ter nada pra ler
				while (lerArq.ready()) {
					// Lê próxima linha
					linha = lerArq.readLine();

					// Pega o index do simbolo '<' e criar duas subtrings com os numeros antes e
					// depois do simbolo
					int simbolo = linha.indexOf("<");
					String num1 = linha.substring(0, simbolo).trim();
					String num2 = linha.substring(simbolo + 1, linha.length()).trim();

					Elo x = null;
					Elo y = null;
					Elo novo = null;

					// Pega o primeiro elemento da String, o primeiro numero, verifica se ele ja
					// existe na lista,
					// se não existir cria ele, se ja existir, ele só pega a referencia do elo
					if (this.busca(Integer.parseInt(num1)) == false) {
						x = new Elo(Integer.parseInt(num1));
						n++;
						novo = x;

					} else {
						x = this.buscaReferencia(Integer.parseInt(num1));
					}

					// Atribui o valor de x para o prim, se for o primeiro elemento
					if (prim == null) {
						prim = x;
					}

					// Pega o ultimo elemento da String, o segundo numero, verifica se ele ja existe
					// na lista,
					// se não existir cria ele, se ja existir, ele só pega a referencia do elo
					if (this.busca(Integer.parseInt(num2)) == false) {
						y = new Elo(Integer.parseInt(num2));
						n++;
						novo = y;

					} else {
						y = this.buscaReferencia(Integer.parseInt(num2));
					}

					// Aponta o prox do ultimo elemento da lista para o novo elemento que foi
					// inserido
					Elo p = null;
					Elo ult = null;

					for (p = prim; p != null; p = p.prox) {
						ult = p;
					}

					ult.prox = novo;

					// Cria um sucessor na Lista de sucessores do x, com id apontando para y
					EloSuc sucessor = new EloSuc(y, x.listaSuc);
					x.listaSuc = sucessor;

					// Atualizar o contador de predecessores de y
					y.contador += 1;
				}

				lerArq.close();

			} catch (IOException ex) {
				System.out.println("Sem conteudo");
			}

		} catch (FileNotFoundException ex) {
			System.out.println("Arquivo não encontrado");
		}
	}

	// Verifica se um determinado elemento está na lista
	public boolean busca(int elem) {

		Elo p;

		for (p = prim; p != null; p = p.prox) {

			if (p.chave == elem) {
				return true;
			}
		}

		return false;
	}

	// Busca o elo com o elemento dado e retorna esse Elo encontrado
	public Elo buscaReferencia(int elem) {
		Elo p;

		for (p = prim; p != null; p = p.prox) {

			if (p.chave == elem) {
				return p;
			}
		}

		return null;

	}

	/* Método para impressão do estado atual da estrutura de dados. */
	private void debug() {
		Elo p = null;
		EloSuc x = null;

		System.out.println("Debug");
		for (p = prim; p != null; p = p.prox) {
			System.out.print("Chave: " + p.chave + ", predecessores: " + p.contador + ", sucessores: ");
			for (x = p.listaSuc; x != null; x = x.prox) {
				System.out.print(x.id.chave + " -> ");
			}
			if (x == null) {
				System.out.println("null");
			}
			System.out.println("");
		}

	}


	/* Método responsável por executar o algoritmo. */
	public boolean executa() {
		// Comando para chamar o método debug
		this.debug();

		System.out.println("Ordenação topologica");

		// Inicio de uma nova lista que vai incluir elementos com 0 predecessores
		OrdenacaoTopologica ordemElementos = new OrdenacaoTopologica();

		// Inicialização das variáveis, em que p é p prim da nossa lista de adjacencia
		Elo p = this.prim;
		Elo q = null;

		ordemElementos.prim = null;

		// Percorre a lista, verificando os elementos que não têm predecessores e coloca
		// eles no início da lista ordemElementos
		while (p != null) {
			q = p;
			p = q.prox;
			if (q.contador == 0) {
				q.prox = ordemElementos.prim;
				ordemElementos.prim = q;
			}

		}

		// Inicialização das variaveis, em que t será o sucessor de q, que é o elemento
		// de ordemElementos
		EloSuc t = null;
		Elo ult = null;
		int contOrdemElementos = 0;
		q = ordemElementos.prim;

		// Percorre ordemElementos a partir do primeiro elemento da lista
		// ordemElementos, imprime esse elemento,
		// e percorre a lista de sucessores desse elemento
		while (q != null) {
			System.out.print(q.chave + " ");
			n--;
			t = q.listaSuc;

			EloSuc ant = null;

			// Contador do numero de elementos que foram adicionados em ordemElementos
			contOrdemElementos = 0;

			// Percorre a lista enquanto q possuir sucessores
			while (t != null) {

				// Decremeneta o contador de predecessores de t
				t.id.contador--;

				// Verifica se o elemento ficou com contador de predecessores igual a 0
				if (t.id.contador == 0) {

					// Verifica quem é o elo anterior na lista de sucessores, se for o primeiro elo,
					// entao ant sera igual a null
					if (ant == null) {

						// Retira o elemento t na lista de sucessores fazendo o proximo ser o primeiro
						// elo da lista de sucessores
						q.listaSuc = q.listaSuc.prox;

					} else {
						// Retira o elemento t da lista de sucessores fazendo o elo ant apontar para o
						// proximo
						q.listaSuc = ant.prox;
					}

					// Se o elemento não possui predecessores, ele é colocado no início de
					// ordemElementos
					t.id.prox = ordemElementos.prim;
					ordemElementos.prim = t.id;

					// O elo t é atualizado
					t = q.listaSuc;

					// Foi inserido um novo elo no ordemElementos, entao o contador é incrementado
					contOrdemElementos++;
				}

				else {
					ant = t;
					t = t.prox;
				}
			}

			// Verifica se foram adicionados novos elementos em ordemElementos, atraves do
			// conOrdemElementos
			// Se ele continuar igual a 0, entao o prim passa a apontar para o próximo
			// elemento da lista
			// E o elo q é excluído da lista OrdemElementos
			// E o elo q é atualizado pro atual prim da lista
			if (contOrdemElementos == 0) {
				ordemElementos.prim = ordemElementos.prim.prox;
				q = ordemElementos.prim;
			}
			// Se foi adicionado novos elementos, percorremos o ordemElementos buscando o
			// ultimo elemento antes do q
			// e fazendo esse apontar pra o proximo do q
			// Assim, excluimos o elo q da lista ordemElementos
			// E atualizamos o elo q com o atual prim
			else {
				for (Elo aux = ordemElementos.prim; aux != q; aux = aux.prox) {
					ult = aux;
				}

				ult.prox = q.prox;
				q = ordemElementos.prim;
			}

		}
		System.out.println("");

		// Verifica se o conjunto é parcialmente ordenado ou não, vendo se o contador de
		// elementos n
		// de elementos de nossa lista de adjacencia, chegou a 0, se sim é um conjunto
		// parcialamente ordenado,
		// se nao, nao é um conjunto parcialmente ordenado
		return (n == 0) ? true : false;
	}



}
