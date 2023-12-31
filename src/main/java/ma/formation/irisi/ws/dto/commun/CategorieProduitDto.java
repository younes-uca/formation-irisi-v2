package  ma.formation.irisi.ws.dto.commun;

import ma.formation.irisi.zynerator.audit.Log;
import ma.formation.irisi.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategorieProduitDto  extends AuditBaseDto {

    private String reference  ;
    private String libelle  ;




    public CategorieProduitDto(){
        super();
    }



    @Log
    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }

    @Log
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }








}
