package co.develhope.RelationshipsInSpring.service;

import co.develhope.RelationshipsInSpring.entities.Product;
import co.develhope.RelationshipsInSpring.entities.Review;
import co.develhope.RelationshipsInSpring.repositories.ProductRepository;
import co.develhope.RelationshipsInSpring.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // chiedo a spring di creare un oggetto di questa classe
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public Product createNewProduct(Product product) {
        // lo inserisce nel database
        System.out.printf("Sto per inserire questo prodotto: %s\n", product);
        Product newProduct = productRepository.save(product);
        System.out.printf("Prodotto inserito: %s\n", newProduct);
        return newProduct;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(int id) throws Exception {
        // può essere che non lo trovo
        /*
        Optional<Product> è un contenitore: può contenere un solo prodotto oppure essere vuoto
        -----------
        | product |  .isEmpty() ritorna true se la scatola è vuota
        -----------  .get() ritorna il contenuto della scatola (se è vuota da errore)
         */
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            // throw new Exception(String.format("Product with id {} not found", id));
            throw new Exception("Product with id " + id + " not found");
        }
        Product p = optionalProduct.get();
        return p;
    }

    public Review addReviewToProduct(Review review, int productId) throws Exception {

        // cerco il prodotto a cui voglio aggiungere la recensione
        Optional<Product> p = productRepository.findById(productId);

        // se non lo trovo lancio un eccezione e mi fermo
        if (p.isEmpty()) {
            throw new Exception("Product not found");
        }

        // se invece lo trovo
        Product product = p.get();

        // aggiungo il prodotto dentro la recensione
        review.setProduct(product);
        // e la salvo nel db
        review = reviewRepository.save(review);

        // aggiungo la recensione alla lista di recensioni del prodotto
        product.getReviews().add(review);
        // e lo salvo nel db (perché l'ho modificato)
        productRepository.save(product);

        return review;

    }


}
