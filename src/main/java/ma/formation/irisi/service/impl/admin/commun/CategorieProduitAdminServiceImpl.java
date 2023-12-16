package ma.formation.irisi.service.impl.admin.commun;


import ma.formation.irisi.bean.core.commun.CategorieProduit;
import ma.formation.irisi.dao.criteria.core.commun.CategorieProduitCriteria;
import ma.formation.irisi.dao.facade.core.commun.CategorieProduitDao;
import ma.formation.irisi.dao.specification.core.commun.CategorieProduitSpecification;
import ma.formation.irisi.service.facade.admin.commun.CategorieProduitAdminService;
import ma.formation.irisi.zynerator.service.AbstractServiceImpl;
import ma.formation.irisi.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;





import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
@Service
public class CategorieProduitAdminServiceImpl extends AbstractServiceImpl<CategorieProduit, CategorieProduitCriteria, CategorieProduitDao> implements CategorieProduitAdminService {





    public CategorieProduit findByReferenceEntity(CategorieProduit t){
        return  dao.findByReference(t.getReference());
    }


    public List<CategorieProduit> findAllOptimized() {
        return dao.findAllOptimized();
    }





    public void configure() {
        super.configure(CategorieProduit.class, CategorieProduitSpecification.class);
    }



    public CategorieProduitAdminServiceImpl(CategorieProduitDao dao) {
        super(dao);
    }

}
