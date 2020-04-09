package com.bs.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Milton BO
 */

@Entity
@Table(name = "solicitud")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "necesidad")
    private String necesidad;

    @Column(name = "contacto")
    private String contacto;

    @Column(name = "ci")
    private String ci;

    @Column(name = "latitude")
    private Double lat;

    @Column(name = "longitude")
    private Double lng;

    @Basic(optional = true)
    @Column(name = "fechahora_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date fechahoraSolicitud;

    @Basic(optional = false)
    @Column(name = "estado")
    @JsonIgnore
    private byte estado;
    
}
