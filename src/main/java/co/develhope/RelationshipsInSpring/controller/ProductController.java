package co.develhope.RelationshipsInSpring.controller;

import co.develhope.RelationshipsInSpring.entities.Product;
import co.develhope.RelationshipsInSpring.entities.Review;
import co.develhope.RelationshipsInSpring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    /*
    Vogliamo almeno i metodi CRUD:
    se possiamo creare, leggere (read), aggiornare (update), cancellare (delete) allora possiamo fare tutto
     */

    @PostMapping("/insert-product")
    public Product insertProduct(@RequestBody Product product) {
        Product insertedProduct = productService.createNewProduct(product);
        return insertedProduct;
    }

    @GetMapping("/get-all-products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/get-product-by-id")
    public ResponseEntity getProduct(@RequestParam int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /*
    Il body della richiesta conterrà una recensione priva del campo product
    C'è invece un parametro che ci informa su qual è l'id del prodotto
     */
    @PostMapping("/insert-review")
    public void insertReview(@RequestBody Review review, @RequestParam int productId) throws Exception {
        // cerco il prodotto che mi interessa
        //      esiste la possibilità che non lo trovo (mi hanno mandato un id sbagliato)
        //      -> lancio un eccezione
        // lo inserisco dentro la recensione
        // solo a questo punto salvo la recensione
        productService.addReviewToProduct(review, productId);

    }



}
