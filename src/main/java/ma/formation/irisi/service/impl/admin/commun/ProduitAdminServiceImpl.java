package ma.formation.irisi.service.impl.admin.commun;


import ma.formation.irisi.bean.core.commun.Produit;
import ma.formation.irisi.dao.criteria.core.commun.ProduitCriteria;
import ma.formation.irisi.dao.facade.core.commun.ProduitDao;
import ma.formation.irisi.dao.specification.core.commun.ProduitSpecification;
import ma.formation.irisi.service.facade.admin.commun.ProduitAdminService;
import ma.formation.irisi.zynerator.service.AbstractServiceImpl;
import ma.formation.irisi.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;





import org.springframework.beans.factory.annotation.Autowired;

import ma.formation.irisi.service.facade.admin.commun.CategorieProduitAdminService ;
import ma.formation.irisi.bean.core.commun.CategorieProduit ;

import java.util.List;
@Service
public class ProduitAdminServiceImpl extends AbstractServiceImpl<Produit, ProduitCriteria, ProduitDao> implements ProduitAdminService {





    public Produit findByReferenceEntity(Produit t){
        return  dao.findByReference(t.getReference());
    }

    public List<Produit> findByCategorieProduitId(Long id){
        return dao.findByCategorieProduitId(id);
    }
    public int deleteByCategorieProduitId(Long id){
        return dao.deleteByCategorieProduitId(id);
    }
    public long countByCategorieProduitReference(String reference){
        return dao.countByCategorieProduitReference(reference);
    }

    public List<Produit> findAllOptimized() {
        return dao.findAllOptimized();
    }





    public void configure() {
        super.configure(Produit.class, ProduitSpecification.class);
    }


    @Autowired
    private CategorieProduitAdminService categorieProduitService ;

    public ProduitAdminServiceImpl(ProduitDao dao) {
        super(dao);
    }

}
