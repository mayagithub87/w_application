package cu.maya.springcloud.msvc.mvsvcaddress.persistence.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Entity
@Table(name = "wm_addresses")
public class WasteManagerAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long addressId;

    @NotBlank
    @Column(unique = true)
    private String address;

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
