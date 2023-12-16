package ma.formation.irisi.service.impl.admin.stock;


import ma.formation.irisi.bean.core.stock.AchatItem;
import ma.formation.irisi.dao.criteria.core.stock.AchatItemCriteria;
import ma.formation.irisi.dao.facade.core.stock.AchatItemDao;
import ma.formation.irisi.dao.specification.core.stock.AchatItemSpecification;
import ma.formation.irisi.service.facade.admin.stock.AchatItemAdminService;
import ma.formation.irisi.zynerator.service.AbstractServiceImpl;
import ma.formation.irisi.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;





import org.springframework.beans.factory.annotation.Autowired;

import ma.formation.irisi.service.facade.admin.stock.AchatAdminService ;
import ma.formation.irisi.bean.core.stock.Achat ;
import ma.formation.irisi.service.facade.admin.commun.ProduitAdminService ;
import ma.formation.irisi.bean.core.commun.Produit ;

import java.util.List;
@Service
public class AchatItemAdminServiceImpl extends AbstractServiceImpl<AchatItem, AchatItemCriteria, AchatItemDao> implements AchatItemAdminService {






    public List<AchatItem> findByProduitId(Long id){
        return dao.findByProduitId(id);
    }
    public int deleteByProduitId(Long id){
        return dao.deleteByProduitId(id);
    }
    public long countByProduitReference(String reference){
        return dao.countByProduitReference(reference);
    }
    public List<AchatItem> findByAchatId(Long id){
        return dao.findByAchatId(id);
    }
    public int deleteByAchatId(Long id){
        return dao.deleteByAchatId(id);
    }
    public long countByAchatReference(String reference){
        return dao.countByAchatReference(reference);
    }






    public void configure() {
        super.configure(AchatItem.class, AchatItemSpecification.class);
    }


    @Autowired
    private AchatAdminService achatService ;
    @Autowired
    private ProduitAdminService produitService ;

    public AchatItemAdminServiceImpl(AchatItemDao dao) {
        super(dao);
    }

}
