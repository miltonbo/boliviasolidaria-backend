package com.bs.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author J. Milton Chambi M.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBy {
    
    private String sortField;
    private String sortOrder;

}
