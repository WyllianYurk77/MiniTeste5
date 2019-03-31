package Wyllian;

import java.util.ArrayList;
import java.util.List;

public class GrafoComListaDeAdjacencias implements Grafo {
	private List<Vertice> vertices = new ArrayList<>();
	private List<VerticeInterno> verticesInternos = new ArrayList<>();
	
	public GrafoComListaDeAdjacencias(String... nomesVertices) {
		for(String nomeVertice: nomesVertices) {
			this.addVertice(nomeVertice);
		}
	}

	@Override
	public Vertice getVertice(String nome) {
		for(Vertice vertice: vertices) {
			if(vertice.getNome().equals(nome)) {
				return vertice;
			}
		}
		return null;
	}

	@Override
	public Vertice addVertice(String nome) {
		Vertice vertice = new Vertice();
		vertice.setNome(nome);
		vertices.add(vertice);
		
		VerticeInterno verticeInterno = new VerticeInterno();
		verticeInterno.setVertice(vertice);
		verticesInternos.add(verticeInterno);
		
		return vertice;
	}

	@Override
	public void addAresta(Vertice v1, Vertice v2) {
		// TODO: código do método addAresta.
		Aresta ida = new Aresta();
		ida.setOrigem(v1);
		ida.setDestino(v2);
		VerticeInterno verticeInternoV1 = getVerticeInterno(v1);
		verticeInternoV1.getArestas().add(ida);
	}

	@Override
	public List<Vertice> getVertices() {
		return this.vertices;
	}

	@Override
	public boolean segue(Vertice v1, Vertice v2) {
		// TODO: código do método segue.
		VerticeInterno seguidor = getVerticeInterno(v1);
		for(Aresta seguido: seguidor.getArestas()) {
			if(seguido.getDestino().equals(v2)) {
				return true;
			}
		}
		return false;
	}
	
	private VerticeInterno getVerticeInterno(Vertice v) {
		for(VerticeInterno verticeInterno: verticesInternos) {
			if(verticeInterno.getVertice().equals(v)) {
				return verticeInterno;
			}
		}
		throw new RuntimeException("Vértice interno não encontrado.");
	}
}
class VerticeInterno {
	private Vertice vertice;
	private List<Aresta> arestas = new ArrayList<>();
	
	public Vertice getVertice() {
		return this.vertice;
	}
	
	public void setVertice(Vertice apex) {
		this.vertice = apex;
	}
	
	public List<Aresta> getArestas() {
		return this.arestas;
	}
}