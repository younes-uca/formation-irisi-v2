package ma.formation.irisi.service.facade.admin.stock;

import java.util.List;
import ma.formation.irisi.bean.core.stock.AchatItem;
import ma.formation.irisi.dao.criteria.core.stock.AchatItemCriteria;
import ma.formation.irisi.zynerator.service.IService;



public interface AchatItemAdminService extends  IService<AchatItem,AchatItemCriteria>  {

    List<AchatItem> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitReference(String reference);
    List<AchatItem> findByAchatId(Long id);
    int deleteByAchatId(Long id);
    long countByAchatReference(String reference);



}
