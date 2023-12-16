package ma.formation.irisi.service.facade.admin.stock;

import java.util.List;
import ma.formation.irisi.bean.core.stock.Achat;
import ma.formation.irisi.dao.criteria.core.stock.AchatCriteria;
import ma.formation.irisi.zynerator.service.IService;



public interface AchatAdminService extends  IService<Achat,AchatCriteria>  {

    List<Achat> findByClientId(Long id);
    int deleteByClientId(Long id);
    long countByClientCin(String cin);



}
