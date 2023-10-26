package co.develhope.RelationshipsInSpring.repositories;

import co.develhope.RelationshipsInSpring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
- le repository serviranno per leggere, scrivere, modificare e cancellare oggetti dal db
- sono interfacce
- si annotano con @Repository
- estendono JpaRepository a cui devo specificare:
    - il tipo di oggetto con cui lavorerà
    - il tipo della sua chiave primaria (usando però la versione "classe": int -> Integer, long -> Long)
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
