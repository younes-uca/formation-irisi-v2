package ma.formation.irisi.zynerator.repository;

import ma.formation.irisi.zynerator.history.HistBusinessObject;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractHistoryRepository<H extends HistBusinessObject, ID> {
}
