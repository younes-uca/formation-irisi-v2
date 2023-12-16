package ma.formation.irisi.zynerator.service;

import ma.formation.irisi.zynerator.audit.AuditBusinessObject;
import ma.formation.irisi.zynerator.criteria.BaseCriteria;
import ma.formation.irisi.zynerator.exception.BusinessRuleException;
import ma.formation.irisi.zynerator.exception.EntityNotFoundException;
import ma.formation.irisi.zynerator.repository.AbstractRepository;
import ma.formation.irisi.zynerator.util.FileUtils;
import ma.formation.irisi.zynerator.util.ListUtil;
import ma.formation.irisi.zynerator.util.MD5Checksum;
import ma.formation.irisi.zynerator.util.RefelexivityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public abstract class AbstractServiceImpl<T extends AuditBusinessObject, CRITERIA extends BaseCriteria, REPO extends AbstractRepository<T, Long>> extends AbstractServiceImplHelper<T> {


    protected REPO dao;


    protected Class<T> itemClass;

    @Value("${uploads.location.directory}")
    private String UPLOADED_FOLDER;
    @Value("${uploads.location.temp}")
    private String UPLOADED_TEMP_FOLDER;

    public AbstractServiceImpl(REPO dao) {
        this.dao = dao;
        this.configure();
    }


    public void deleteAssociatedLists(Long id) {
    }

    public void deleteAssociatedListsByReferenceEntity(T t) {
    }

    public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }

    public boolean deleteByIdCheckCondition(Long id) {
        return true;
    }

    public void deleteByIdIn(List<Long> ids) {
        //dao.deleteByIdIn(ids);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public T create(T t) {
        T loaded = findByReferenceEntity(t);
        if (loaded == null) {
            T saved = dao.save(t);
            return saved;
        } else {
            return null;
        }
    }
    /*

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DTO create(DTO dto) {
        T t = converter.toItem(dto);
        T saved = dao.save(t);
        dto.setId(saved.getId());
        return dto;
    }
    */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<T> create(List<T> ts) {
        List<T> result = new ArrayList<>();
        if (ts != null) {
            for (T t : ts) {
                if (t.getId() == null || findById(t.getId()) == null) {
                    dao.save(t);
                } else {
                    result.add(t);
                }
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<T> update(List<T> ts, boolean createIfNotExist) {
        List<T> result = new ArrayList<>();
        if (ts != null) {
            for (T t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    T loadedItem = dao.findById(t.getId()).orElse(null);
                    if (createIfNotExist && (t.getId() == null || loadedItem == null)) {
                        dao.save(t);
                    } else if (t.getId() != null && loadedItem != null) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }

    /*  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DTO> create(List<DTO> dtos) {
        if (dtos != null) {
            for (DTO dto : dtos) {
                create(dto);
            }
        }
        return dtos;
    }
    */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public T update(T t) {
        //saveAuditData(t, ACTION_TYPE.UPDATE);
        T loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{itemClass.getSimpleName(), t.getId().toString()});
        } else {
            //Utils.copyNonNullProperties(t, loadedItem);
            //dao.saveAndFlush(loadedItem);
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public T findById(Long id) {
        Optional<T> item = dao.findById(id);
        return item.orElse(null);
    }

    public T findByReferenceEntity(T t) {
        return t.getId() == null ? null : findById(t.getId());
    }

    public T findOrSave(T t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            T result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public void findOrSaveAssociatedObject(T t) {

    }

    public List<T> importerData(List<T> items) {
        List<T> list = new ArrayList<>();
        for (T t : items) {
            T founded = findByReferenceEntity(t);
            if (founded == null) {
                findOrSaveAssociatedObject(t);
                dao.save(t);
            } else {
                list.add(founded);
            }
        }
        return list;
    }

    public T findWithAssociatedLists(Long id) {
        return findById(id);
    }

    public void deleteWithAssociatedLists(T t) {
        deleteAssociatedLists(t.getId());
        delete(t);
    }

    public void updateWithAssociatedLists(T t) {

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void delete(T t) {
        if (t != null) {
            deleteAssociatedLists(t.getId());
            dao.deleteById(t.getId()); // il fait find by id apres delete !!!
            //constructAndSaveHistory(dto, ACTION_TYPE.DELETE); TO DO
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void delete(List<T> list) {
        if (list != null) {
            for (T t : list) {
                deleteAssociatedLists(t.getId());
                dao.deleteById(t.getId()); // il fait find by id apres delete !!!
                //constructAndSaveHistory(dto, ACTION_TYPE.DELETE); TO DO
            }
        }
    }


    public List<T> findAll() {
        return dao.findAll();
    }


    //****************************** HISTORY

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
        history.setDate(LocalDateTime.now());
        historyRepository.save(history);
    }

    */


    public void configure(Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    public abstract void configure();


    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        String crName = null;
        if (FileUtils.isFileExist(UPLOADED_TEMP_FOLDER, tempUpladedFile)) {
            String filePath = destinationFilePath;
            if (!FileUtils.isFileExist(UPLOADED_TEMP_FOLDER, tempUpladedFile))
                return crName;

            String checksum = MD5Checksum.getMD5Checksum(UPLOADED_TEMP_FOLDER + tempUpladedFile);
            if (!checksum.equals(checksumOld)) {
                throw new BusinessRuleException("errors.file.checksum", new String[]{tempUpladedFile});
            }

            crName = FileUtils.saveFile(UPLOADED_TEMP_FOLDER, UPLOADED_FOLDER, tempUpladedFile, filePath, "");

            if (FileUtils.isFileExist(UPLOADED_FOLDER, crName)) {
                checksum = MD5Checksum.getMD5Checksum(UPLOADED_FOLDER + crName);
                if (!checksum.equals(checksumOld)) {
                    throw new BusinessRuleException("errors.file.checksum", new String[]{""});
                }
            } else {
                throw new BusinessRuleException("errors.file.data.creation", new String[]{""});
            }
        }
        return crName;
    }

    public List<T> importExcel(MultipartFile file) {

        return null;
    }

    protected List<Attribute> getAttributes() {
        return new ArrayList<>();
    }

    public boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    //************************************************** UPDATE ***********************************
    public List<List<T>> getToBeSavedAndToBeDeleted(List<T> oldList, List<T> newList) {
        List<List<T>> result = new ArrayList<>();
        List<T> resultDelete = new ArrayList<>();
        List<T> resultUpdateOrSave = new ArrayList<>();
        if (ListUtil.isEmpty(oldList) && ListUtil.isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (ListUtil.isEmpty(newList) && ListUtil.isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (ListUtil.isNotEmpty(newList) && ListUtil.isNotEmpty(oldList)) {
            for (int i = 0; i < oldList.size(); i++) {
                T myOld = oldList.get(i);
                T t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t);
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                T myNew = newList.get(i);
                T t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew);
                }
            }
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }
}
