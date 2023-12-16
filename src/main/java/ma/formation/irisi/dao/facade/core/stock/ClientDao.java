package ma.formation.irisi.dao.facade.core.stock;

import org.springframework.data.jpa.repository.Query;
import ma.formation.irisi.zynerator.repository.AbstractRepository;
import ma.formation.irisi.bean.core.stock.Client;
import org.springframework.stereotype.Repository;
import ma.formation.irisi.bean.core.stock.Client;
import java.util.List;


@Repository
public interface ClientDao extends AbstractRepository<Client,Long>  {
    Client findByCin(String cin);
    int deleteByCin(String cin);


    @Query("SELECT NEW Client(item.id,item.nom) FROM Client item")
    List<Client> findAllOptimized();

}
