package ma.formation.irisi.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.formation.irisi.zynerator.repository.AbstractRepository;
import ma.formation.irisi.bean.core.commun.CategorieProduit;
import org.springframework.stereotype.Repository;
import ma.formation.irisi.bean.core.commun.CategorieProduit;
import java.util.List;


@Repository
public interface CategorieProduitDao extends AbstractRepository<CategorieProduit,Long>  {
    CategorieProduit findByReference(String reference);
    int deleteByReference(String reference);


    @Query("SELECT NEW CategorieProduit(item.id,item.reference) FROM CategorieProduit item")
    List<CategorieProduit> findAllOptimized();

}
