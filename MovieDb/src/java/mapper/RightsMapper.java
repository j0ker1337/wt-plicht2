/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import bla.entities.Rights;
import dto.RightDTO;

/**
 *
 * @author joker
 */
public class RightsMapper {
    public static RightDTO entityToDTO(Rights rights){
        RightDTO rightDTO = new RightDTO();
        rightDTO.setId(rights.getId());
        rightDTO.setName(rights.getName());
        return rightDTO;
    }
    
    public static Rights dtoToentityT(RightDTO rightDTO){
        Rights rights = new Rights();
        rights.setId(rightDTO.getId());
        rights.setName(rightDTO.getName());
        return rights;
    }
}
