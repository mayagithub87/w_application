package cu.maya.springcloud.msvc.msvcentity.client;

import cu.maya.springcloud.msvc.msvcentity.persistence.models.WasteManagerAddressEntityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "msvc-address")
public interface AddressClientRest {

    @GetMapping("/{id}")
    WasteManagerAddressEntityDTO detail(@PathVariable Long id);

    @PostMapping("/")
    WasteManagerAddressEntityDTO save(@RequestBody WasteManagerAddressEntityDTO address);

    @PutMapping("/")
    WasteManagerAddressEntityDTO update(@RequestBody WasteManagerAddressEntityDTO address);

    @DeleteMapping("/{id}")
    WasteManagerAddressEntityDTO delete(@PathVariable Long id);

}
