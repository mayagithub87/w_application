package cu.maya.springcloud.msvc.msvcentity.services;

import cu.maya.springcloud.msvc.msvcentity.persistence.models.WasteManagerEntity;

import java.util.List;
import java.util.Optional;

public interface IWasteManagerEntityService {

    WasteManagerEntity save(WasteManagerEntity entity, boolean update);

    Optional<WasteManagerEntity> byId(Long id);

    List<WasteManagerEntity> list();

    void delete(Long id);
}
