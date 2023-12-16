package ma.formation.irisi.zynerator.service;

import ma.formation.irisi.zynerator.dto.AuditEntityDto;
import ma.formation.irisi.zynerator.history.HistBusinessObject;
import ma.formation.irisi.zynerator.history.HistCriteria;
import ma.formation.irisi.zynerator.repository.AbstractHistoryRepository;

import java.util.List;


public abstract class AbstractServiceHistoryImpl<H extends HistBusinessObject, HC extends HistCriteria, HISTREPO extends AbstractHistoryRepository<H, Long>> {


    protected HISTREPO historyRepository;
    protected Class<H> historyClass;
    protected Class<HC> historyCriteriaClass;


    public AbstractServiceHistoryImpl(HISTREPO historyRepository) {
        this.historyRepository = historyRepository;
        this.configure();
    }


    public void deleteAssociatedLists(Long id) {
    }

    public void deleteById(Long id) {
        return;
    }

    public void deleteByIdIn(List<Long> ids) {
        //dao.deleteByIdIn(ids);
    }


    /*
    public void saveAuditData(DTO dto, ACTION_TYPE action){
    DTO old = abstractConverter.toDto(findById(dto.getId()));
    try {
        if (Utils.compareObjectsDiff(dto, old)) {
            constructAndSaveHistory(dto, action);
        }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public void constructAndSaveHistory(DTO dto, ACTION_TYPE action) {
        User currentUser = getCurrentUser();
        H history = RefelexivityUtil.constructObjectUsingDefaultConstr(historyClass);
        history.setActionType(action.name());
        history.setObjectName(itemClass.getSimpleName());
        history.setObjectId(dto.getId());
        history.setUserId(currentUser.getId());
        history.setUsername(currentUser.getUsername());
        String dtoAsJson = null;
        try {
            dtoAsJson = new ObjectMapper().writeValueAsString(dto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        history.setData(dtoAsJson);
        history.setDateHistory(LocalDateTime.now());
        historyRepository.save(history);
    }

    */

    public AuditEntityDto findHistoryById(Long id) {
        return null;
        //TODO: restore this ==> return (AuditEntityDto) abstractConverter.copyFromHistory(h);
    }


    public void configure(Class<H> historyClass, Class<HC> historyCriteriaClass) {
        this.historyClass = historyClass;
        this.historyCriteriaClass = historyCriteriaClass;
    }

    public abstract void configure();


}
