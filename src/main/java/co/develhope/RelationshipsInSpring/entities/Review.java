package co.develhope.RelationshipsInSpring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "reviews") // facoltativa, serve a specificare un nome diverso da quello della classe
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; // int occupa molto meno spazio del long, però è limitato
                    // uso int se non prevedo più di un paio di milioni di prodotti
    private String userName;
    private String content;

    @ManyToOne()
    @JsonIgnore
    private Product product;

    public Review() {
    }

    public Review(String userName, String content) {
        this.userName = userName;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
