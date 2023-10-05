package cu.maya.springcloud.msvc.msvcentity.services.impl;

import cu.maya.springcloud.msvc.msvcentity.client.AddressClientRest;
import cu.maya.springcloud.msvc.msvcentity.persistence.models.WasteManagerAddressEntityDTO;
import cu.maya.springcloud.msvc.msvcentity.persistence.models.WasteManagerEntity;
import cu.maya.springcloud.msvc.msvcentity.persistence.repositories.WasteManagerEntityRepository;
import cu.maya.springcloud.msvc.msvcentity.services.IWasteManagerEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WasteManagerEntityService implements IWasteManagerEntityService {

    @Autowired
    private WasteManagerEntityRepository wasteManagerEntityRepository;

    @Autowired
    private AddressClientRest addressClientRest;

    @Override
    @Transactional
    public WasteManagerEntity save(WasteManagerEntity entity, boolean update) {
        WasteManagerAddressEntityDTO address = null;
        if (update) address = addressClientRest.update(entity.getWasteManagerAddressEntity());
        else address = addressClientRest.save(entity.getWasteManagerAddressEntity());
        entity.setAddressId(address.getAddressId());
        WasteManagerEntity dbEntity = wasteManagerEntityRepository.save(entity);
        dbEntity.setWasteManagerAddressEntity(address);
        return dbEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WasteManagerEntity> byId(Long id) {
        Optional<WasteManagerEntity> optEntity = wasteManagerEntityRepository.findById(id);
        if (optEntity.isPresent()) {
            WasteManagerEntity wasteManagerEntity = optEntity.get();
            WasteManagerAddressEntityDTO address = addressClientRest.detail(wasteManagerEntity.getAddressId());
            wasteManagerEntity.setWasteManagerAddressEntity(address);
            return Optional.of(wasteManagerEntity);
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<WasteManagerEntity> list() {
        List<WasteManagerEntity> entities = (List<WasteManagerEntity>) wasteManagerEntityRepository.findAll();
        for (WasteManagerEntity wasteManagerEntity : entities) {
            WasteManagerAddressEntityDTO address = addressClientRest.detail(wasteManagerEntity.getAddressId());
            wasteManagerEntity.setWasteManagerAddressEntity(address);
        }
        return entities;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<WasteManagerEntity> optEntity = wasteManagerEntityRepository.findById(id);
        if (optEntity.isPresent()) {
            WasteManagerEntity wasteManagerEntity = optEntity.get();
            addressClientRest.delete(wasteManagerEntity.getAddressId());
            wasteManagerEntityRepository.delete(wasteManagerEntity);
        }
    }
}
