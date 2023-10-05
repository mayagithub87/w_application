package cu.maya.springcloud.msvc.mvsvcaddress.controllers;

import com.netflix.discovery.EurekaClient;
import cu.maya.springcloud.msvc.mvsvcaddress.persistence.models.WasteManagerAddressEntity;
import cu.maya.springcloud.msvc.mvsvcaddress.services.impl.WasteManagerAddressEntityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class WasteManagerAddressController {

    @Lazy
    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private WasteManagerAddressEntityService wasteManagerAddressService;

    @GetMapping
    public ResponseEntity<List<WasteManagerAddressEntity>> list() {
        return ResponseEntity.ok(wasteManagerAddressService.list());
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody WasteManagerAddressEntity address, BindingResult result) {
        if (result.hasErrors()) {
            return validate(result);
        }

        WasteManagerAddressEntity dbAddress = wasteManagerAddressService.save(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(dbAddress);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@Valid @RequestBody WasteManagerAddressEntity address, BindingResult result) {
        if (result.hasErrors()) {
            return validate(result);
        }

        Optional<WasteManagerAddressEntity> optAddress = wasteManagerAddressService.byId(address.getAddressId());
        if (optAddress.isPresent()) {
            WasteManagerAddressEntity dbAddress = optAddress.get();
            dbAddress.setIsEnabled(address.getIsEnabled());
            dbAddress.setAddress(address.getAddress());
            dbAddress.setVersion(address.getVersion());
            return ResponseEntity.status(HttpStatus.CREATED).body(wasteManagerAddressService.save(dbAddress));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {
        Optional<WasteManagerAddressEntity> optAddress = wasteManagerAddressService.byId(id);
        if (optAddress.isPresent()) return ResponseEntity.ok(optAddress.get());
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<WasteManagerAddressEntity> optAddress = wasteManagerAddressService.byId(id);
        if (optAddress.isPresent()) {
            wasteManagerAddressService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "At field %s %s".formatted(err.getField(), err.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
