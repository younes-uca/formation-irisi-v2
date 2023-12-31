package  ma.formation.irisi.ws.facade.admin.commun;

import io.swagger.v3.oas.annotations.Operation;
import ma.formation.irisi.bean.core.commun.CategorieProduit;
import ma.formation.irisi.dao.criteria.core.commun.CategorieProduitCriteria;
import ma.formation.irisi.service.facade.admin.commun.CategorieProduitAdminService;
import ma.formation.irisi.ws.converter.commun.CategorieProduitConverter;
import ma.formation.irisi.ws.dto.commun.CategorieProduitDto;
import ma.formation.irisi.zynerator.controller.AbstractController;
import ma.formation.irisi.zynerator.dto.FileTempDto;
import ma.formation.irisi.zynerator.util.PaginatedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categorieProduit/")
public class CategorieProduitRestAdmin  extends AbstractController<CategorieProduit, CategorieProduitDto, CategorieProduitCriteria, CategorieProduitAdminService, CategorieProduitConverter> {



    @Operation(summary = "upload one categorieProduit")
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }
    @Operation(summary = "upload multiple categorieProduits")
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    @Operation(summary = "Finds a list of all categorieProduits")
    @GetMapping("")
    public ResponseEntity<List<CategorieProduit>> findAllDyialna() throws Exception {
        List<CategorieProduit> list = service.findAllOptimized();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @Operation(summary = "Finds an optimized list of all categorieProduits")
    @GetMapping("optimized")
    public ResponseEntity<List<CategorieProduitDto>> findAllOptimized() throws Exception {
        return super.findAllOptimized();
    }

    @Operation(summary = "Finds a categorieProduit by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CategorieProduitDto> findById(@PathVariable Long id, String[] includes, String[] excludes) throws Exception {
        return super.findById(id, includes, excludes);
    }

    @Operation(summary = "Finds a categorieProduit by reference")
    @GetMapping("reference/{reference}")
    public ResponseEntity<CategorieProduit> findByReference(@PathVariable String reference, String[] includes, String[] excludes) throws Exception {
        return new ResponseEntity<>(service.findByReferenceEntity(new CategorieProduit(reference)), HttpStatus.OK);
    }

    @Operation(summary = "Saves the specified  categorieProduit")
    @PostMapping("")
    public ResponseEntity<CategorieProduitDto> save(@RequestBody CategorieProduitDto dto) throws Exception {
        return super.save(dto);
    }

    @Operation(summary = "Updates the specified  categorieProduit")
    @PutMapping("")
    public ResponseEntity<CategorieProduitDto> update(@RequestBody CategorieProduitDto dto) throws Exception {
        return super.update(dto);
    }

    @Operation(summary = "Delete list of categorieProduit")
    @PostMapping("multiple")
    public ResponseEntity<List<CategorieProduitDto>> delete(@RequestBody List<CategorieProduitDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }
    @Operation(summary = "Delete the specified categorieProduit")
    @DeleteMapping("")
    public ResponseEntity<CategorieProduitDto> delete(@RequestBody CategorieProduitDto dto) throws Exception {
            return super.delete(dto);
    }

    @Operation(summary = "Delete the specified categorieProduit")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        return super.deleteById(id);
    }
    @Operation(summary = "Delete multiple categorieProduits by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
            return super.deleteByIdIn(ids);
     }


    @Operation(summary = "Finds categorieProduits by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CategorieProduitDto>> findByCriteria(@RequestBody CategorieProduitCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    @Operation(summary = "Finds paginated categorieProduits by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CategorieProduitCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }


    @Operation(summary = "Gets categorieProduit data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CategorieProduitCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }



    public CategorieProduitRestAdmin (CategorieProduitAdminService service, CategorieProduitConverter converter) {
        super(service, converter);
    }




}
