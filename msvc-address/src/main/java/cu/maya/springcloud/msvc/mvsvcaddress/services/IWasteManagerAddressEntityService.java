package cu.maya.springcloud.msvc.mvsvcaddress.services;

import cu.maya.springcloud.msvc.mvsvcaddress.persistence.models.WasteManagerAddressEntity;

import java.util.List;
import java.util.Optional;

public interface IWasteManagerAddressEntityService {

    WasteManagerAddressEntity save(WasteManagerAddressEntity entity);

    Optional<WasteManagerAddressEntity> byId(Long id);

    List<WasteManagerAddressEntity> list();

    void delete(Long id);

}
