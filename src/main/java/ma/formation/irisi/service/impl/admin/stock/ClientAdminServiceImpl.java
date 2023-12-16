package ma.formation.irisi.service.impl.admin.stock;


import ma.formation.irisi.bean.core.stock.Client;
import ma.formation.irisi.dao.criteria.core.stock.ClientCriteria;
import ma.formation.irisi.dao.facade.core.stock.ClientDao;
import ma.formation.irisi.dao.specification.core.stock.ClientSpecification;
import ma.formation.irisi.service.facade.admin.stock.ClientAdminService;
import ma.formation.irisi.zynerator.service.AbstractServiceImpl;
import ma.formation.irisi.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;





import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
@Service
public class ClientAdminServiceImpl extends AbstractServiceImpl<Client, ClientCriteria, ClientDao> implements ClientAdminService {





    public Client findByReferenceEntity(Client t){
        return  dao.findByCin(t.getCin());
    }


    public List<Client> findAllOptimized() {
        return dao.findAllOptimized();
    }





    public void configure() {
        super.configure(Client.class, ClientSpecification.class);
    }



    public ClientAdminServiceImpl(ClientDao dao) {
        super(dao);
    }

}
