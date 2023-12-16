package ma.formation.irisi.ws.converter.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import ma.formation.irisi.zynerator.util.StringUtil;
import ma.formation.irisi.zynerator.converter.AbstractConverter;
import ma.formation.irisi.zynerator.util.DateUtil;
import ma.formation.irisi.bean.core.commun.CategorieProduit;
import ma.formation.irisi.ws.dto.commun.CategorieProduitDto;

@Component
public class CategorieProduitConverter extends AbstractConverter<CategorieProduit, CategorieProduitDto> {


    public CategorieProduitConverter() {
        super(CategorieProduit.class, CategorieProduitDto.class);
    }

    @Override
    public CategorieProduit toItem(CategorieProduitDto dto) {
        if (dto == null) {
            return null;
        } else {
            CategorieProduit item = new CategorieProduit();
            if (StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId().toString());
            if (StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());
            if (StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());


            return item;
        }
    }

    @Override
    public CategorieProduitDto toDto(CategorieProduit item) {
        if (item == null) {
            return null;
        } else {
            CategorieProduitDto dto = new CategorieProduitDto();
            if (StringUtil.isNotEmpty(item.getId()))
                dto.setId(Long.valueOf(item.getId()));
            if (StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());
            if (StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


            return dto;
        }
    }


    public void initObject(boolean value) {
    }


}
