package  ma.formation.irisi.ws.facade.admin.commun;

import io.swagger.v3.oas.annotations.Operation;
import ma.formation.irisi.bean.core.commun.Produit;
import ma.formation.irisi.dao.criteria.core.commun.ProduitCriteria;
import ma.formation.irisi.service.facade.admin.commun.ProduitAdminService;
import ma.formation.irisi.ws.converter.commun.ProduitConverter;
import ma.formation.irisi.ws.dto.commun.ProduitDto;
import ma.formation.irisi.zynerator.controller.AbstractController;
import ma.formation.irisi.zynerator.dto.FileTempDto;
import ma.formation.irisi.zynerator.util.PaginatedList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/produit/")
public class ProduitRestAdmin  extends AbstractController<Produit, ProduitDto, ProduitCriteria, ProduitAdminService, ProduitConverter> {



    @Operation(summary = "upload one produit")
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }
    @Operation(summary = "upload multiple produits")
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    @Operation(summary = "Finds a list of all produits")
    @GetMapping("")
    public ResponseEntity<List<ProduitDto>> findAll() throws Exception {
        return super.findAll();
    }

    @Operation(summary = "Finds an optimized list of all produits")
    @GetMapping("optimized")
    public ResponseEntity<List<ProduitDto>> findAllOptimized() throws Exception {
        return super.findAllOptimized();
    }

    @Operation(summary = "Finds a produit by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProduitDto> findById(@PathVariable Long id, String[] includes, String[] excludes) throws Exception {
        return super.findById(id, includes, excludes);
    }

    @Operation(summary = "Finds a produit by reference")
    @GetMapping("reference/{reference}")
    public ResponseEntity<ProduitDto> findByReference(@PathVariable String reference, String[] includes, String[] excludes) throws Exception {
        return super.findByReferenceEntity(new Produit(reference), includes, excludes);
    }

    @Operation(summary = "Saves the specified  produit")
    @PostMapping("")
    public ResponseEntity<ProduitDto> save(@RequestBody ProduitDto dto) throws Exception {
        return super.save(dto);
    }

    @Operation(summary = "Updates the specified  produit")
    @PutMapping("")
    public ResponseEntity<ProduitDto> update(@RequestBody ProduitDto dto) throws Exception {
        return super.update(dto);
    }

    @Operation(summary = "Delete list of produit")
    @PostMapping("multiple")
    public ResponseEntity<List<ProduitDto>> delete(@RequestBody List<ProduitDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }
    @Operation(summary = "Delete the specified produit")
    @DeleteMapping("")
    public ResponseEntity<ProduitDto> delete(@RequestBody ProduitDto dto) throws Exception {
            return super.delete(dto);
    }

    @Operation(summary = "Delete the specified produit")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        return super.deleteById(id);
    }
    @Operation(summary = "Delete multiple produits by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
            return super.deleteByIdIn(ids);
     }


    @Operation(summary = "find by categorieProduit id")
    @GetMapping("categorieProduit/id/{id}")
    public List<ProduitDto> findByCategorieProduitId(@PathVariable Long id){
        return findDtos(service.findByCategorieProduitId(id));
    }
    @Operation(summary = "delete by categorieProduit id")
    @DeleteMapping("categorieProduit/id/{id}")
    public int deleteByCategorieProduitId(@PathVariable Long id){
        return service.deleteByCategorieProduitId(id);
    }
    @Operation(summary = "Finds produits by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProduitDto>> findByCriteria(@RequestBody ProduitCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    @Operation(summary = "Finds paginated produits by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProduitCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @Operation(summary = "Gets produit data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProduitCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }



    public ProduitRestAdmin (ProduitAdminService service, ProduitConverter converter) {
        super(service, converter);
    }




}
