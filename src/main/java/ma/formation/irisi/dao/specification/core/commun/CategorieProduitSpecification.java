package  ma.formation.irisi.dao.specification.core.commun;

import ma.formation.irisi.dao.criteria.core.commun.CategorieProduitCriteria;
import ma.formation.irisi.bean.core.commun.CategorieProduit;
import ma.formation.irisi.zynerator.specification.AbstractSpecification;


public class CategorieProduitSpecification extends  AbstractSpecification<CategorieProduitCriteria, CategorieProduit>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("reference", criteria.getReference(),criteria.getReferenceLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public CategorieProduitSpecification(CategorieProduitCriteria criteria) {
        super(criteria);
    }

    public CategorieProduitSpecification(CategorieProduitCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
