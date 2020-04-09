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
@Table(name = "punto_acopio")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PuntoAcopio implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "responsable")
    private String necesidad;

    @Column(name = "contacto")
    private String contacto;

    @Column(name = "latitud")
    private Double lat;

    @Column(name = "longitud")
    private Double lng;
    
}
