package io.altar.jseproject.textinterface;

import io.altar.jseproject.repositories.EntityRepository;
import io.altar.jseproject.repositories.ProductRepository;
import io.altar.jseproject.repositories.ShelfRepository;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.altar.jseproject.model.Entity;
import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.utils.ScannerUtils;

public class TextInterface {

	static ScannerUtils sc = new ScannerUtils();
	static ProductRepository pr = ProductRepository.getInstance();
	static ShelfRepository sr = ShelfRepository.getInstance();

	public static void main(String[] args) {

		showEntryScreen();
	}
//hello
	static void showEntryScreen() {

		int option = 0;
		String msg = "Por favor selecione uma das seguintes opções:\n" + "  1)  Listar produtos\n"
				+ "  2)  Listar prateleiras\n" + "  3)  Sair";
		do {

			option = sc.getInt(msg, 1, 3);
			switch (option) {
			case 1:
				Collection<Product> products = pr.consultEntity();
				if (products.size() > 0) {
					System.out.println(products);
				}
				showProductsScreen();
				break;
			case 2:
				Collection<Shelf> shelfs = sr.consultEntity();
				if (shelfs.size() > 0) {
					System.out.println(shelfs);
				}
				showShelfsScreen();
				break;
			case 3:
				System.out.println("Saída");
				break;
			}
		} while (option != 3);

	}

	static void showProductsScreen() {
		int option = 0;
		String msg = "Por favor selecione uma das seguintes opções:\n" + "  1)  Criar novo produto\n"
				+ "  2)  Editar um produto existente\n" + "  3)  Consultar o detalhe de um produto\n"
				+ "  4)  Remover um produto\n" + "  5)  Voltar ao ecrã anterior";
		do {
			option = sc.getInt(msg, 1, 5);
			switch (option) {
			case 1:
				createProduct();
				break;
			case 2:
				editProductScreen(sc.getInt("Introduza o id do produto a editar: "));
				break;
			case 3:
				consultProductById(sc.getInt("Introduza o id da prateleira a consultar: "));
				break;
			case 4:
				removeProduct(sc.getInt("Introduza o id do produto a remover: "));
				break;
			case 5:
				System.out.println("Voltar");
				break;
			}
		} while (option != 5);
	}

	static void showShelfsScreen() {
		int option = 0;
		String msg = "Por favor selecione uma das seguintes opções:\n" + "  1)  Criar nova prateleira\n"
				+ "  2)  Editar uma prateleira existente\n" + "  3)  Consultar o detalhe de uma prateleira\n"
				+ "  4)  Remover uma prateleira\n" + "  5)  Voltar ao ecrã anterior";
		do {
			option = sc.getInt(msg, 1, 5);

			switch (option) {
			case 1:
				createShelf();
				break;
			case 2:
				editShelfScreen(sc.getInt("Introduza o id da prateleira a editar: "));
				break;
			case 3:
				consultShelfById(sc.getInt("Introduza o id da prateleira a consultar: "));
				break;
			case 4:
				removeShelf(sc.getInt("Introduza o id da prateleira a remover: "));
				break;
			case 5:
				System.out.println("Voltar");
				return;
			}
		} while (option != 5);
	}
	
	static void editProductScreen(long productId) {

		int option = 0;
		String msg = "Por favor selecione um dos atributos do produto "+productId+" para editar:\n" 
					+ "  1)  Adicionar o produto a uma prateleira\n"
					+ "  2)  Remover produto de uma prateleira\n" 
					+ "  3)  Alterar o preço base\n"
					+ "  4)  Alterar o iva\n" 
					+ "  5)  Voltar ao ecrã anterior";
		do {
			option = sc.getInt(msg, 1, 5);

			switch (option) {
			case 1:
				addReplaceProductFromShelf(sc.getInt("Introduza o id da prateleira: "),productId);
				break;
			case 2:
				removeProductFromShelf(sc.getInt("Introduza o id da prateleira: "),productId);
				break;
			case 3:
				editProductBasePrice(productId,sc.getInt("Introduza o novo valor do preço base do produto: "));
				break;
			case 4:
				editProductIva(productId, sc.getInt("Introduza o novo valor do preço base do produto: "));
				break;
			case 5:
				System.out.println("Voltar");
				return;
			}
		} while (option != 5);
	}
	
	static void editShelfScreen(long shelfId) {
		int option = 0;
		String msg = "Por favor selecione um dos atributos da prateleira "+shelfId+" para editar:\n" 
					+ "  1)  Adicionar ou substituir produto colocado\n"
					+ "  2)  Remover produto\n" 
					+ "  3)  Alterar a capacidade \n"
					+ "  4)  Alterar o preço diário de aluguer\n" 
					+ "  5)  Voltar ao ecrã anterior";
		do {
			option = sc.getInt(msg, 1, 5);

			switch (option) {
			case 1:
				addReplaceProductFromShelf(shelfId, sc.getInt("Introduza o Id do produto a colocar na prateleira: "));
				break;
			case 2:
				Shelf shelf = sr.consultEntity(shelfId);
				Product product = shelf.getProduct();
				removeProductFromShelf(shelfId, product.getId());
				break;
			case 3:
				editShelfCapacity(shelfId, sc.getInt("Introduza o novo valor da capacidade da prateleira: "));
				break;
			case 4:
				editShelfDailyRentalprice(shelfId, sc.getInt("Introduza o novo valor da capacidade da prateleira: "));
				break;
			case 5:
				System.out.println("Voltar");
				return;
			}
		} while (option != 5);
	}

	static void createProduct() {
		Product newProduct = new Product(sc.getInt("Introduza o preço base do produto: "),
				sc.getInt("Introduza o iva atual a ser considerado: "));
		pr.createEntity(newProduct);
	}

	static void createShelf() {
		Shelf newShelf = new Shelf(sc.getInt("Introduza a capacidade da prateleira: "),
				sc.getInt("Introduza o preço diàrio de aluguer: "));
		sr.createEntity(newShelf);
	}

	private static void editProductIva(long productId, int novoIva) {
		Product product = pr.consultEntity(productId);
		product.setIva(novoIva);
		pr.editEntity(product);	
	}

	private static void editProductBasePrice(long productId, int novoBasePrice) {
		Product product = pr.consultEntity(productId);
		product.setBasePrice(novoBasePrice);
		pr.editEntity(product);
	}
	
	private static void editShelfDailyRentalprice(long shelfId, int novoDailyRentalPrice) {
		Shelf shelf = sr.consultEntity(shelfId);
		shelf.setDailyRentalPrice(novoDailyRentalPrice);
		sr.editEntity(shelf);
	}

	private static void editShelfCapacity(long shelfId, int novoCapacity) {
		Shelf shelf = sr.consultEntity(shelfId);
		shelf.setCapacity(novoCapacity);
		sr.editEntity(shelf);
		
	}

	private static void removeProductFromShelf(long shelfId, long productId) {
		Product product = pr.consultEntity(productId);
		List<Long> shelfList = product.getInShelfList();
		if(product.getInShelfList().size() >0 && shelfList.contains(shelfId)) {
			shelfList.remove(shelfList.indexOf(shelfId));
			product.setInShelfList(shelfList);
			pr.editEntity(product);
			Shelf shelf = sr.consultEntity(shelfId);
			shelf.setProduct(null);
			sr.editEntity(shelf);
		}else if(product.getInShelfList().size() == 0){
			System.out.println("Este produto não se encontra em nenhuma prateleira.");
		}else {
			System.out.println("Este produto não se encontra na prateleira.");
		}
	}

	private static void addReplaceProductFromShelf(long shelfId, long productId) {
		Shelf shelf = sr.consultEntity(shelfId);
		Product productAdicionar = pr.consultEntity(productId);
		if (shelf.getProduct()!= null) {
			Product product = shelf.getProduct();
			removeProductFromShelf(shelfId, product.getId());
		}
		List<Long> shelfList = productAdicionar.getInShelfList();
		shelfList.add(shelfId);
		productAdicionar.setInShelfList(shelfList);
		
		shelf.setProduct(productAdicionar);
		sr.editEntity(shelf);
		pr.editEntity(productAdicionar);
		
	}

	static void consultProductById(long productId) {
		Product product = pr.consultEntity(productId);
		if(product != null) {
		System.out.println(product);
		}else {
			System.out.println("Esse produto não existe");
		}
	}

	static void consultShelfById(long shelfId) {
		Shelf shelf = sr.consultEntity(shelfId);
		if(shelf != null) {
			System.out.println(shelf);
		}else {
			System.out.println("Essa prateleira não existe");
		}
		
	}

	static void removeProduct(long productId) {
		pr.removeEntity(productId);
		System.out.println("Produto com o Id '" + productId + "' foi removido");
	}

	static void removeShelf(long shelfId) {
		sr.removeEntity(shelfId);
		System.out.println("Prateleira com o Id '" + shelfId + "' foi removida");
	}

}
