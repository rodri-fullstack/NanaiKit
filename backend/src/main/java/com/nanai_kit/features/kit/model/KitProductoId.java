package com.nanai_kit.features.kit.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class KitProductoId implements Serializable {

    private Long idKit;
    private Long idProducto;
}
