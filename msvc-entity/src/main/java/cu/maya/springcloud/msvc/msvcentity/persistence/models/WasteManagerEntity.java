package cu.maya.springcloud.msvc.msvcentity.persistence.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Entity
@Table(name = "wm_entities")
public class WasteManagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long entityId;

    @NotBlank
    @Column(unique = true)
    private String name;

    @NotBlank
    @Column(unique = true)
    private String nif;

    @Transient
    private WasteManagerAddressEntityDTO wasteManagerAddressEntity;

    @Column(name = "address_id", unique = true)
    private Long addressId;

    private Boolean isEnabled = Boolean.TRUE;

    private Long version = 0L;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @PrePersist
    public void prePersist() {
        createdDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        lastModifiedDate = new Date();
    }

}
