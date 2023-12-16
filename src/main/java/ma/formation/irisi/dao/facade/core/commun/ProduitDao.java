package ma.formation.irisi.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.formation.irisi.zynerator.repository.AbstractRepository;
import ma.formation.irisi.bean.core.commun.Produit;
import org.springframework.stereotype.Repository;
import ma.formation.irisi.bean.core.commun.Produit;
import java.util.List;


@Repository
public interface ProduitDao extends AbstractRepository<Produit,Long>  {
    Produit findByReference(String reference);
    int deleteByReference(String reference);

    List<Produit> findByCategorieProduitId(Long id);
    int deleteByCategorieProduitId(Long id);
    long countByCategorieProduitReference(String reference);

    @Query("SELECT NEW Produit(item.id,item.reference) FROM Produit item")
    List<Produit> findAllOptimized();

}
