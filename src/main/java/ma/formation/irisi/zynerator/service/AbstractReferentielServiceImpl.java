package ma.formation.irisi.zynerator.service;

import ma.formation.irisi.zynerator.audit.AuditBusinessObjectEnhanced;
import ma.formation.irisi.zynerator.criteria.BaseCriteria;
import ma.formation.irisi.zynerator.repository.AbstractRepository;

public abstract class AbstractReferentielServiceImpl<T extends AuditBusinessObjectEnhanced, CRITERIA extends BaseCriteria, REPO extends AbstractRepository<T, Long>> extends AbstractServiceImpl<T, CRITERIA, REPO> {

    public AbstractReferentielServiceImpl(REPO dao) {
        super(dao);
    }

}
