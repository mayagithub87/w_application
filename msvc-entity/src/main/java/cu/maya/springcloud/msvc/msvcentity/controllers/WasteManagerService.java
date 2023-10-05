package cu.maya.springcloud.msvc.msvcentity.controllers;

import com.netflix.discovery.EurekaClient;
import cu.maya.springcloud.msvc.msvcentity.persistence.models.WasteManagerEntity;
import cu.maya.springcloud.msvc.msvcentity.services.impl.WasteManagerEntityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class WasteManagerService {

    @Lazy
    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private WasteManagerEntityService wasteManagerEntityService;

    @GetMapping
    public ResponseEntity<List<WasteManagerEntity>> list() {
        return ResponseEntity.ok(wasteManagerEntityService.list());
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody WasteManagerEntity entity, BindingResult result) {
        if (result.hasErrors()) {
            return validate(result);
        }

        WasteManagerEntity dbEntity = wasteManagerEntityService.save(entity, false);
        return ResponseEntity.status(HttpStatus.CREATED).body(dbEntity);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@Valid @RequestBody WasteManagerEntity entity, BindingResult result) {
        if (result.hasErrors()) {
            return validate(result);
        }

        //check that the address is has the same id
        if (entity.getAddressId() != entity.getWasteManagerAddressEntity().getAddressId()) {
            result.addError(new ObjectError("address id", "Address id doesn't match"));
            return validate(result);
        }

        Optional<WasteManagerEntity> optEntity = wasteManagerEntityService.byId(entity.getEntityId());
        if (optEntity.isPresent()) {
            WasteManagerEntity dbEntity = optEntity.get();

            if (dbEntity.getAddressId() != entity.getAddressId()) {
                result.addError(new ObjectError("address id", "Address id doesn't match with one in database"));
                return validate(result);
            }

            dbEntity.setIsEnabled(entity.getIsEnabled());
            dbEntity.setNif(entity.getNif());
            dbEntity.setName(entity.getName());
            dbEntity.setVersion(entity.getVersion());
            dbEntity.setWasteManagerAddressEntity(entity.getWasteManagerAddressEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body(wasteManagerEntityService.save(dbEntity, true));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {
        Optional<WasteManagerEntity> optEntity = wasteManagerEntityService.byId(id);
        if (optEntity.isPresent()) return ResponseEntity.ok(optEntity.get());
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<WasteManagerEntity> optEntity = wasteManagerEntityService.byId(id);
        if (optEntity.isPresent()){
            wasteManagerEntityService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "At field %s %s".formatted(err.getField(), err.getDefaultMessage())));
        result.getAllErrors().forEach(error -> errors.put(error.getObjectName(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

}
