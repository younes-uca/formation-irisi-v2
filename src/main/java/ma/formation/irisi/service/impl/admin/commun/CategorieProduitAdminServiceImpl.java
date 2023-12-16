package ma.formation.irisi.service.impl.admin.commun;


import ma.formation.irisi.bean.core.commun.CategorieProduit;
import ma.formation.irisi.dao.criteria.core.commun.CategorieProduitCriteria;
import ma.formation.irisi.dao.facade.core.commun.CategorieProduitDao;
import ma.formation.irisi.service.facade.admin.commun.CategorieProduitAdminService;
import ma.formation.irisi.zynerator.service.AbstractServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieProduitAdminServiceImpl extends AbstractServiceImpl<CategorieProduit, CategorieProduitCriteria, CategorieProduitDao> implements CategorieProduitAdminService {


    public CategorieProduit findByReferenceEntity(CategorieProduit t) {
        return dao.findByReference(t.getReference());
    }

    @Override
    public List<CategorieProduit> findByCriteria(CategorieProduitCriteria critera) {
        return null;
    }

    @Override
    public List<CategorieProduit> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<CategorieProduit> findPaginatedByCriteria(CategorieProduitCriteria critera, int page, int pageSize, String order, String sortField) {
        return null;
    }

    @Override
    public int getDataSize(CategorieProduitCriteria criteria) {
        return 0;
    }


    public void configure() {
        super.configure(CategorieProduit.class);
    }


    public CategorieProduitAdminServiceImpl(CategorieProduitDao dao) {
        super(dao);
    }

}
