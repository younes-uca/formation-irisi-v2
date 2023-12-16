package ma.formation.irisi.bean.core.commun;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.formation.irisi.zynerator.audit.AuditBusinessObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;




@Document(collection = "categorie_produit")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategorieProduit   extends AuditBusinessObject     {

    private String id;

    private String reference;
    private String libelle;



    public CategorieProduit(){
        super();
    }

    public CategorieProduit(String id,String reference){
        this.id = id;
        this.reference = reference ;
    }
    public CategorieProduit(String reference){
        this.reference = reference ;
    }




    @Id
    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    @Transient
    public String getLabel() {
        label = reference;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorieProduit categorieProduit = (CategorieProduit) o;
        return id != null && id.equals(categorieProduit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

