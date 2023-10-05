package cu.maya.springcloud.msvc.msvcentity.persistence.models;

import lombok.Data;

import java.util.Date;

@Data
public class WasteManagerAddressEntityDTO {

    private Long addressId;

    private String address;

    private Boolean isEnabled = Boolean.TRUE;

    private Long version = 0L;

    private Date createdDate;

    private Date lastModifiedDate;

}
