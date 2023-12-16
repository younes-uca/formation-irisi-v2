package ma.formation.irisi.dao.facade.core.commun;

import ma.formation.irisi.zynerator.repository.AbstractRepository;
import ma.formation.irisi.bean.core.commun.CategorieProduit;
import org.springframework.stereotype.Repository;
import ma.formation.irisi.bean.core.commun.CategorieProduit;

import java.util.List;


@Repository
public interface CategorieProduitDao extends AbstractRepository<CategorieProduit, Long> {
    CategorieProduit findByReference(String reference);

    int deleteByReference(String reference);


}
