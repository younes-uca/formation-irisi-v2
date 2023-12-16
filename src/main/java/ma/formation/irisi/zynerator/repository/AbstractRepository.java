package ma.formation.irisi.zynerator.repository;

import ma.formation.irisi.zynerator.audit.AuditBusinessObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T extends AuditBusinessObject, ID> extends MongoRepository<T, ID> {
}
