package cu.maya.springcloud.msvc.mvsvcaddress.services.impl;

import cu.maya.springcloud.msvc.mvsvcaddress.persistence.models.WasteManagerAddressEntity;
import cu.maya.springcloud.msvc.mvsvcaddress.persistence.repositories.WasteManagerAddressEntityRepository;
import cu.maya.springcloud.msvc.mvsvcaddress.services.IWasteManagerAddressEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WasteManagerAddressEntityService implements IWasteManagerAddressEntityService {

    @Autowired
    private WasteManagerAddressEntityRepository wasteManagerAddressEntityRepository;

    @Override
    @Transactional
    public WasteManagerAddressEntity save(WasteManagerAddressEntity address) {
        return wasteManagerAddressEntityRepository.save(address);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WasteManagerAddressEntity> byId(Long id) {
        return wasteManagerAddressEntityRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WasteManagerAddressEntity> list() {
        return (List<WasteManagerAddressEntity>) wasteManagerAddressEntityRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        wasteManagerAddressEntityRepository.deleteById(id);
    }
}
