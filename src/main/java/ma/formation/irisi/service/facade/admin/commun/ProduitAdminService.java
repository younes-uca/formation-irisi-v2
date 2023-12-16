package ma.formation.irisi.service.facade.admin.commun;

import java.util.List;
import ma.formation.irisi.bean.core.commun.Produit;
import ma.formation.irisi.dao.criteria.core.commun.ProduitCriteria;
import ma.formation.irisi.zynerator.service.IService;



public interface ProduitAdminService extends  IService<Produit,ProduitCriteria>  {

    List<Produit> findByCategorieProduitId(Long id);
    int deleteByCategorieProduitId(Long id);
    long countByCategorieProduitReference(String reference);



}
